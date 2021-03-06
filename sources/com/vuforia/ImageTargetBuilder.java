package com.vuforia;

public class ImageTargetBuilder {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class FRAME_QUALITY {
        public static final int FRAME_QUALITY_HIGH = 2;
        public static final int FRAME_QUALITY_LOW = 0;
        public static final int FRAME_QUALITY_MEDIUM = 1;
        public static final int FRAME_QUALITY_NONE = -1;

        private FRAME_QUALITY() {
        }
    }

    protected ImageTargetBuilder(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageTargetBuilder obj) {
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
        if (obj instanceof ImageTargetBuilder) {
            return ((ImageTargetBuilder) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public boolean build(String name, float sceneSizeWidth) {
        return VuforiaJNI.ImageTargetBuilder_build(this.swigCPtr, this, name, sceneSizeWidth);
    }

    public void startScan() {
        VuforiaJNI.ImageTargetBuilder_startScan(this.swigCPtr, this);
    }

    public void stopScan() {
        VuforiaJNI.ImageTargetBuilder_stopScan(this.swigCPtr, this);
    }

    public int getFrameQuality() {
        return VuforiaJNI.ImageTargetBuilder_getFrameQuality(this.swigCPtr, this);
    }

    public TrackableSource getTrackableSource() {
        long cPtr = VuforiaJNI.ImageTargetBuilder_getTrackableSource(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new TrackableSource(cPtr, false);
    }
}
