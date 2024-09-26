use std::fs::File;
use std::slice;

use crate::parquet::col_type::ColumnType;
use crate::parquet::error::{ParquetErrorExt, ParquetResult};
use crate::parquet::io::{FromRawFdI32Ext, NonOwningFile};
use crate::parquet::qdb_metadata::ParquetFieldId;
use crate::parquet_read::decode::ParquetColumnIndex;
use jni::objects::JClass;
use jni::JNIEnv;
use std::mem::{offset_of, size_of};

use crate::parquet_read::{
    ColumnChunkBuffers, ColumnChunkStats, ColumnMeta, ParquetDecoder, RowGroupBuffers,
    RowGroupStatBuffers,
};
use crate::utils;

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_create(
    mut env: JNIEnv,
    _class: JClass,
    raw_fd: i32,
) -> *mut ParquetDecoder<NonOwningFile> {
    let reader = NonOwningFile::new(unsafe { File::from_raw_fd_i32(raw_fd) });
    match ParquetDecoder::read(reader) {
        Ok(decoder) => Box::into_raw(Box::new(decoder)),
        Err(err) => utils::throw_java_ex(&mut env, "PartitionDecoder.create", &err),
    }
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_destroy(
    _env: JNIEnv,
    _class: JClass,
    decoder: *mut ParquetDecoder<NonOwningFile>,
) {
    if decoder.is_null() {
        panic!("decoder pointer is null");
    }

    drop(unsafe { Box::from_raw(decoder) });
}

fn validate_jni_column_types(columns: &[(ParquetFieldId, ColumnType)]) -> ParquetResult<()> {
    for &(_, java_column_type) in columns {
        let code = java_column_type.code();
        let res: ParquetResult<ColumnType> = code.try_into();
        res.context("invalid column type passed across JNI layer")?;
    }
    Ok(())
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_decodeRowGroup(
    mut env: JNIEnv,
    _class: JClass,
    decoder: *mut ParquetDecoder<NonOwningFile>,
    row_group_bufs: *mut RowGroupBuffers,
    columns: *const (ParquetColumnIndex, ColumnType),
    column_count: u32,
    row_group_index: u32,
) -> usize {
    assert!(!decoder.is_null(), "decoder pointer is null");
    assert!(
        !row_group_bufs.is_null(),
        "row group buffers pointer is null"
    );
    assert!(!columns.is_null(), "columns pointer is null");

    let decoder = unsafe { &mut *decoder };
    let row_group_bufs = unsafe { &mut *row_group_bufs };
    let columns = unsafe { slice::from_raw_parts(columns, column_count as usize) };

    // We've unsafely accepted a `ColumnType` from Java, so we need to validate it.
    let res = validate_jni_column_types(columns)
        .and_then(|()| decoder.decode_row_group(row_group_bufs, columns, row_group_index));

    match res {
        Ok(row_count) => row_count,
        Err(err) => utils::throw_java_ex(&mut env, "decodeRowGroup", &err),
    }
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_getRowGroupStats(
    mut env: JNIEnv,
    _class: JClass,
    decoder: *mut ParquetDecoder<NonOwningFile>,
    row_group_stat_bufs: *mut RowGroupStatBuffers,
    columns: *const (ParquetColumnIndex, ColumnType),
    column_count: u32,
    row_group_index: u32,
) {
    assert!(!decoder.is_null(), "decoder pointer is null");
    assert!(
        !row_group_stat_bufs.is_null(),
        "row group stat buffers pointer is null"
    );
    assert!(!columns.is_null(), "columns pointer is null");

    let decoder = unsafe { &mut *decoder };
    let row_group_stat_bufs = unsafe { &mut *row_group_stat_bufs };
    let columns = unsafe { slice::from_raw_parts(columns, column_count as usize) };

    let res = validate_jni_column_types(columns).and_then(|()| {
        decoder.update_column_chunk_stats(row_group_stat_bufs, columns, row_group_index)
    });

    match res {
        Ok(_) => {}
        Err(err) => utils::throw_java_ex(&mut env, "getRowGroupStats", &err),
    }
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnCountOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ParquetDecoder<NonOwningFile>, col_count)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_rowCountOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ParquetDecoder<NonOwningFile>, row_count)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_rowGroupCountOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ParquetDecoder<NonOwningFile>, row_group_count)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_rowGroupSizesPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ParquetDecoder<NonOwningFile>, row_group_sizes_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnsPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ParquetDecoder<NonOwningFile>, columns_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnRecordTypeOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnMeta, column_type)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnRecordNamePtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnMeta, name_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnRecordNameSizeOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnMeta, name_size)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnIdsOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnMeta, id)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_PartitionDecoder_columnRecordSize(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    size_of::<ColumnMeta>()
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_create(
    _env: JNIEnv,
    _class: JClass,
) -> *mut RowGroupBuffers {
    Box::into_raw(Box::new(RowGroupBuffers::new()))
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_destroy(
    _env: JNIEnv,
    _class: JClass,
    buffers: *mut RowGroupBuffers,
) {
    if buffers.is_null() {
        panic!("row group buffers pointer is null");
    }

    unsafe {
        drop(Box::from_raw(buffers));
    }
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_columnChunkBuffersSize(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    size_of::<ColumnChunkBuffers>()
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_columnBuffersPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(RowGroupBuffers, column_bufs_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_chunkDataPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkBuffers, data_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_chunkDataSizeOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkBuffers, data_size)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_chunkAuxPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkBuffers, aux_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupBuffers_chunkAuxSizeOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkBuffers, aux_size)
}

// RowGroupStatsBuffers
#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_create(
    _env: JNIEnv,
    _class: JClass,
) -> *mut RowGroupStatBuffers {
    Box::into_raw(Box::new(RowGroupStatBuffers::new()))
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_destroy(
    _env: JNIEnv,
    _class: JClass,
    stat_buffers: *mut RowGroupStatBuffers,
) {
    if stat_buffers.is_null() {
        panic!("row group stat buffers pointer is null");
    }

    unsafe {
        drop(Box::from_raw(stat_buffers));
    }
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_buffersPtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(RowGroupStatBuffers, column_chunk_stats_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_buffersSize(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    size_of::<ColumnChunkStats>()
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_minValuePtrOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkStats, min_value_ptr)
}

#[no_mangle]
pub extern "system" fn Java_io_questdb_griffin_engine_table_parquet_RowGroupStatBuffers_minValueSizeOffset(
    _env: JNIEnv,
    _class: JClass,
) -> usize {
    offset_of!(ColumnChunkStats, min_value_size)
}
