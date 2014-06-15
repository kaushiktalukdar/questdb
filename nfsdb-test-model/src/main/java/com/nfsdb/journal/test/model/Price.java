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

public class Price implements org.apache.thrift.TBase<Price, Price._Fields>, java.io.Serializable, Cloneable {
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        tmpMap.put(_Fields.SYM, new org.apache.thrift.meta_data.FieldMetaData("sym", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.PRICE, new org.apache.thrift.meta_data.FieldMetaData("price", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Price.class, metaDataMap);
    }

    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Price");
    private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short) 1);
    private static final org.apache.thrift.protocol.TField SYM_FIELD_DESC = new org.apache.thrift.protocol.TField("sym", org.apache.thrift.protocol.TType.STRING, (short) 2);
    private static final org.apache.thrift.protocol.TField PRICE_FIELD_DESC = new org.apache.thrift.protocol.TField("price", org.apache.thrift.protocol.TType.DOUBLE, (short) 3);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();

    static {
        schemes.put(StandardScheme.class, new PriceStandardSchemeFactory());
        schemes.put(TupleScheme.class, new PriceTupleSchemeFactory());
    }

    // isset id assignments
    private static final int __TIMESTAMP_ISSET_ID = 0;
    private static final int __PRICE_ISSET_ID = 1;
    public long timestamp; // required
    public String sym; // required
    public double price; // required
    private byte __isset_bitfield = 0;

    public Price() {
    }

    public Price(
            long timestamp,
            String sym,
            double price) {
        this();
        this.timestamp = timestamp;
        setTimestampIsSet(true);
        this.sym = sym;
        this.price = price;
        setPriceIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public Price(Price other) {
        __isset_bitfield = other.__isset_bitfield;
        this.timestamp = other.timestamp;
        if (other.isSetSym()) {
            this.sym = other.sym;
        }
        this.price = other.price;
    }

    public Price deepCopy() {
        return new Price(this);
    }

    @Override
    public void clear() {
        setTimestampIsSet(false);
        this.timestamp = 0;
        this.sym = null;
        setPriceIsSet(false);
        this.price = 0.0;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Price setTimestamp(long timestamp) {
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

    public String getSym() {
        return this.sym;
    }

    public Price setSym(String sym) {
        this.sym = sym;
        return this;
    }

    public void unsetSym() {
        this.sym = null;
    }

    /**
     * Returns true if field sym is set (has been assigned a value) and false otherwise
     */
    public boolean isSetSym() {
        return this.sym != null;
    }

    public void setSymIsSet(boolean value) {
        if (!value) {
            this.sym = null;
        }
    }

    public double getPrice() {
        return this.price;
    }

    public Price setPrice(double price) {
        this.price = price;
        setPriceIsSet(true);
        return this;
    }

    public void unsetPrice() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PRICE_ISSET_ID);
    }

    /**
     * Returns true if field price is set (has been assigned a value) and false otherwise
     */
    public boolean isSetPrice() {
        return EncodingUtils.testBit(__isset_bitfield, __PRICE_ISSET_ID);
    }

    public void setPriceIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PRICE_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case TIMESTAMP:
                if (value == null) {
                    unsetTimestamp();
                } else {
                    setTimestamp((Long) value);
                }
                break;

            case SYM:
                if (value == null) {
                    unsetSym();
                } else {
                    setSym((String) value);
                }
                break;

            case PRICE:
                if (value == null) {
                    unsetPrice();
                } else {
                    setPrice((Double) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case TIMESTAMP:
                return Long.valueOf(getTimestamp());

            case SYM:
                return getSym();

            case PRICE:
                return Double.valueOf(getPrice());

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
            case TIMESTAMP:
                return isSetTimestamp();
            case SYM:
                return isSetSym();
            case PRICE:
                return isSetPrice();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof Price)
            return this.equals((Price) that);
        return false;
    }

    public boolean equals(Price that) {
        if (that == null)
            return false;

        boolean this_present_timestamp = true;
        boolean that_present_timestamp = true;
        if (this_present_timestamp || that_present_timestamp) {
            if (!(this_present_timestamp && that_present_timestamp))
                return false;
            if (this.timestamp != that.timestamp)
                return false;
        }

        boolean this_present_sym = true && this.isSetSym();
        boolean that_present_sym = true && that.isSetSym();
        if (this_present_sym || that_present_sym) {
            if (!(this_present_sym && that_present_sym))
                return false;
            if (!this.sym.equals(that.sym))
                return false;
        }

        boolean this_present_price = true;
        boolean that_present_price = true;
        if (this_present_price || that_present_price) {
            if (!(this_present_price && that_present_price))
                return false;
            if (this.price != that.price)
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        boolean present_timestamp = true;
        builder.append(present_timestamp);
        if (present_timestamp)
            builder.append(timestamp);

        boolean present_sym = true && (isSetSym());
        builder.append(present_sym);
        if (present_sym)
            builder.append(sym);

        boolean present_price = true;
        builder.append(present_price);
        if (present_price)
            builder.append(price);

        return builder.toHashCode();
    }

    public int compareTo(Price other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;
        Price typedOther = (Price) other;

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
        lastComparison = Boolean.valueOf(isSetSym()).compareTo(typedOther.isSetSym());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSym()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sym, typedOther.sym);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPrice()).compareTo(typedOther.isSetPrice());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPrice()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.price, typedOther.price);
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
        StringBuilder sb = new StringBuilder("Price(");
        boolean first = true;

        sb.append("timestamp:");
        sb.append(this.timestamp);
        first = false;
        if (!first) sb.append(", ");
        sb.append("sym:");
        if (this.sym == null) {
            sb.append("null");
        } else {
            sb.append(this.sym);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("price:");
        sb.append(this.price);
        first = false;
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        // alas, we cannot check 'timestamp' because it's a primitive and you chose the non-beans generator.
        if (sym == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'sym' was not present! Struct: " + toString());
        }
        // alas, we cannot check 'price' because it's a primitive and you chose the non-beans generator.
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
        TIMESTAMP((short) 1, "timestamp"),
        SYM((short) 2, "sym"),
        PRICE((short) 3, "price");

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
                case 1: // TIMESTAMP
                    return TIMESTAMP;
                case 2: // SYM
                    return SYM;
                case 3: // PRICE
                    return PRICE;
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

    private static class PriceStandardSchemeFactory implements SchemeFactory {
        public PriceStandardScheme getScheme() {
            return new PriceStandardScheme();
        }
    }

    private static class PriceStandardScheme extends StandardScheme<Price> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, Price struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // TIMESTAMP
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.timestamp = iprot.readI64();
                            struct.setTimestampIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // SYM
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.sym = iprot.readString();
                            struct.setSymIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // PRICE
                        if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
                            struct.price = iprot.readDouble();
                            struct.setPriceIsSet(true);
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
            if (!struct.isSetPrice()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'price' was not found in serialized data! Struct: " + toString());
            }
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, Price struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
            oprot.writeI64(struct.timestamp);
            oprot.writeFieldEnd();
            if (struct.sym != null) {
                oprot.writeFieldBegin(SYM_FIELD_DESC);
                oprot.writeString(struct.sym);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldBegin(PRICE_FIELD_DESC);
            oprot.writeDouble(struct.price);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class PriceTupleSchemeFactory implements SchemeFactory {
        public PriceTupleScheme getScheme() {
            return new PriceTupleScheme();
        }
    }

    private static class PriceTupleScheme extends TupleScheme<Price> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, Price struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            oprot.writeI64(struct.timestamp);
            oprot.writeString(struct.sym);
            oprot.writeDouble(struct.price);
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, Price struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            struct.timestamp = iprot.readI64();
            struct.setTimestampIsSet(true);
            struct.sym = iprot.readString();
            struct.setSymIsSet(true);
            struct.price = iprot.readDouble();
            struct.setPriceIsSet(true);
        }
    }

}

