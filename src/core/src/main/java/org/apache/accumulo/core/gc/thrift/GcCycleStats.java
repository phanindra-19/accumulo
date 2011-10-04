/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package org.apache.accumulo.core.gc.thrift;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GcCycleStats implements org.apache.thrift.TBase<GcCycleStats, GcCycleStats._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GcCycleStats");

  private static final org.apache.thrift.protocol.TField STARTED_FIELD_DESC = new org.apache.thrift.protocol.TField("started", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField FINISHED_FIELD_DESC = new org.apache.thrift.protocol.TField("finished", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField CANDIDATES_FIELD_DESC = new org.apache.thrift.protocol.TField("candidates", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField IN_USE_FIELD_DESC = new org.apache.thrift.protocol.TField("inUse", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField DELETED_FIELD_DESC = new org.apache.thrift.protocol.TField("deleted", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField ERRORS_FIELD_DESC = new org.apache.thrift.protocol.TField("errors", org.apache.thrift.protocol.TType.I64, (short)6);

  public long started;
  public long finished;
  public long candidates;
  public long inUse;
  public long deleted;
  public long errors;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STARTED((short)1, "started"),
    FINISHED((short)2, "finished"),
    CANDIDATES((short)3, "candidates"),
    IN_USE((short)4, "inUse"),
    DELETED((short)5, "deleted"),
    ERRORS((short)6, "errors");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // STARTED
          return STARTED;
        case 2: // FINISHED
          return FINISHED;
        case 3: // CANDIDATES
          return CANDIDATES;
        case 4: // IN_USE
          return IN_USE;
        case 5: // DELETED
          return DELETED;
        case 6: // ERRORS
          return ERRORS;
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

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __STARTED_ISSET_ID = 0;
  private static final int __FINISHED_ISSET_ID = 1;
  private static final int __CANDIDATES_ISSET_ID = 2;
  private static final int __INUSE_ISSET_ID = 3;
  private static final int __DELETED_ISSET_ID = 4;
  private static final int __ERRORS_ISSET_ID = 5;
  private BitSet __isset_bit_vector = new BitSet(6);

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STARTED, new org.apache.thrift.meta_data.FieldMetaData("started", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FINISHED, new org.apache.thrift.meta_data.FieldMetaData("finished", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CANDIDATES, new org.apache.thrift.meta_data.FieldMetaData("candidates", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.IN_USE, new org.apache.thrift.meta_data.FieldMetaData("inUse", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.DELETED, new org.apache.thrift.meta_data.FieldMetaData("deleted", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ERRORS, new org.apache.thrift.meta_data.FieldMetaData("errors", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GcCycleStats.class, metaDataMap);
  }

  public GcCycleStats() {
  }

  public GcCycleStats(
    long started,
    long finished,
    long candidates,
    long inUse,
    long deleted,
    long errors)
  {
    this();
    this.started = started;
    setStartedIsSet(true);
    this.finished = finished;
    setFinishedIsSet(true);
    this.candidates = candidates;
    setCandidatesIsSet(true);
    this.inUse = inUse;
    setInUseIsSet(true);
    this.deleted = deleted;
    setDeletedIsSet(true);
    this.errors = errors;
    setErrorsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GcCycleStats(GcCycleStats other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.started = other.started;
    this.finished = other.finished;
    this.candidates = other.candidates;
    this.inUse = other.inUse;
    this.deleted = other.deleted;
    this.errors = other.errors;
  }

  public GcCycleStats deepCopy() {
    return new GcCycleStats(this);
  }

  @Override
  public void clear() {
    setStartedIsSet(false);
    this.started = 0;
    setFinishedIsSet(false);
    this.finished = 0;
    setCandidatesIsSet(false);
    this.candidates = 0;
    setInUseIsSet(false);
    this.inUse = 0;
    setDeletedIsSet(false);
    this.deleted = 0;
    setErrorsIsSet(false);
    this.errors = 0;
  }

  public long getStarted() {
    return this.started;
  }

  public GcCycleStats setStarted(long started) {
    this.started = started;
    setStartedIsSet(true);
    return this;
  }

  public void unsetStarted() {
    __isset_bit_vector.clear(__STARTED_ISSET_ID);
  }

  /** Returns true if field started is set (has been assigned a value) and false otherwise */
  public boolean isSetStarted() {
    return __isset_bit_vector.get(__STARTED_ISSET_ID);
  }

  public void setStartedIsSet(boolean value) {
    __isset_bit_vector.set(__STARTED_ISSET_ID, value);
  }

  public long getFinished() {
    return this.finished;
  }

  public GcCycleStats setFinished(long finished) {
    this.finished = finished;
    setFinishedIsSet(true);
    return this;
  }

  public void unsetFinished() {
    __isset_bit_vector.clear(__FINISHED_ISSET_ID);
  }

  /** Returns true if field finished is set (has been assigned a value) and false otherwise */
  public boolean isSetFinished() {
    return __isset_bit_vector.get(__FINISHED_ISSET_ID);
  }

  public void setFinishedIsSet(boolean value) {
    __isset_bit_vector.set(__FINISHED_ISSET_ID, value);
  }

  public long getCandidates() {
    return this.candidates;
  }

  public GcCycleStats setCandidates(long candidates) {
    this.candidates = candidates;
    setCandidatesIsSet(true);
    return this;
  }

  public void unsetCandidates() {
    __isset_bit_vector.clear(__CANDIDATES_ISSET_ID);
  }

  /** Returns true if field candidates is set (has been assigned a value) and false otherwise */
  public boolean isSetCandidates() {
    return __isset_bit_vector.get(__CANDIDATES_ISSET_ID);
  }

  public void setCandidatesIsSet(boolean value) {
    __isset_bit_vector.set(__CANDIDATES_ISSET_ID, value);
  }

  public long getInUse() {
    return this.inUse;
  }

  public GcCycleStats setInUse(long inUse) {
    this.inUse = inUse;
    setInUseIsSet(true);
    return this;
  }

  public void unsetInUse() {
    __isset_bit_vector.clear(__INUSE_ISSET_ID);
  }

  /** Returns true if field inUse is set (has been assigned a value) and false otherwise */
  public boolean isSetInUse() {
    return __isset_bit_vector.get(__INUSE_ISSET_ID);
  }

  public void setInUseIsSet(boolean value) {
    __isset_bit_vector.set(__INUSE_ISSET_ID, value);
  }

  public long getDeleted() {
    return this.deleted;
  }

  public GcCycleStats setDeleted(long deleted) {
    this.deleted = deleted;
    setDeletedIsSet(true);
    return this;
  }

  public void unsetDeleted() {
    __isset_bit_vector.clear(__DELETED_ISSET_ID);
  }

  /** Returns true if field deleted is set (has been assigned a value) and false otherwise */
  public boolean isSetDeleted() {
    return __isset_bit_vector.get(__DELETED_ISSET_ID);
  }

  public void setDeletedIsSet(boolean value) {
    __isset_bit_vector.set(__DELETED_ISSET_ID, value);
  }

  public long getErrors() {
    return this.errors;
  }

  public GcCycleStats setErrors(long errors) {
    this.errors = errors;
    setErrorsIsSet(true);
    return this;
  }

  public void unsetErrors() {
    __isset_bit_vector.clear(__ERRORS_ISSET_ID);
  }

  /** Returns true if field errors is set (has been assigned a value) and false otherwise */
  public boolean isSetErrors() {
    return __isset_bit_vector.get(__ERRORS_ISSET_ID);
  }

  public void setErrorsIsSet(boolean value) {
    __isset_bit_vector.set(__ERRORS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case STARTED:
      if (value == null) {
        unsetStarted();
      } else {
        setStarted((Long)value);
      }
      break;

    case FINISHED:
      if (value == null) {
        unsetFinished();
      } else {
        setFinished((Long)value);
      }
      break;

    case CANDIDATES:
      if (value == null) {
        unsetCandidates();
      } else {
        setCandidates((Long)value);
      }
      break;

    case IN_USE:
      if (value == null) {
        unsetInUse();
      } else {
        setInUse((Long)value);
      }
      break;

    case DELETED:
      if (value == null) {
        unsetDeleted();
      } else {
        setDeleted((Long)value);
      }
      break;

    case ERRORS:
      if (value == null) {
        unsetErrors();
      } else {
        setErrors((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STARTED:
      return new Long(getStarted());

    case FINISHED:
      return new Long(getFinished());

    case CANDIDATES:
      return new Long(getCandidates());

    case IN_USE:
      return new Long(getInUse());

    case DELETED:
      return new Long(getDeleted());

    case ERRORS:
      return new Long(getErrors());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STARTED:
      return isSetStarted();
    case FINISHED:
      return isSetFinished();
    case CANDIDATES:
      return isSetCandidates();
    case IN_USE:
      return isSetInUse();
    case DELETED:
      return isSetDeleted();
    case ERRORS:
      return isSetErrors();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GcCycleStats)
      return this.equals((GcCycleStats)that);
    return false;
  }

  public boolean equals(GcCycleStats that) {
    if (that == null)
      return false;

    boolean this_present_started = true;
    boolean that_present_started = true;
    if (this_present_started || that_present_started) {
      if (!(this_present_started && that_present_started))
        return false;
      if (this.started != that.started)
        return false;
    }

    boolean this_present_finished = true;
    boolean that_present_finished = true;
    if (this_present_finished || that_present_finished) {
      if (!(this_present_finished && that_present_finished))
        return false;
      if (this.finished != that.finished)
        return false;
    }

    boolean this_present_candidates = true;
    boolean that_present_candidates = true;
    if (this_present_candidates || that_present_candidates) {
      if (!(this_present_candidates && that_present_candidates))
        return false;
      if (this.candidates != that.candidates)
        return false;
    }

    boolean this_present_inUse = true;
    boolean that_present_inUse = true;
    if (this_present_inUse || that_present_inUse) {
      if (!(this_present_inUse && that_present_inUse))
        return false;
      if (this.inUse != that.inUse)
        return false;
    }

    boolean this_present_deleted = true;
    boolean that_present_deleted = true;
    if (this_present_deleted || that_present_deleted) {
      if (!(this_present_deleted && that_present_deleted))
        return false;
      if (this.deleted != that.deleted)
        return false;
    }

    boolean this_present_errors = true;
    boolean that_present_errors = true;
    if (this_present_errors || that_present_errors) {
      if (!(this_present_errors && that_present_errors))
        return false;
      if (this.errors != that.errors)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(GcCycleStats other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    GcCycleStats typedOther = (GcCycleStats)other;

    lastComparison = Boolean.valueOf(isSetStarted()).compareTo(typedOther.isSetStarted());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStarted()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.started, typedOther.started);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFinished()).compareTo(typedOther.isSetFinished());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFinished()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.finished, typedOther.finished);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCandidates()).compareTo(typedOther.isSetCandidates());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCandidates()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.candidates, typedOther.candidates);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInUse()).compareTo(typedOther.isSetInUse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInUse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.inUse, typedOther.inUse);
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
    lastComparison = Boolean.valueOf(isSetErrors()).compareTo(typedOther.isSetErrors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errors, typedOther.errors);
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
    org.apache.thrift.protocol.TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == org.apache.thrift.protocol.TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // STARTED
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.started = iprot.readI64();
            setStartedIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // FINISHED
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.finished = iprot.readI64();
            setFinishedIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // CANDIDATES
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.candidates = iprot.readI64();
            setCandidatesIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // IN_USE
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.inUse = iprot.readI64();
            setInUseIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // DELETED
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.deleted = iprot.readI64();
            setDeletedIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 6: // ERRORS
          if (field.type == org.apache.thrift.protocol.TType.I64) {
            this.errors = iprot.readI64();
            setErrorsIsSet(true);
          } else { 
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(STARTED_FIELD_DESC);
    oprot.writeI64(this.started);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(FINISHED_FIELD_DESC);
    oprot.writeI64(this.finished);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(CANDIDATES_FIELD_DESC);
    oprot.writeI64(this.candidates);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(IN_USE_FIELD_DESC);
    oprot.writeI64(this.inUse);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(DELETED_FIELD_DESC);
    oprot.writeI64(this.deleted);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(ERRORS_FIELD_DESC);
    oprot.writeI64(this.errors);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GcCycleStats(");
    boolean first = true;

    sb.append("started:");
    sb.append(this.started);
    first = false;
    if (!first) sb.append(", ");
    sb.append("finished:");
    sb.append(this.finished);
    first = false;
    if (!first) sb.append(", ");
    sb.append("candidates:");
    sb.append(this.candidates);
    first = false;
    if (!first) sb.append(", ");
    sb.append("inUse:");
    sb.append(this.inUse);
    first = false;
    if (!first) sb.append(", ");
    sb.append("deleted:");
    sb.append(this.deleted);
    first = false;
    if (!first) sb.append(", ");
    sb.append("errors:");
    sb.append(this.errors);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

}

