package com.vuforia;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class InstanceId {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class ID_DATA_TYPE {
        public static final int BYTES = 0;
        public static final int NUMERIC = 2;
        public static final int STRING = 1;
    }

    protected InstanceId(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(InstanceId obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_InstanceId(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof InstanceId) {
            return ((InstanceId) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public ByteBuffer getBuffer() {
        return VuforiaJNI.InstanceId_getBuffer(this.swigCPtr, this);
    }

    public long getLength() {
        return VuforiaJNI.InstanceId_getLength(this.swigCPtr, this);
    }

    public BigInteger getNumericValue() {
        return VuforiaJNI.InstanceId_getNumericValue(this.swigCPtr, this);
    }

    public int getDataType() {
        return VuforiaJNI.InstanceId_getDataType(this.swigCPtr, this);
    }
}
