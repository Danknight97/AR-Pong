package com.vuforia;

public class RotationalDeviceTracker extends DeviceTracker {
    private long swigCPtr;

    protected RotationalDeviceTracker(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.RotationalDeviceTracker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(RotationalDeviceTracker obj) {
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
                VuforiaJNI.delete_RotationalDeviceTracker(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof RotationalDeviceTracker) {
            return ((RotationalDeviceTracker) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.RotationalDeviceTracker_getClassType(), true);
    }

    public boolean recenter() {
        return VuforiaJNI.RotationalDeviceTracker_recenter(this.swigCPtr, this);
    }

    public boolean setPosePrediction(boolean enable) {
        return VuforiaJNI.RotationalDeviceTracker_setPosePrediction(this.swigCPtr, this, enable);
    }

    public boolean getPosePrediction() {
        return VuforiaJNI.RotationalDeviceTracker_getPosePrediction(this.swigCPtr, this);
    }

    public boolean setModelCorrection(TransformModel transformationmodel) {
        return VuforiaJNI.RotationalDeviceTracker_setModelCorrection(this.swigCPtr, this, TransformModel.getCPtr(transformationmodel), transformationmodel);
    }

    public TransformModel getModelCorrection() {
        long cPtr = VuforiaJNI.RotationalDeviceTracker_getModelCorrection(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        switch (new TransformModel(cPtr, false).getType()) {
            case 0:
                return new HeadTransformModel(cPtr, false);
            case 1:
                return new HandheldTransformModel(cPtr, false);
            default:
                return null;
        }
    }

    public HeadTransformModel getDefaultHeadModel() {
        long cPtr = VuforiaJNI.RotationalDeviceTracker_getDefaultHeadModel(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new HeadTransformModel(cPtr, false);
    }

    public HandheldTransformModel getDefaultHandheldModel() {
        long cPtr = VuforiaJNI.RotationalDeviceTracker_getDefaultHandheldModel(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new HandheldTransformModel(cPtr, false);
    }
}
