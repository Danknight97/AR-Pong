package com.vuforia;

public class HeadTransformModel extends TransformModel {
    private long swigCPtr;

    protected HeadTransformModel(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.HeadTransformModel_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(HeadTransformModel obj) {
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
                VuforiaJNI.delete_HeadTransformModel(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof HeadTransformModel) {
            return ((HeadTransformModel) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public int getType() {
        return VuforiaJNI.HeadTransformModel_getType(this.swigCPtr, this);
    }

    public HeadTransformModel() {
        this(VuforiaJNI.new_HeadTransformModel__SWIG_0(), true);
    }

    public HeadTransformModel(HeadTransformModel other) {
        this(VuforiaJNI.new_HeadTransformModel__SWIG_1(getCPtr(other), other), true);
    }

    public HeadTransformModel(Vec3F pivotPos) {
        this(VuforiaJNI.new_HeadTransformModel__SWIG_2(Vec3F.getCPtr(pivotPos), pivotPos), true);
    }

    public boolean setPivotPoint(Vec3F pivot) {
        return VuforiaJNI.HeadTransformModel_setPivotPoint(this.swigCPtr, this, Vec3F.getCPtr(pivot), pivot);
    }

    public Vec3F getPivotPoint() {
        return new Vec3F(VuforiaJNI.HeadTransformModel_getPivotPoint(this.swigCPtr, this), false);
    }
}
