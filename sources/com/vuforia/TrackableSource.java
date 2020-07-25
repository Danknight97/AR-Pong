package com.vuforia;

public class TrackableSource {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TrackableSource(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(TrackableSource obj) {
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
                VuforiaJNI.delete_TrackableSource(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TrackableSource) {
            return ((TrackableSource) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public TrackableSource() {
        this(VuforiaJNI.new_TrackableSource(), true);
    }
}
