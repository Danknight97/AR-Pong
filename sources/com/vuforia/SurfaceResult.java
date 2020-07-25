package com.vuforia;

public class SurfaceResult extends TrackableResult {
    private long swigCPtr;

    protected SurfaceResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.SurfaceResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(SurfaceResult obj) {
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
                VuforiaJNI.delete_SurfaceResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SurfaceResult) {
            return ((SurfaceResult) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.SurfaceResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new Surface(VuforiaJNI.SurfaceResult_getTrackable(this.swigCPtr, this), false);
    }
}
