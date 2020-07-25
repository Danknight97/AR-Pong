package com.vuforia;

public class GLTextureData extends TextureData {
    private long swigCPtr;

    protected GLTextureData(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.GLTextureData_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(GLTextureData obj) {
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
                VuforiaJNI.delete_GLTextureData(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GLTextureData) {
            return ((GLTextureData) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public GLTextureData(int videoBackgroundTextureID) {
        this(VuforiaJNI.new_GLTextureData__SWIG_0(videoBackgroundTextureID), true);
    }

    public GLTextureData() {
        this(VuforiaJNI.new_GLTextureData__SWIG_1(), true);
    }

    public void setVideoBackgroundTextureID(int value) {
        VuforiaJNI.GLTextureData_VideoBackgroundTextureID_set(this.swigCPtr, this, value);
    }

    public int getVideoBackgroundTextureID() {
        return VuforiaJNI.GLTextureData_VideoBackgroundTextureID_get(this.swigCPtr, this);
    }
}
