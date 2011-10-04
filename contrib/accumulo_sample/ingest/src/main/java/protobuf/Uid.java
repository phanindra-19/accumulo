// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Uid.proto

package protobuf;

public final class Uid {
  private Uid() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class List extends
      com.google.protobuf.GeneratedMessage {
    // Use List.newBuilder() to construct.
    private List() {
      initFields();
    }
    private List(boolean noInit) {}
    
    private static final List defaultInstance;
    public static List getDefaultInstance() {
      return defaultInstance;
    }
    
    public List getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protobuf.Uid.internal_static_protobuf_List_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.Uid.internal_static_protobuf_List_fieldAccessorTable;
    }
    
    // required bool IGNORE = 1;
    public static final int IGNORE_FIELD_NUMBER = 1;
    private boolean hasIGNORE;
    private boolean iGNORE_ = false;
    public boolean hasIGNORE() { return hasIGNORE; }
    public boolean getIGNORE() { return iGNORE_; }
    
    // required uint64 COUNT = 2;
    public static final int COUNT_FIELD_NUMBER = 2;
    private boolean hasCOUNT;
    private long cOUNT_ = 0L;
    public boolean hasCOUNT() { return hasCOUNT; }
    public long getCOUNT() { return cOUNT_; }
    
    // repeated string UID = 3;
    public static final int UID_FIELD_NUMBER = 3;
    private java.util.List<java.lang.String> uID_ =
      java.util.Collections.emptyList();
    public java.util.List<java.lang.String> getUIDList() {
      return uID_;
    }
    public int getUIDCount() { return uID_.size(); }
    public java.lang.String getUID(int index) {
      return uID_.get(index);
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      if (!hasIGNORE) return false;
      if (!hasCOUNT) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (hasIGNORE()) {
        output.writeBool(1, getIGNORE());
      }
      if (hasCOUNT()) {
        output.writeUInt64(2, getCOUNT());
      }
      for (java.lang.String element : getUIDList()) {
        output.writeString(3, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasIGNORE()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, getIGNORE());
      }
      if (hasCOUNT()) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(2, getCOUNT());
      }
      {
        int dataSize = 0;
        for (java.lang.String element : getUIDList()) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeStringSizeNoTag(element);
        }
        size += dataSize;
        size += 1 * getUIDList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static protobuf.Uid.List parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.Uid.List parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.Uid.List parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static protobuf.Uid.List parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.Uid.List parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.Uid.List parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static protobuf.Uid.List parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.Uid.List parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static protobuf.Uid.List parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static protobuf.Uid.List parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(protobuf.Uid.List prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private protobuf.Uid.List result;
      
      // Construct using protobuf.Uid.List.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new protobuf.Uid.List();
        return builder;
      }
      
      protected protobuf.Uid.List internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new protobuf.Uid.List();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.Uid.List.getDescriptor();
      }
      
      public protobuf.Uid.List getDefaultInstanceForType() {
        return protobuf.Uid.List.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public protobuf.Uid.List build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private protobuf.Uid.List buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public protobuf.Uid.List buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.uID_ != java.util.Collections.EMPTY_LIST) {
          result.uID_ =
            java.util.Collections.unmodifiableList(result.uID_);
        }
        protobuf.Uid.List returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.Uid.List) {
          return mergeFrom((protobuf.Uid.List)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(protobuf.Uid.List other) {
        if (other == protobuf.Uid.List.getDefaultInstance()) return this;
        if (other.hasIGNORE()) {
          setIGNORE(other.getIGNORE());
        }
        if (other.hasCOUNT()) {
          setCOUNT(other.getCOUNT());
        }
        if (!other.uID_.isEmpty()) {
          if (result.uID_.isEmpty()) {
            result.uID_ = new java.util.ArrayList<java.lang.String>();
          }
          result.uID_.addAll(other.uID_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setIGNORE(input.readBool());
              break;
            }
            case 16: {
              setCOUNT(input.readUInt64());
              break;
            }
            case 26: {
              addUID(input.readString());
              break;
            }
          }
        }
      }
      
      
      // required bool IGNORE = 1;
      public boolean hasIGNORE() {
        return result.hasIGNORE();
      }
      public boolean getIGNORE() {
        return result.getIGNORE();
      }
      public Builder setIGNORE(boolean value) {
        result.hasIGNORE = true;
        result.iGNORE_ = value;
        return this;
      }
      public Builder clearIGNORE() {
        result.hasIGNORE = false;
        result.iGNORE_ = false;
        return this;
      }
      
      // required uint64 COUNT = 2;
      public boolean hasCOUNT() {
        return result.hasCOUNT();
      }
      public long getCOUNT() {
        return result.getCOUNT();
      }
      public Builder setCOUNT(long value) {
        result.hasCOUNT = true;
        result.cOUNT_ = value;
        return this;
      }
      public Builder clearCOUNT() {
        result.hasCOUNT = false;
        result.cOUNT_ = 0L;
        return this;
      }
      
      // repeated string UID = 3;
      public java.util.List<java.lang.String> getUIDList() {
        return java.util.Collections.unmodifiableList(result.uID_);
      }
      public int getUIDCount() {
        return result.getUIDCount();
      }
      public java.lang.String getUID(int index) {
        return result.getUID(index);
      }
      public Builder setUID(int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  result.uID_.set(index, value);
        return this;
      }
      public Builder addUID(java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  if (result.uID_.isEmpty()) {
          result.uID_ = new java.util.ArrayList<java.lang.String>();
        }
        result.uID_.add(value);
        return this;
      }
      public Builder addAllUID(
          java.lang.Iterable<? extends java.lang.String> values) {
        if (result.uID_.isEmpty()) {
          result.uID_ = new java.util.ArrayList<java.lang.String>();
        }
        super.addAll(values, result.uID_);
        return this;
      }
      public Builder clearUID() {
        result.uID_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:protobuf.List)
    }
    
    static {
      defaultInstance = new List(true);
      protobuf.Uid.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:protobuf.List)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_List_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_protobuf_List_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tUid.proto\022\010protobuf\"2\n\004List\022\016\n\006IGNORE\030" +
      "\001 \002(\010\022\r\n\005COUNT\030\002 \002(\004\022\013\n\003UID\030\003 \003(\tB\014\n\010pro" +
      "tobufH\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_protobuf_List_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_protobuf_List_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_protobuf_List_descriptor,
              new java.lang.String[] { "IGNORE", "COUNT", "UID", },
              protobuf.Uid.List.class,
              protobuf.Uid.List.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}
