package com.vuforia;

public class MultiTargetResult extends ObjectTargetResult {
    private long swigCPtr;

    protected MultiTargetResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.MultiTargetResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(MultiTargetResult obj) {
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
                VuforiaJNI.delete_MultiTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MultiTargetResult) {
            return ((MultiTargetResult) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.MultiTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new MultiTarget(VuforiaJNI.MultiTargetResult_getTrackable(this.swigCPtr, this), false);
    }

    public int getNumPartResults() {
        return VuforiaJNI.MultiTargetResult_getNumPartResults(this.swigCPtr, this);
    }

    public TrackableResult getPartResult(int idx) {
        long cPtr = VuforiaJNI.MultiTargetResult_getPartResult__SWIG_0(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        TrackableResult tmp = new TrackableResult(cPtr, false);
        if (tmp.isOfType(ImageTargetResult.getClassType())) {
            return new ImageTargetResult(cPtr, false);
        }
        if (tmp.isOfType(CylinderTargetResult.getClassType())) {
            return new CylinderTargetResult(cPtr, false);
        }
        if (tmp.isOfType(getClassType())) {
            return new MultiTargetResult(cPtr, false);
        }
        if (tmp.isOfType(VuMarkTargetResult.getClassType())) {
            return new VuMarkTargetResult(cPtr, false);
        }
        if (tmp.isOfType(ObjectTargetResult.getClassType())) {
            return new ObjectTargetResult(cPtr, false);
        }
        if (tmp.isOfType(WordResult.getClassType())) {
            return new WordResult(cPtr, false);
        }
        if (tmp.isOfType(SurfaceResult.getClassType())) {
            return new SurfaceResult(cPtr, false);
        }
        if (tmp.isOfType(PropResult.getClassType())) {
            return new PropResult(cPtr, false);
        }
        if (tmp.isOfType(DeviceTrackableResult.getClassType())) {
            return new DeviceTrackableResult(cPtr, false);
        }
        return null;
    }

    public TrackableResult getPartResult(String name) {
        long cPtr = VuforiaJNI.MultiTargetResult_getPartResult__SWIG_1(this.swigCPtr, this, name);
        if (cPtr == 0) {
            return null;
        }
        TrackableResult tmp = new TrackableResult(cPtr, false);
        if (tmp.isOfType(ImageTargetResult.getClassType())) {
            return new ImageTargetResult(cPtr, false);
        }
        if (tmp.isOfType(CylinderTargetResult.getClassType())) {
            return new CylinderTargetResult(cPtr, false);
        }
        if (tmp.isOfType(getClassType())) {
            return new MultiTargetResult(cPtr, false);
        }
        if (tmp.isOfType(VuMarkTargetResult.getClassType())) {
            return new VuMarkTargetResult(cPtr, false);
        }
        if (tmp.isOfType(ObjectTargetResult.getClassType())) {
            return new ObjectTargetResult(cPtr, false);
        }
        if (tmp.isOfType(WordResult.getClassType())) {
            return new WordResult(cPtr, false);
        }
        if (tmp.isOfType(SurfaceResult.getClassType())) {
            return new SurfaceResult(cPtr, false);
        }
        if (tmp.isOfType(PropResult.getClassType())) {
            return new PropResult(cPtr, false);
        }
        if (tmp.isOfType(DeviceTrackableResult.getClassType())) {
            return new DeviceTrackableResult(cPtr, false);
        }
        return null;
    }
}
