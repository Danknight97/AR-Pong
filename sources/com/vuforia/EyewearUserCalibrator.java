package com.vuforia;

public class EyewearUserCalibrator {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class CONSISTENCY {
        public static final int BAD = 2;
        public static final int CONSISTENCY_LEN = 5;
        public static final int GOOD = 4;
        public static final int NONE = 0;

        /* renamed from: OK */
        public static final int f150OK = 3;
        public static final int VERY_BAD = 1;
    }

    protected EyewearUserCalibrator(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(EyewearUserCalibrator obj) {
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
                VuforiaJNI.delete_EyewearUserCalibrator(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean getProjectionMatrix(EyewearCalibrationReading[] reading, Matrix34F cameraToEyePose, Matrix34F eyeProjection) {
        if (cameraToEyePose != null && eyeProjection != null) {
            return getProjectionMatrix(reading, reading.length, cameraToEyePose, eyeProjection);
        }
        throw new NullPointerException("Matrix34F argument is null");
    }

    public int getProjectionMatrices(EyewearCalibrationReading[] leftReading, EyewearCalibrationReading[] rightReading, Matrix34F leftCameraToEyePose, Matrix34F leftEyeProjection, Matrix34F rightCameraToEyePose, Matrix34F rightEyeProjection) {
        if (leftCameraToEyePose == null || leftEyeProjection == null || rightCameraToEyePose == null || rightEyeProjection == null) {
            throw new NullPointerException("Matrix34F argument is null");
        }
        return getProjectionMatrices(leftReading, leftReading.length, rightReading, rightReading.length, leftCameraToEyePose, leftEyeProjection, rightCameraToEyePose, rightEyeProjection);
    }

    public boolean init(long surfaceWidth, long surfaceHeight, float targetWidth, float targetHeight) {
        return VuforiaJNI.EyewearUserCalibrator_init(this.swigCPtr, this, surfaceWidth, surfaceHeight, targetWidth, targetHeight);
    }

    public float getMinScaleHint() {
        return VuforiaJNI.EyewearUserCalibrator_getMinScaleHint(this.swigCPtr, this);
    }

    public float getMaxScaleHint() {
        return VuforiaJNI.EyewearUserCalibrator_getMaxScaleHint(this.swigCPtr, this);
    }

    public float getDrawingAspectRatio(long surfaceWidth, long surfaceHeight) {
        return VuforiaJNI.EyewearUserCalibrator_getDrawingAspectRatio(this.swigCPtr, this, surfaceWidth, surfaceHeight);
    }

    public boolean isStereoStretched() {
        return VuforiaJNI.EyewearUserCalibrator_isStereoStretched(this.swigCPtr, this);
    }

    private boolean getProjectionMatrix(EyewearCalibrationReading[] reading, int numReadings, Matrix34F cameraToEyePose, Matrix34F eyeProjection) {
        return VuforiaJNI.EyewearUserCalibrator_getProjectionMatrix(this.swigCPtr, this, EyewearCalibrationReading.cArrayUnwrap(reading), numReadings, Matrix34F.getCPtr(cameraToEyePose), cameraToEyePose, Matrix34F.getCPtr(eyeProjection), eyeProjection);
    }

    private int getProjectionMatrices(EyewearCalibrationReading[] leftReading, int numLeftReadings, EyewearCalibrationReading[] rightReading, int numRightReadings, Matrix34F leftCameraToEyePose, Matrix34F leftEyeProjection, Matrix34F rightCameraToEyePose, Matrix34F rightEyeProjection) {
        return VuforiaJNI.EyewearUserCalibrator_getProjectionMatrices(this.swigCPtr, this, EyewearCalibrationReading.cArrayUnwrap(leftReading), numLeftReadings, EyewearCalibrationReading.cArrayUnwrap(rightReading), numRightReadings, Matrix34F.getCPtr(leftCameraToEyePose), leftCameraToEyePose, Matrix34F.getCPtr(leftEyeProjection), leftEyeProjection, Matrix34F.getCPtr(rightCameraToEyePose), rightCameraToEyePose, Matrix34F.getCPtr(rightEyeProjection), rightEyeProjection);
    }
}
