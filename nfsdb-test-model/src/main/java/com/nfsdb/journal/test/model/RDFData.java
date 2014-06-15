/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.nfsdb.journal.test.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import java.util.*;

public class RDFData implements org.apache.thrift.TBase<RDFData, RDFData._Fields>, java.io.Serializable, Cloneable {
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.SUBJ, new org.apache.thrift.meta_data.FieldMetaData("subj", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.SUBJ_TYPE, new org.apache.thrift.meta_data.FieldMetaData("subjType", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.PREDICATE, new org.apache.thrift.meta_data.FieldMetaData("predicate", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.OBJ, new org.apache.thrift.meta_data.FieldMetaData("obj", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.OBJ_TYPE, new org.apache.thrift.meta_data.FieldMetaData("objType", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        tmpMap.put(_Fields.DELETED, new org.apache.thrift.meta_data.FieldMetaData("deleted", org.apache.thrift.TFieldRequirementType.OPTIONAL,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RDFData.class, metaDataMap);
    }

    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RDFData");
    private static final org.apache.thrift.protocol.TField SUBJ_FIELD_DESC = new org.apache.thrift.protocol.TField("subj", org.apache.thrift.protocol.TType.STRING, (short) 1);
    private static final org.apache.thrift.protocol.TField SUBJ_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("subjType", org.apache.thrift.protocol.TType.STRING, (short) 2);
    private static final org.apache.thrift.protocol.TField PREDICATE_FIELD_DESC = new org.apache.thrift.protocol.TField("predicate", org.apache.thrift.protocol.TType.STRING, (short) 3);
    private static final org.apache.thrift.protocol.TField OBJ_FIELD_DESC = new org.apache.thrift.protocol.TField("obj", org.apache.thrift.protocol.TType.STRING, (short) 4);
    private static final org.apache.thrift.protocol.TField OBJ_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("objType", org.apache.thrift.protocol.TType.STRING, (short) 5);
    private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short) 6);
    private static final org.apache.thrift.protocol.TField DELETED_FIELD_DESC = new org.apache.thrift.protocol.TField("deleted", org.apache.thrift.protocol.TType.BOOL, (short) 7);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();

    static {
        schemes.put(StandardScheme.class, new RDFDataStandardSchemeFactory());
        schemes.put(TupleScheme.class, new RDFDataTupleSchemeFactory());
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private static final int __DELETED_ISSET_ID = 1;
    public String subj; // required
    public String subjType; // required
    public String predicate; // required
    public String obj; // required
    public String objType; // required
    public long timestamp; // required
    public boolean deleted; // optional
    private byte __isset_bitfield = 0;
    private _Fields optionals[] = {_Fields.DELETED};

    public RDFData() {
    }

    public RDFData(
            String subj,
            String subjType,
            String predicate,
            String obj,
            String objType,
            long timestamp) {
        this();
        this.subj = subj;
        this.subjType = subjType;
        this.predicate = predicate;
        this.obj = obj;
        this.objType = objType;
        this.timestamp = timestamp;
        setTimestampIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public RDFData(RDFData other) {
        __isset_bitfield = other.__isset_bitfield;
        if (other.isSetSubj()) {
            this.subj = other.subj;
        }
        if (other.isSetSubjType()) {
            this.subjType = other.subjType;
        }
        if (other.isSetPredicate()) {
            this.predicate = other.predicate;
        }
        if (other.isSetObj()) {
            this.obj = other.obj;
        }
        if (other.isSetObjType()) {
            this.objType = other.objType;
        }
        this.timestamp = other.timestamp;
        this.deleted = other.deleted;
    }

    public RDFData deepCopy() {
        return new RDFData(this);
    }

    @Override
    public void clear() {
        this.subj = null;
        this.subjType = null;
        this.predicate = null;
        this.obj = null;
        this.objType = null;
        setTimestampIsSet(false);
        this.timestamp = 0;
        setDeletedIsSet(false);
        this.deleted = false;
    }

    public String getSubj() {
        return this.subj;
    }

    public RDFData setSubj(String subj) {
        this.subj = subj;
        return this;
    }

    public void unsetSubj() {
        this.subj = null;
    }

    /**
     * Returns true if field subj is set (has been assigned a value) and false otherwise
     */
    public boolean isSetSubj() {
        return this.subj != null;
    }

    public void setSubjIsSet(boolean value) {
        if (!value) {
            this.subj = null;
        }
    }

    public String getSubjType() {
        return this.subjType;
    }

    public RDFData setSubjType(String subjType) {
        this.subjType = subjType;
        return this;
    }

    public void unsetSubjType() {
        this.subjType = null;
    }

    /**
     * Returns true if field subjType is set (has been assigned a value) and false otherwise
     */
    public boolean isSetSubjType() {
        return this.subjType != null;
    }

    public void setSubjTypeIsSet(boolean value) {
        if (!value) {
            this.subjType = null;
        }
    }

    public String getPredicate() {
        return this.predicate;
    }

    public RDFData setPredicate(String predicate) {
        this.predicate = predicate;
        return this;
    }

    public void unsetPredicate() {
        this.predicate = null;
    }

    /**
     * Returns true if field predicate is set (has been assigned a value) and false otherwise
     */
    public boolean isSetPredicate() {
        return this.predicate != null;
    }

    public void setPredicateIsSet(boolean value) {
        if (!value) {
            this.predicate = null;
        }
    }

    public String getObj() {
        return this.obj;
    }

    public RDFData setObj(String obj) {
        this.obj = obj;
        return this;
    }

    public void unsetObj() {
        this.obj = null;
    }

    /**
     * Returns true if field obj is set (has been assigned a value) and false otherwise
     */
    public boolean isSetObj() {
        return this.obj != null;
    }

    public void setObjIsSet(boolean value) {
        if (!value) {
            this.obj = null;
        }
    }

    public String getObjType() {
        return this.objType;
    }

    public RDFData setObjType(String objType) {
        this.objType = objType;
        return this;
    }

    public void unsetObjType() {
        this.objType = null;
    }

    /**
     * Returns true if field objType is set (has been assigned a value) and false otherwise
     */
    public boolean isSetObjType() {
        return this.objType != null;
    }

    public void setObjTypeIsSet(boolean value) {
        if (!value) {
            this.objType = null;
        }
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public RDFData setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        setTimestampIsSet(true);
        return this;
    }

    public void unsetTimestamp() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
    }

    /**
     * Returns true if field timestamp is set (has been assigned a value) and false otherwise
     */
    public boolean isSetTimestamp() {
        return EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
    }

    public void setTimestampIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public RDFData setDeleted(boolean deleted) {
        this.deleted = deleted;
        setDeletedIsSet(true);
        return this;
    }

    public void unsetDeleted() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DELETED_ISSET_ID);
    }

    /**
     * Returns true if field deleted is set (has been assigned a value) and false otherwise
     */
    public boolean isSetDeleted() {
        return EncodingUtils.testBit(__isset_bitfield, __DELETED_ISSET_ID);
    }

    public void setDeletedIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DELETED_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case SUBJ:
                if (value == null) {
                    unsetSubj();
                } else {
                    setSubj((String) value);
                }
                break;

            case SUBJ_TYPE:
                if (value == null) {
                    unsetSubjType();
                } else {
                    setSubjType((String) value);
                }
                break;

            case PREDICATE:
                if (value == null) {
                    unsetPredicate();
                } else {
                    setPredicate((String) value);
                }
                break;

            case OBJ:
                if (value == null) {
                    unsetObj();
                } else {
                    setObj((String) value);
                }
                break;

            case OBJ_TYPE:
                if (value == null) {
                    unsetObjType();
                } else {
                    setObjType((String) value);
                }
                break;

            case TIMESTAMP:
                if (value == null) {
                    unsetTimestamp();
                } else {
                    setTimestamp((Long) value);
                }
                break;

            case DELETED:
                if (value == null) {
                    unsetDeleted();
                } else {
                    setDeleted((Boolean) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case SUBJ:
                return getSubj();

            case SUBJ_TYPE:
                return getSubjType();

            case PREDICATE:
                return getPredicate();

            case OBJ:
                return getObj();

            case OBJ_TYPE:
                return getObjType();

            case TIMESTAMP:
                return Long.valueOf(getTimestamp());

            case DELETED:
                return Boolean.valueOf(isDeleted());

        }
        throw new IllegalStateException();
    }

    /**
     * Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
     */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case SUBJ:
                return isSetSubj();
            case SUBJ_TYPE:
                return isSetSubjType();
            case PREDICATE:
                return isSetPredicate();
            case OBJ:
                return isSetObj();
            case OBJ_TYPE:
                return isSetObjType();
            case TIMESTAMP:
                return isSetTimestamp();
            case DELETED:
                return isSetDeleted();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof RDFData)
            return this.equals((RDFData) that);
        return false;
    }

    public boolean equals(RDFData that) {
        if (that == null)
            return false;

        boolean this_present_subj = true && this.isSetSubj();
        boolean that_present_subj = true && that.isSetSubj();
        if (this_present_subj || that_present_subj) {
            if (!(this_present_subj && that_present_subj))
                return false;
            if (!this.subj.equals(that.subj))
                return false;
        }

        boolean this_present_subjType = true && this.isSetSubjType();
        boolean that_present_subjType = true && that.isSetSubjType();
        if (this_present_subjType || that_present_subjType) {
            if (!(this_present_subjType && that_present_subjType))
                return false;
            if (!this.subjType.equals(that.subjType))
                return false;
        }

        boolean this_present_predicate = true && this.isSetPredicate();
        boolean that_present_predicate = true && that.isSetPredicate();
        if (this_present_predicate || that_present_predicate) {
            if (!(this_present_predicate && that_present_predicate))
                return false;
            if (!this.predicate.equals(that.predicate))
                return false;
        }

        boolean this_present_obj = true && this.isSetObj();
        boolean that_present_obj = true && that.isSetObj();
        if (this_present_obj || that_present_obj) {
            if (!(this_present_obj && that_present_obj))
                return false;
            if (!this.obj.equals(that.obj))
                return false;
        }

        boolean this_present_objType = true && this.isSetObjType();
        boolean that_present_objType = true && that.isSetObjType();
        if (this_present_objType || that_present_objType) {
            if (!(this_present_objType && that_present_objType))
                return false;
            if (!this.objType.equals(that.objType))
                return false;
        }

        boolean this_present_timestamp = true;
        boolean that_present_timestamp = true;
        if (this_present_timestamp || that_present_timestamp) {
            if (!(this_present_timestamp && that_present_timestamp))
                return false;
            if (this.timestamp != that.timestamp)
                return false;
        }

        boolean this_present_deleted = true && this.isSetDeleted();
        boolean that_present_deleted = true && that.isSetDeleted();
        if (this_present_deleted || that_present_deleted) {
            if (!(this_present_deleted && that_present_deleted))
                return false;
            if (this.deleted != that.deleted)
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        boolean present_subj = true && (isSetSubj());
        builder.append(present_subj);
        if (present_subj)
            builder.append(subj);

        boolean present_subjType = true && (isSetSubjType());
        builder.append(present_subjType);
        if (present_subjType)
            builder.append(subjType);

        boolean present_predicate = true && (isSetPredicate());
        builder.append(present_predicate);
        if (present_predicate)
            builder.append(predicate);

        boolean present_obj = true && (isSetObj());
        builder.append(present_obj);
        if (present_obj)
            builder.append(obj);

        boolean present_objType = true && (isSetObjType());
        builder.append(present_objType);
        if (present_objType)
            builder.append(objType);

        boolean present_timestamp = true;
        builder.append(present_timestamp);
        if (present_timestamp)
            builder.append(timestamp);

        boolean present_deleted = true && (isSetDeleted());
        builder.append(present_deleted);
        if (present_deleted)
            builder.append(deleted);

        return builder.toHashCode();
    }

    public int compareTo(RDFData other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;
        RDFData typedOther = (RDFData) other;

        lastComparison = Boolean.valueOf(isSetSubj()).compareTo(typedOther.isSetSubj());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSubj()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subj, typedOther.subj);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetSubjType()).compareTo(typedOther.isSetSubjType());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSubjType()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subjType, typedOther.subjType);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPredicate()).compareTo(typedOther.isSetPredicate());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPredicate()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.predicate, typedOther.predicate);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetObj()).compareTo(typedOther.isSetObj());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetObj()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.obj, typedOther.obj);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetObjType()).compareTo(typedOther.isSetObjType());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetObjType()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.objType, typedOther.objType);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(typedOther.isSetTimestamp());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTimestamp()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, typedOther.timestamp);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDeleted()).compareTo(typedOther.isSetDeleted());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDeleted()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deleted, typedOther.deleted);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
        schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
        schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RDFData(");
        boolean first = true;

        sb.append("subj:");
        if (this.subj == null) {
            sb.append("null");
        } else {
            sb.append(this.subj);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("subjType:");
        if (this.subjType == null) {
            sb.append("null");
        } else {
            sb.append(this.subjType);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("predicate:");
        if (this.predicate == null) {
            sb.append("null");
        } else {
            sb.append(this.predicate);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("obj:");
        if (this.obj == null) {
            sb.append("null");
        } else {
            sb.append(this.obj);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("objType:");
        if (this.objType == null) {
            sb.append("null");
        } else {
            sb.append(this.objType);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("timestamp:");
        sb.append(this.timestamp);
        first = false;
        if (isSetDeleted()) {
            if (!first) sb.append(", ");
            sb.append("deleted:");
            sb.append(this.deleted);
            first = false;
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        if (subj == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'subj' was not present! Struct: " + toString());
        }
        if (subjType == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'subjType' was not present! Struct: " + toString());
        }
        if (predicate == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'predicate' was not present! Struct: " + toString());
        }
        if (obj == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'obj' was not present! Struct: " + toString());
        }
        if (objType == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'objType' was not present! Struct: " + toString());
        }
        // alas, we cannot check 'timestamp' because it's a primitive and you chose the non-beans generator.
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        try {
            // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
            __isset_bitfield = 0;
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    /**
     * The set of fields this struct contains, along with convenience methods for finding and manipulating them.
     */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        SUBJ((short) 1, "subj"),
        SUBJ_TYPE((short) 2, "subjType"),
        PREDICATE((short) 3, "predicate"),
        OBJ((short) 4, "obj"),
        OBJ_TYPE((short) 5, "objType"),
        TIMESTAMP((short) 6, "timestamp"),
        DELETED((short) 7, "deleted");

        private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

        static {
            for (_Fields field : EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        private final short _thriftId;
        private final String _fieldName;

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // SUBJ
                    return SUBJ;
                case 2: // SUBJ_TYPE
                    return SUBJ_TYPE;
                case 3: // PREDICATE
                    return PREDICATE;
                case 4: // OBJ
                    return OBJ;
                case 5: // OBJ_TYPE
                    return OBJ_TYPE;
                case 6: // TIMESTAMP
                    return TIMESTAMP;
                case 7: // DELETED
                    return DELETED;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        public static _Fields findByName(String name) {
            return byName.get(name);
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public String getFieldName() {
            return _fieldName;
        }

        _Fields(short thriftId, String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }
    }

    private static class RDFDataStandardSchemeFactory implements SchemeFactory {
        public RDFDataStandardScheme getScheme() {
            return new RDFDataStandardScheme();
        }
    }

    private static class RDFDataStandardScheme extends StandardScheme<RDFData> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, RDFData struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // SUBJ
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.subj = iprot.readString();
                            struct.setSubjIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // SUBJ_TYPE
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.subjType = iprot.readString();
                            struct.setSubjTypeIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // PREDICATE
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.predicate = iprot.readString();
                            struct.setPredicateIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 4: // OBJ
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.obj = iprot.readString();
                            struct.setObjIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 5: // OBJ_TYPE
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.objType = iprot.readString();
                            struct.setObjTypeIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 6: // TIMESTAMP
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.timestamp = iprot.readI64();
                            struct.setTimestampIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 7: // DELETED
                        if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
                            struct.deleted = iprot.readBool();
                            struct.setDeletedIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            if (!struct.isSetTimestamp()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'timestamp' was not found in serialized data! Struct: " + toString());
            }
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, RDFData struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.subj != null) {
                oprot.writeFieldBegin(SUBJ_FIELD_DESC);
                oprot.writeString(struct.subj);
                oprot.writeFieldEnd();
            }
            if (struct.subjType != null) {
                oprot.writeFieldBegin(SUBJ_TYPE_FIELD_DESC);
                oprot.writeString(struct.subjType);
                oprot.writeFieldEnd();
            }
            if (struct.predicate != null) {
                oprot.writeFieldBegin(PREDICATE_FIELD_DESC);
                oprot.writeString(struct.predicate);
                oprot.writeFieldEnd();
            }
            if (struct.obj != null) {
                oprot.writeFieldBegin(OBJ_FIELD_DESC);
                oprot.writeString(struct.obj);
                oprot.writeFieldEnd();
            }
            if (struct.objType != null) {
                oprot.writeFieldBegin(OBJ_TYPE_FIELD_DESC);
                oprot.writeString(struct.objType);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
            oprot.writeI64(struct.timestamp);
            oprot.writeFieldEnd();
            if (struct.isSetDeleted()) {
                oprot.writeFieldBegin(DELETED_FIELD_DESC);
                oprot.writeBool(struct.deleted);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class RDFDataTupleSchemeFactory implements SchemeFactory {
        public RDFDataTupleScheme getScheme() {
            return new RDFDataTupleScheme();
        }
    }

    private static class RDFDataTupleScheme extends TupleScheme<RDFData> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, RDFData struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            oprot.writeString(struct.subj);
            oprot.writeString(struct.subjType);
            oprot.writeString(struct.predicate);
            oprot.writeString(struct.obj);
            oprot.writeString(struct.objType);
            oprot.writeI64(struct.timestamp);
            BitSet optionals = new BitSet();
            if (struct.isSetDeleted()) {
                optionals.set(0);
            }
            oprot.writeBitSet(optionals, 1);
            if (struct.isSetDeleted()) {
                oprot.writeBool(struct.deleted);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, RDFData struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            struct.subj = iprot.readString();
            struct.setSubjIsSet(true);
            struct.subjType = iprot.readString();
            struct.setSubjTypeIsSet(true);
            struct.predicate = iprot.readString();
            struct.setPredicateIsSet(true);
            struct.obj = iprot.readString();
            struct.setObjIsSet(true);
            struct.objType = iprot.readString();
            struct.setObjTypeIsSet(true);
            struct.timestamp = iprot.readI64();
            struct.setTimestampIsSet(true);
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
                struct.deleted = iprot.readBool();
                struct.setDeletedIsSet(true);
            }
        }
    }

}

