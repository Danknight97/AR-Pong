package com.vuforia;

public class Obb2D {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Obb2D(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Obb2D obj) {
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
                VuforiaJNI.delete_Obb2D(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Obb2D) {
            return ((Obb2D) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public Obb2D() {
        this(VuforiaJNI.new_Obb2D__SWIG_0(), true);
    }

    public Obb2D(Obb2D other) {
        this(VuforiaJNI.new_Obb2D__SWIG_1(getCPtr(other), other), true);
    }

    public Obb2D(Vec2F nCenter, Vec2F nHalfExtents, float nRotation) {
        this(VuforiaJNI.new_Obb2D__SWIG_2(Vec2F.getCPtr(nCenter), nCenter, Vec2F.getCPtr(nHalfExtents), nHalfExtents, nRotation), true);
    }

    public Vec2F getCenter() {
        return new Vec2F(VuforiaJNI.Obb2D_getCenter(this.swigCPtr, this), false);
    }

    public Vec2F getHalfExtents() {
        return new Vec2F(VuforiaJNI.Obb2D_getHalfExtents(this.swigCPtr, this), false);
    }

    public float getRotation() {
        return VuforiaJNI.Obb2D_getRotation(this.swigCPtr, this);
    }
}
