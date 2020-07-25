package com.vuforia;

public class VideoBackgroundTextureInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VideoBackgroundTextureInfo(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VideoBackgroundTextureInfo obj) {
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
                VuforiaJNI.delete_VideoBackgroundTextureInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoBackgroundTextureInfo) {
            return ((VideoBackgroundTextureInfo) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public Vec2I getTextureSize() {
        long cPtr = VuforiaJNI.VideoBackgroundTextureInfo_TextureSize_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Vec2I(cPtr, false);
    }

    public Vec2I getImageSize() {
        long cPtr = VuforiaJNI.VideoBackgroundTextureInfo_ImageSize_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Vec2I(cPtr, false);
    }

    public int getPixelFormat() {
        return VuforiaJNI.VideoBackgroundTextureInfo_PixelFormat_get(this.swigCPtr, this);
    }

    public VideoBackgroundTextureInfo() {
        this(VuforiaJNI.new_VideoBackgroundTextureInfo(), true);
    }
}
