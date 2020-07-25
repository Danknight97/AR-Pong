package com.vuforia;

public class ObjectTargetResult extends TrackableResult {
    private long swigCPtr;

    protected ObjectTargetResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ObjectTargetResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ObjectTargetResult obj) {
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
                VuforiaJNI.delete_ObjectTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ObjectTargetResult) {
            return ((ObjectTargetResult) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ObjectTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new ObjectTarget(VuforiaJNI.ObjectTargetResult_getTrackable(this.swigCPtr, this), false);
    }
}
