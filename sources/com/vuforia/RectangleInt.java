package com.vuforia;

public class RectangleInt extends Area {
    private long swigCPtr;

    protected RectangleInt(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.RectangleInt_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(RectangleInt obj) {
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
                VuforiaJNI.delete_RectangleInt(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof RectangleInt) {
            return ((RectangleInt) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public RectangleInt() {
        this(VuforiaJNI.new_RectangleInt__SWIG_0(), true);
    }

    public RectangleInt(RectangleInt other) {
        this(VuforiaJNI.new_RectangleInt__SWIG_1(getCPtr(other), other), true);
    }

    public RectangleInt(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        this(VuforiaJNI.new_RectangleInt__SWIG_2(leftTopX, leftTopY, rightBottomX, rightBottomY), true);
    }

    public int getLeftTopX() {
        return VuforiaJNI.RectangleInt_getLeftTopX(this.swigCPtr, this);
    }

    public int getLeftTopY() {
        return VuforiaJNI.RectangleInt_getLeftTopY(this.swigCPtr, this);
    }

    public int getRightBottomX() {
        return VuforiaJNI.RectangleInt_getRightBottomX(this.swigCPtr, this);
    }

    public int getRightBottomY() {
        return VuforiaJNI.RectangleInt_getRightBottomY(this.swigCPtr, this);
    }

    public int getWidth() {
        return VuforiaJNI.RectangleInt_getWidth(this.swigCPtr, this);
    }

    public int getHeight() {
        return VuforiaJNI.RectangleInt_getHeight(this.swigCPtr, this);
    }

    public int getAreaSize() {
        return VuforiaJNI.RectangleInt_getAreaSize(this.swigCPtr, this);
    }

    public int getType() {
        return VuforiaJNI.RectangleInt_getType(this.swigCPtr, this);
    }
}
