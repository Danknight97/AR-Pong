package com.vuforia;

import java.nio.ByteBuffer;

public class Image {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Image(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Image obj) {
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
        if (obj instanceof Image) {
            return ((Image) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public int getWidth() {
        return VuforiaJNI.Image_getWidth(this.swigCPtr, this);
    }

    public int getHeight() {
        return VuforiaJNI.Image_getHeight(this.swigCPtr, this);
    }

    public int getStride() {
        return VuforiaJNI.Image_getStride(this.swigCPtr, this);
    }

    public int getBufferWidth() {
        return VuforiaJNI.Image_getBufferWidth(this.swigCPtr, this);
    }

    public int getBufferHeight() {
        return VuforiaJNI.Image_getBufferHeight(this.swigCPtr, this);
    }

    public int getFormat() {
        return VuforiaJNI.Image_getFormat(this.swigCPtr, this);
    }

    public ByteBuffer getPixels() {
        return VuforiaJNI.Image_getPixels(this.swigCPtr, this);
    }
}
