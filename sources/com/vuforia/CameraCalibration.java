package com.vuforia;

public class CameraCalibration {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CameraCalibration(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(CameraCalibration obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof CameraCalibration) {
            return ((CameraCalibration) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public Vec2F getSize() {
        return new Vec2F(VuforiaJNI.CameraCalibration_getSize(this.swigCPtr, this), true);
    }

    public Vec2F getFocalLength() {
        return new Vec2F(VuforiaJNI.CameraCalibration_getFocalLength(this.swigCPtr, this), true);
    }

    public Vec2F getPrincipalPoint() {
        return new Vec2F(VuforiaJNI.CameraCalibration_getPrincipalPoint(this.swigCPtr, this), true);
    }

    public Vec4F getDistortionParameters() {
        return new Vec4F(VuforiaJNI.CameraCalibration_getDistortionParameters(this.swigCPtr, this), true);
    }

    public Vec2F getFieldOfViewRads() {
        return new Vec2F(VuforiaJNI.CameraCalibration_getFieldOfViewRads(this.swigCPtr, this), true);
    }
}
