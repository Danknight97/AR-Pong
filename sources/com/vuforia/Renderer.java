package com.vuforia;

public class Renderer {
    public static final int TARGET_FPS_CONTINUOUS = -1;
    private static State sState = null;
    private static final Object sStateMutex = new Object();
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class FPSHINT_FLAGS {
        public static final int FPSHINT_DEFAULT_FLAGS = 0;
        public static final int FPSHINT_FAST = 4;
        public static final int FPSHINT_NONE = 0;
        public static final int FPSHINT_NO_VIDEOBACKGROUND = 1;
        public static final int FPSHINT_POWER_EFFICIENCY = 2;
    }

    protected Renderer(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Renderer obj) {
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
                VuforiaJNI.delete_Renderer(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Renderer) {
            return ((Renderer) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Renderer getInstance() {
        if (Vuforia.wasInitializedJava()) {
            return new Renderer(VuforiaJNI.Renderer_getInstance(), false);
        }
        throw new RuntimeException("Use of the Java Vuforia APIs requires initalization via the com.vuforia.Vuforia class");
    }

    public State begin() {
        synchronized (sStateMutex) {
            if (sState != null) {
                sState.delete();
            }
            sState = new State(VuforiaJNI.Renderer_begin__SWIG_0(this.swigCPtr, this), true);
        }
        return sState;
    }

    public void begin(State state) {
        VuforiaJNI.Renderer_begin__SWIG_1(this.swigCPtr, this, State.getCPtr(state), state);
    }

    public boolean drawVideoBackground() {
        return VuforiaJNI.Renderer_drawVideoBackground(this.swigCPtr, this);
    }

    public void end() {
        VuforiaJNI.Renderer_end(this.swigCPtr, this);
        synchronized (sStateMutex) {
            if (sState != null) {
                sState.delete();
                sState = null;
            }
        }
    }

    public boolean updateVideoBackgroundTexture(TextureUnit textureUnit) {
        return VuforiaJNI.Renderer_updateVideoBackgroundTexture__SWIG_0(this.swigCPtr, this, TextureUnit.getCPtr(textureUnit), textureUnit);
    }

    public boolean updateVideoBackgroundTexture() {
        return VuforiaJNI.Renderer_updateVideoBackgroundTexture__SWIG_1(this.swigCPtr, this);
    }

    public void setVideoBackgroundConfig(VideoBackgroundConfig cfg) {
        VuforiaJNI.Renderer_setVideoBackgroundConfig(this.swigCPtr, this, VideoBackgroundConfig.getCPtr(cfg), cfg);
    }

    public VideoBackgroundConfig getVideoBackgroundConfig() {
        return new VideoBackgroundConfig(VuforiaJNI.Renderer_getVideoBackgroundConfig(this.swigCPtr, this), false);
    }

    public VideoBackgroundTextureInfo getVideoBackgroundTextureInfo() {
        return new VideoBackgroundTextureInfo(VuforiaJNI.Renderer_getVideoBackgroundTextureInfo(this.swigCPtr, this), false);
    }

    public boolean setVideoBackgroundTexture(TextureData textureData) {
        return VuforiaJNI.Renderer_setVideoBackgroundTexture(this.swigCPtr, this, TextureData.getCPtr(textureData), textureData);
    }

    public void setARProjection(float nearPlane, float farPlane) {
        VuforiaJNI.Renderer_setARProjection(this.swigCPtr, this, nearPlane, farPlane);
    }

    public boolean setTargetFps(int fps) {
        return VuforiaJNI.Renderer_setTargetFps(this.swigCPtr, this, fps);
    }

    public int getRecommendedFps(int flags) {
        return VuforiaJNI.Renderer_getRecommendedFps__SWIG_0(this.swigCPtr, this, flags);
    }

    public int getRecommendedFps() {
        return VuforiaJNI.Renderer_getRecommendedFps__SWIG_1(this.swigCPtr, this);
    }
}
