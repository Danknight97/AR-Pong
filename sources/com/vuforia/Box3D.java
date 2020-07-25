package com.vuforia;

public class Box3D {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Box3D(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Box3D obj) {
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
                VuforiaJNI.delete_Box3D(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Box3D) {
            return ((Box3D) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public Box3D() {
        this(VuforiaJNI.new_Box3D__SWIG_0(), true);
    }

    public Box3D(Box3D other) {
        this(VuforiaJNI.new_Box3D__SWIG_1(getCPtr(other), other), true);
    }

    public Box3D(Vec3F nMinPos, Vec3F nMaxPos) {
        this(VuforiaJNI.new_Box3D__SWIG_2(Vec3F.getCPtr(nMinPos), nMinPos, Vec3F.getCPtr(nMaxPos), nMaxPos), true);
    }

    public Vec3F getMinimumPosition() {
        return new Vec3F(VuforiaJNI.Box3D_getMinimumPosition(this.swigCPtr, this), false);
    }

    public Vec3F getMaximumPosition() {
        return new Vec3F(VuforiaJNI.Box3D_getMaximumPosition(this.swigCPtr, this), false);
    }
}
