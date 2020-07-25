package com.vuforia;

public class CustomViewerParameters extends ViewerParameters {
    private long swigCPtr;

    protected CustomViewerParameters(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.CustomViewerParameters_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(CustomViewerParameters obj) {
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
                VuforiaJNI.delete_CustomViewerParameters(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CustomViewerParameters) {
            return ((CustomViewerParameters) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public CustomViewerParameters(float version, String name, String manufacturer) {
        this(VuforiaJNI.new_CustomViewerParameters__SWIG_0(version, name, manufacturer), true);
    }

    public CustomViewerParameters(CustomViewerParameters arg0) {
        this(VuforiaJNI.new_CustomViewerParameters__SWIG_1(getCPtr(arg0), arg0), true);
    }

    public void setButtonType(int val) {
        VuforiaJNI.CustomViewerParameters_setButtonType(this.swigCPtr, this, val);
    }

    public void setScreenToLensDistance(float val) {
        VuforiaJNI.CustomViewerParameters_setScreenToLensDistance(this.swigCPtr, this, val);
    }

    public void setInterLensDistance(float val) {
        VuforiaJNI.CustomViewerParameters_setInterLensDistance(this.swigCPtr, this, val);
    }

    public void setTrayAlignment(int val) {
        VuforiaJNI.CustomViewerParameters_setTrayAlignment(this.swigCPtr, this, val);
    }

    public void setLensCentreToTrayDistance(float val) {
        VuforiaJNI.CustomViewerParameters_setLensCentreToTrayDistance(this.swigCPtr, this, val);
    }

    public void clearDistortionCoefficients() {
        VuforiaJNI.CustomViewerParameters_clearDistortionCoefficients(this.swigCPtr, this);
    }

    public void addDistortionCoefficient(float val) {
        VuforiaJNI.CustomViewerParameters_addDistortionCoefficient(this.swigCPtr, this, val);
    }

    public void setFieldOfView(Vec4F val) {
        VuforiaJNI.CustomViewerParameters_setFieldOfView(this.swigCPtr, this, Vec4F.getCPtr(val), val);
    }

    public void setContainsMagnet(boolean val) {
        VuforiaJNI.CustomViewerParameters_setContainsMagnet(this.swigCPtr, this, val);
    }
}
