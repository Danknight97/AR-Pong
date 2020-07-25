package com.vuforia;

public class VideoBackgroundConfig {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VideoBackgroundConfig(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VideoBackgroundConfig obj) {
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
                VuforiaJNI.delete_VideoBackgroundConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoBackgroundConfig) {
            return ((VideoBackgroundConfig) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public VideoBackgroundConfig() {
        this(VuforiaJNI.new_VideoBackgroundConfig(), true);
    }

    public void setEnabled(boolean value) {
        VuforiaJNI.VideoBackgroundConfig_Enabled_set(this.swigCPtr, this, value);
    }

    public boolean getEnabled() {
        return VuforiaJNI.VideoBackgroundConfig_Enabled_get(this.swigCPtr, this);
    }

    public void setPosition(Vec2I value) {
        VuforiaJNI.VideoBackgroundConfig_Position_set(this.swigCPtr, this, Vec2I.getCPtr(value), value);
    }

    public Vec2I getPosition() {
        long cPtr = VuforiaJNI.VideoBackgroundConfig_Position_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Vec2I(cPtr, false);
    }

    public void setSize(Vec2I value) {
        VuforiaJNI.VideoBackgroundConfig_Size_set(this.swigCPtr, this, Vec2I.getCPtr(value), value);
    }

    public Vec2I getSize() {
        long cPtr = VuforiaJNI.VideoBackgroundConfig_Size_get(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Vec2I(cPtr, false);
    }

    public void setReflection(int value) {
        VuforiaJNI.VideoBackgroundConfig_Reflection_set(this.swigCPtr, this, value);
    }

    public int getReflection() {
        return VuforiaJNI.VideoBackgroundConfig_Reflection_get(this.swigCPtr, this);
    }
}
