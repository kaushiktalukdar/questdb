/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2024 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.griffin.engine.table.parquet;

import io.questdb.cairo.Reopenable;
import io.questdb.log.Log;
import io.questdb.log.LogFactory;
import io.questdb.std.Os;
import io.questdb.std.QuietCloseable;
import io.questdb.std.Unsafe;

public class RowGroupStatBuffers implements QuietCloseable, Reopenable {
    private static final long CHUNK_STATS_PTR_OFFSET;
    private static final long CHUNK_STATS_MIN_VALUE_PTR_OFFSET;
    private static final long CHUNK_STATS_MIN_VALUE_SIZE_OFFSET;
    private static final long CHUNK_STATS_STRUCT_SIZE;
    private static final Log LOG = LogFactory.getLog(RowGroupStatBuffers.class);
    private long ptr;

    public RowGroupStatBuffers() {
        this.ptr = create();
    }

    public long getMinValuePtr(int columnIndex) {
        final long statBuffersPtr = Unsafe.getUnsafe().getLong(ptr + CHUNK_STATS_PTR_OFFSET);
        return Unsafe.getUnsafe().getLong(statBuffersPtr + columnIndex * CHUNK_STATS_STRUCT_SIZE + CHUNK_STATS_MIN_VALUE_PTR_OFFSET);
    }

    public long getMinValueSize(int columnIndex) {
        final long statBuffersPtr = Unsafe.getUnsafe().getLong(ptr + CHUNK_STATS_PTR_OFFSET);
        return Unsafe.getUnsafe().getLong(statBuffersPtr + columnIndex * CHUNK_STATS_STRUCT_SIZE + CHUNK_STATS_MIN_VALUE_SIZE_OFFSET);
    }

    public long getMinValueLong(int columnIndex) {
        final long size = getMinValueSize(columnIndex);
        assert size == Long.BYTES;
        final long ptr = getMinValuePtr(columnIndex);
        assert ptr != 0;
        return Unsafe.getUnsafe().getLong(ptr);
    }

    @Override
    public void close() {
        if (ptr != 0) {
            destroy(ptr);
            ptr = 0;
        }
    }

    public long ptr() {
        return ptr;
    }

    @Override
    public void reopen() {
        if (ptr == 0) {
            ptr = create();
        }
    }

    private static native long minValuePtrOffset();

    private static native long minValueSizeOffset();

    private static native long buffersPtrOffset();

    private static native long buffersSize();

    private static native long create();

    private static native void destroy(long impl);

    static {
        Os.init();

        CHUNK_STATS_PTR_OFFSET = buffersPtrOffset();
        CHUNK_STATS_STRUCT_SIZE = buffersSize();
        CHUNK_STATS_MIN_VALUE_PTR_OFFSET = minValuePtrOffset();
        CHUNK_STATS_MIN_VALUE_SIZE_OFFSET = minValueSizeOffset();
    }
}