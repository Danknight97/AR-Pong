package com.vuforia;

public class Frame implements Cloneable {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Frame(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Frame obj) {
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
                VuforiaJNI.delete_Frame(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public Frame clone() {
        return new Frame(this);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Frame) {
            return ((Frame) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public Frame() {
        this(VuforiaJNI.new_Frame__SWIG_0(), true);
    }

    public Frame(Frame other) {
        this(VuforiaJNI.new_Frame__SWIG_1(getCPtr(other), other), true);
    }

    public double getTimeStamp() {
        return VuforiaJNI.Frame_getTimeStamp(this.swigCPtr, this);
    }

    public int getIndex() {
        return VuforiaJNI.Frame_getIndex(this.swigCPtr, this);
    }

    public long getNumImages() {
        return VuforiaJNI.Frame_getNumImages(this.swigCPtr, this);
    }

    public Image getImage(int idx) {
        long cPtr = VuforiaJNI.Frame_getImage(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new Image(cPtr, false);
    }
}
