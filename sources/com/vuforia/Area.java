package com.vuforia;

public class Area {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class TYPE {
        public static final int INVALID = 2;
        public static final int RECTANGLE = 0;
        public static final int RECTANGLE_INT = 1;

        private TYPE() {
        }
    }

    protected Area(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Area obj) {
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
                VuforiaJNI.delete_Area(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Area) {
            return ((Area) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public int getType() {
        return VuforiaJNI.Area_getType(this.swigCPtr, this);
    }
}
