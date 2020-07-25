package com.vuforia;

public class HandheldTransformModel extends TransformModel {
    private long swigCPtr;

    protected HandheldTransformModel(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.HandheldTransformModel_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(HandheldTransformModel obj) {
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
                VuforiaJNI.delete_HandheldTransformModel(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof HandheldTransformModel) {
            return ((HandheldTransformModel) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public int getType() {
        return VuforiaJNI.HandheldTransformModel_getType(this.swigCPtr, this);
    }

    public HandheldTransformModel() {
        this(VuforiaJNI.new_HandheldTransformModel__SWIG_0(), true);
    }

    public HandheldTransformModel(HandheldTransformModel other) {
        this(VuforiaJNI.new_HandheldTransformModel__SWIG_1(getCPtr(other), other), true);
    }

    public HandheldTransformModel(Vec3F pivotPos) {
        this(VuforiaJNI.new_HandheldTransformModel__SWIG_2(Vec3F.getCPtr(pivotPos), pivotPos), true);
    }

    public boolean setPivotPoint(Vec3F pivot) {
        return VuforiaJNI.HandheldTransformModel_setPivotPoint(this.swigCPtr, this, Vec3F.getCPtr(pivot), pivot);
    }

    public Vec3F getPivotPoint() {
        return new Vec3F(VuforiaJNI.HandheldTransformModel_getPivotPoint(this.swigCPtr, this), false);
    }
}
