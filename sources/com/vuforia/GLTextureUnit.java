package com.vuforia;

public class GLTextureUnit extends TextureUnit {
    private long swigCPtr;

    protected GLTextureUnit(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.GLTextureUnit_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(GLTextureUnit obj) {
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
                VuforiaJNI.delete_GLTextureUnit(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GLTextureUnit) {
            return ((GLTextureUnit) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public GLTextureUnit(int unit) {
        this(VuforiaJNI.new_GLTextureUnit__SWIG_0(unit), true);
    }

    public GLTextureUnit() {
        this(VuforiaJNI.new_GLTextureUnit__SWIG_1(), true);
    }

    public void setTextureUnit(int value) {
        VuforiaJNI.GLTextureUnit_TextureUnit_set(this.swigCPtr, this, value);
    }

    public int getTextureUnit() {
        return VuforiaJNI.GLTextureUnit_TextureUnit_get(this.swigCPtr, this);
    }
}
