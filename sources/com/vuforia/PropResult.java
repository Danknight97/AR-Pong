package com.vuforia;

public class PropResult extends TrackableResult {
    private long swigCPtr;

    protected PropResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.PropResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(PropResult obj) {
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
                VuforiaJNI.delete_PropResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PropResult) {
            return ((PropResult) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.PropResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new Prop(VuforiaJNI.PropResult_getTrackable(this.swigCPtr, this), false);
    }
}
