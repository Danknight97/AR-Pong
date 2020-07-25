package com.vuforia.p000ar.p001pl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureRequest.Key;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Trace;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import com.vuforia.PIXEL_FORMAT;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(21)
/* renamed from: com.vuforia.ar.pl.Camera2_Preview */
public class Camera2_Preview {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
    private static final int AR_CAMERA_EXPOSUREMODE_MANUAL = 805339136;
    private static final int AR_CAMERA_EXPOSUREMODE_SHUTTER_PRIORITY = 805371904;
    private static final int AR_CAMERA_FOCUSMODE_AUTO = 805306400;
    private static final int AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO = 805306432;
    private static final int AR_CAMERA_FOCUSMODE_FIXED = 805306880;
    private static final int AR_CAMERA_FOCUSMODE_INFINITY = 805306624;
    private static final int AR_CAMERA_FOCUSMODE_MACRO = 805306496;
    private static final int AR_CAMERA_FOCUSMODE_NORMAL = 805306384;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB32 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB8888 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR24 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR888 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA32 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA8888 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV16 = 268439816;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB24 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA32 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA4444 = 268439821;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA5551 = 268439820;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_UNKNOWN = 268439808;
    private static final int AR_CAMERA_IMAGE_FORMAT_YUV420P = 268439828;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV12 = 268439818;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV16 = 268439819;
    private static final int AR_CAMERA_PARAMTYPE_BASE = 536870912;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE = 537133056;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE = 537001984;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTRANGE = 537919488;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTVALUE = 537395200;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREMODE = 536870944;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIME = 536871168;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE = 536871424;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUE = 536871936;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE = 536872960;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSMODE = 536870914;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSRANGE = 536870920;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSREGION = 536870928;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSVALUE = 536870916;
    private static final int AR_CAMERA_PARAMTYPE_ISO = 536870976;
    private static final int AR_CAMERA_PARAMTYPE_ISORANGE = 536871040;
    private static final int AR_CAMERA_PARAMTYPE_LENS_IS_ADJUSTING = 545259520;
    private static final int AR_CAMERA_PARAMTYPE_RECORDING_HINT = 541065216;
    private static final int AR_CAMERA_PARAMTYPE_ROTATION = 538968064;
    private static final int AR_CAMERA_PARAMTYPE_TORCHMODE = 536870913;
    private static final int AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION = 553648128;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE = 536875008;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE = 536887296;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE = 536879104;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMRANGE = 536936448;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMVALUE = 536903680;
    private static final int AR_CAMERA_PARAMVALUE_BASE = 805306368;
    private static final int AR_CAMERA_STATUS_CAPTURE_RUNNING = 268443651;
    private static final int AR_CAMERA_STATUS_OPENED = 268443650;
    private static final int AR_CAMERA_STATUS_UNINITIALIZED = 268443649;
    private static final int AR_CAMERA_STATUS_UNKNOWN = 268443648;
    private static final int AR_CAMERA_TORCHMODE_AUTO = 805306372;
    private static final int AR_CAMERA_TORCHMODE_CONTINUOUSAUTO = 805306376;
    private static final int AR_CAMERA_TORCHMODE_OFF = 805306369;
    private static final int AR_CAMERA_TORCHMODE_ON = 805306370;
    private static final int AR_CAMERA_TYPE_MONO = 268447761;
    private static final int AR_CAMERA_TYPE_STEREO = 268447762;
    private static final int AR_CAMERA_TYPE_UNKNOWN = 268447760;
    private static final int AR_CAMERA_WHITEBALANCEMODE_AUTO = 807403520;
    private static final int AR_CAMERA_WHITEBALANCEMODE_CONTINUOUSAUTO = 809500672;
    private static final int AR_CAMERA_WHITEBALANCEMODE_LOCKED = 806354944;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_FRAMERATES = 4;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGEFORMATS = 5;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGESIZES = 3;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_PARAMVALUES = 2;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_QUERYABLE_PARAMS = 0;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_SETTABLE_PARAMS = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_FORMAT = 2;
    private static final int CAMERA_CAPTUREINFO_VALUE_FRAMERATE = 3;
    private static final int CAMERA_CAPTUREINFO_VALUE_HEIGHT = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED = 4;
    private static final int CAMERA_CAPTUREINFO_VALUE_WIDTH = 0;
    private static final int[] CAMERA_IMAGE_FORMAT_CONVERSIONTABLE = {35, AR_CAMERA_IMAGE_FORMAT_NV21};
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = true;
    private static final Range<Integer> EMPTY_RANGE = new Range<>(Integer.valueOf(0), Integer.valueOf(0));
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final int MAX_HIGHEST_FPS_ALLOWED = 300;
    private static final int MAX_LOWEST_FPS_ALLOWED = 150;
    private static final String MODULENAME = "Camera2_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    /* access modifiers changed from: private */
    public HashMap<ImageReader, Integer> mCameraCacheInfoIndexCache = null;
    /* access modifiers changed from: private */
    public Vector<CameraCacheInfo> mCameraCacheInfos = null;
    /* access modifiers changed from: private */
    public Vector<CameraCacheInfo> mCameraCacheInfosInProgress = null;
    private CameraManager mCameraManager;
    private Context mContext;
    private int mIsPermissionGranted = -1;
    /* access modifiers changed from: private */
    public Semaphore mOpenCloseSemaphore = new Semaphore(1);

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner */
    private class AutofocusRunner extends CaptureCallback {
        private CameraCacheInfo mCCI;
        private CaptureRequest mCancelRequest = null;
        private CaptureRequest mFocusRequest = null;

        public AutofocusRunner(CameraCacheInfo cci) {
            this.mCCI = cci;
        }

        public boolean triggerAutofocus() throws CameraAccessException {
            if (this.mCCI == null || this.mCCI.builder == null || this.mCCI.session == null) {
                return false;
            }
            Integer mode = (Integer) this.mCCI.builder.get(CaptureRequest.CONTROL_AF_MODE);
            if (CaptureRequest.CONTROL_AF_MODE == null || mode == null) {
                return false;
            }
            if (mode.intValue() != 1 && mode.intValue() != 2) {
                return false;
            }
            this.mCCI.isAutoFocusing = true;
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
            this.mCancelRequest = this.mCCI.builder.build();
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
            this.mFocusRequest = this.mCCI.builder.build();
            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
            this.mCCI.session.capture(this.mCancelRequest, this, this.mCCI.handler);
            return true;
        }

        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Integer afState = (Integer) result.get(CaptureResult.CONTROL_AF_STATE);
            if (request.equals(this.mCancelRequest) && afState.intValue() == 0) {
                try {
                    this.mCCI.session.capture(this.mFocusRequest, this, this.mCCI.handler);
                } catch (CameraAccessException e) {
                }
            } else if (!request.equals(this.mFocusRequest)) {
            } else {
                if (afState.intValue() == 4 || afState.intValue() == 5) {
                    this.mCCI.isAutoFocusing = false;
                }
            }
        }
    }

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo */
    public class CameraCacheInfo {
        int bufferFormatPL;
        int bufferHeight;
        int bufferWidth;
        Builder builder;
        int[] caps;
        CameraCharacteristics characteristics;
        CameraDevice device;
        long deviceHandle;
        int deviceID;
        String deviceIDString;
        Handler handler;
        Semaphore imageSemaphore;
        Image[] images;
        boolean isAutoFocusing;
        CaptureResult lastResult;
        int overrideFormatAndroid;
        int overrideHeight;
        int overrideWidth;
        ImageReader reader;
        int requestFormatAndroid;
        int requestFramerate;
        int requestHeight;
        int requestWidth;
        CameraCaptureSession session;
        int status;
        List<Surface> surfaces;
        HandlerThread thread;

        public CameraCacheInfo() {
        }
    }

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$FrameInfo */
    public class FrameInfo {
        long exposureTime;
        int iso;
        long timestamp;

        public FrameInfo() {
        }
    }

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$OnCameraDataAvailable */
    private class OnCameraDataAvailable implements OnImageAvailableListener {
        private int DEBUG_FORMAT = 0;
        private int[] actualBufferSize = null;
        private int actualCaptureFormat = Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
        private int currentTestBufferIndex = 0;
        private ByteBuffer[] testYUVBuffers = new ByteBuffer[2];

        public OnCameraDataAvailable() {
        }

        public void onImageAvailable(ImageReader reader) {
            Trace.beginSection("onImageAvailable (java)");
            Integer index = (Integer) Camera2_Preview.this.mCameraCacheInfoIndexCache.get(reader);
            if (index == null) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to find reader in the index cache!");
                Trace.endSection();
                return;
            }
            CameraCacheInfo _cci = (CameraCacheInfo) Camera2_Preview.this.mCameraCacheInfos.get(index.intValue());
            if (_cci == null) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to find CCI in list!");
                Trace.endSection();
            } else if (!_cci.imageSemaphore.tryAcquire()) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to aquire image semaphore, need to free some buffers!!");
                Trace.endSection();
            } else {
                if (reader.getMaxImages() > 0) {
                    Image img = reader.acquireLatestImage();
                    if (img != null) {
                        FrameInfo info = new FrameInfo();
                        info.timestamp = img.getTimestamp();
                        CaptureResult captureResult = _cci.lastResult;
                        if (captureResult != null) {
                            Long exposureTime = (Long) captureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                            if (exposureTime != null) {
                                info.exposureTime = exposureTime.longValue();
                                info.timestamp += exposureTime.longValue();
                            }
                            Integer iso = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
                            if (iso != null) {
                                info.iso = iso.intValue();
                            }
                        }
                        if (this.actualCaptureFormat == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN) {
                            this.actualCaptureFormat = getImageFormat(img);
                        }
                        if (this.DEBUG_FORMAT == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12) {
                            this.testYUVBuffers[this.currentTestBufferIndex] = convertNV21toYV12Contigious(img, this.testYUVBuffers[this.currentTestBufferIndex]);
                            img.close();
                            Camera2_Preview.this.newFrameAvailable(_cci.deviceHandle, index.intValue(), _cci.bufferWidth, _cci.bufferHeight, this.actualBufferSize, Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12, this.testYUVBuffers[this.currentTestBufferIndex], info);
                            this.currentTestBufferIndex = (this.currentTestBufferIndex + 1) % 2;
                        } else if (this.DEBUG_FORMAT == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P) {
                            this.testYUVBuffers[this.currentTestBufferIndex] = convertNV21toYUV420Contigious(img, this.testYUVBuffers[this.currentTestBufferIndex]);
                            img.close();
                            Camera2_Preview.this.newFrameAvailable(_cci.deviceHandle, index.intValue(), _cci.bufferWidth, _cci.bufferHeight, this.actualBufferSize, Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P, this.testYUVBuffers[this.currentTestBufferIndex], info);
                            this.currentTestBufferIndex = (this.currentTestBufferIndex + 1) % 2;
                        } else {
                            Camera2_Preview.this.newFrameAvailable(_cci.deviceHandle, index.intValue(), _cci.bufferWidth, _cci.bufferHeight, this.actualBufferSize, this.actualCaptureFormat, img.getPlanes()[0].getBuffer(), info);
                            img.close();
                        }
                    }
                }
                _cci.imageSemaphore.release();
                Trace.endSection();
            }
        }

        private ByteBuffer convertNV21toYUV420Contigious(Image img, ByteBuffer buff) {
            Plane y = img.getPlanes()[0];
            Plane u = img.getPlanes()[1];
            Plane v = img.getPlanes()[2];
            int height = img.getHeight();
            int width = img.getWidth();
            int bufferSize = ((height * width) * 12) / 8;
            int lumaPaddingX = 0;
            int lumaPaddingY = 0;
            int chromaPaddingX = 0;
            int chromaPaddingY = 0;
            if (this.actualBufferSize != null) {
                bufferSize = (this.actualBufferSize[0] * this.actualBufferSize[1]) + (this.actualBufferSize[2] * 2 * this.actualBufferSize[3]);
                lumaPaddingX = this.actualBufferSize[0] - width;
                lumaPaddingY = this.actualBufferSize[1] - height;
                chromaPaddingX = this.actualBufferSize[2] - (width / 2);
                chromaPaddingY = this.actualBufferSize[3] - (height / 2);
            }
            return convertNV21toPaddedYUV(buff, y, v, u, height, width, bufferSize, lumaPaddingX, lumaPaddingY, chromaPaddingX, chromaPaddingY);
        }

        private ByteBuffer convertNV21toYV12Contigious(Image img, ByteBuffer buff) {
            Plane y = img.getPlanes()[0];
            Plane u = img.getPlanes()[1];
            Plane v = img.getPlanes()[2];
            int height = img.getHeight();
            int width = img.getWidth();
            int bufferSize = ((height * width) * 12) / 8;
            int lumaPaddingX = 0;
            int lumaPaddingY = 0;
            int chromaPaddingX = 0;
            int chromaPaddingY = 0;
            if (this.actualBufferSize != null) {
                bufferSize = (this.actualBufferSize[0] * this.actualBufferSize[1]) + (this.actualBufferSize[2] * 2 * this.actualBufferSize[3]);
                lumaPaddingX = this.actualBufferSize[0] - width;
                lumaPaddingY = this.actualBufferSize[1] - height;
                chromaPaddingX = this.actualBufferSize[2] - (width / 2);
                chromaPaddingY = this.actualBufferSize[3] - (height / 2);
            }
            return convertNV21toPaddedYUV(buff, y, u, v, height, width, bufferSize, lumaPaddingX, lumaPaddingY, chromaPaddingX, chromaPaddingY);
        }

        private ByteBuffer convertNV21toPaddedYUV(ByteBuffer buff, Plane y, Plane u, Plane v, int height, int width, int bufferSize, int lumaPaddingX, int lumaPaddingY, int chromaPaddingX, int chromaPaddingY) {
            ByteBuffer out = buff != null ? buff : ByteBuffer.allocateDirect(bufferSize);
            out.rewind();
            if (lumaPaddingX == 0) {
                out.put(y.getBuffer());
            } else {
                int actualLimit = y.getBuffer().limit();
                while (y.getBuffer().hasRemaining()) {
                    y.getBuffer().limit(y.getBuffer().position() + width);
                    out.put(y.getBuffer());
                    for (int p = 0; p < lumaPaddingX; p++) {
                        out.put(0);
                    }
                    y.getBuffer().limit(actualLimit);
                }
            }
            if (lumaPaddingY > 0) {
                for (int p2 = 0; p2 < this.actualBufferSize[0] * lumaPaddingY; p2++) {
                    out.put(0);
                }
            }
            for (int row = 0; row < height / 2; row++) {
                for (int col = 0; col < width / 2; col++) {
                    out.put(u.getBuffer().get());
                    if (u.getBuffer().hasRemaining()) {
                        u.getBuffer().get();
                    }
                }
                for (int p3 = 0; p3 < chromaPaddingX; p3++) {
                    out.put(0);
                }
            }
            if (chromaPaddingY > 0) {
                for (int p4 = 0; p4 < this.actualBufferSize[2] * chromaPaddingY; p4++) {
                    out.put(0);
                }
            }
            for (int row2 = 0; row2 < height / 2; row2++) {
                for (int col2 = 0; col2 < width / 2; col2++) {
                    out.put(v.getBuffer().get());
                    if (v.getBuffer().hasRemaining()) {
                        v.getBuffer().get();
                    }
                }
                for (int p5 = 0; p5 < chromaPaddingX; p5++) {
                    out.put(0);
                }
            }
            if (chromaPaddingY > 0) {
                for (int p6 = 0; p6 < this.actualBufferSize[2] * chromaPaddingY; p6++) {
                    out.put(0);
                }
            }
            out.flip();
            return out;
        }

        private int getImageFormat(Image img) {
            if (img == null || img.getPlanes().length != 3 || img.getFormat() != 35) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            Plane y = img.getPlanes()[0];
            Plane u = img.getPlanes()[1];
            Plane v = img.getPlanes()[2];
            if (y.getPixelStride() != 1 || y.getRowStride() != img.getWidth() || u.getPixelStride() != v.getPixelStride() || u.getRowStride() != v.getRowStride()) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            long[] buffers = {Camera2_Preview.this.getBufferAddress(y.getBuffer()), Camera2_Preview.this.getBufferAddress(u.getBuffer()), Camera2_Preview.this.getBufferAddress(v.getBuffer())};
            if (buffers[0] == 0 || buffers[1] == 0 || buffers[2] == 0) {
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            }
            if (u.getPixelStride() == 2) {
                if (buffers[1] + 1 == buffers[2]) {
                    return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12;
                }
                if (buffers[2] + 1 == buffers[1]) {
                    return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21;
                }
            }
            if (u.getPixelStride() != 1) {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to detect a supported image format, Unknown Image Format");
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            } else if (buffers[1] < buffers[2]) {
                if (buffers[1] + ((long) ((img.getHeight() / 2) * (img.getWidth() / 2))) != buffers[2]) {
                    this.actualBufferSize = new int[4];
                    this.actualBufferSize[0] = y.getRowStride();
                    this.actualBufferSize[1] = (int) ((buffers[1] - buffers[0]) / ((long) y.getRowStride()));
                    this.actualBufferSize[2] = u.getRowStride();
                    this.actualBufferSize[3] = (int) ((buffers[2] - buffers[1]) / ((long) u.getRowStride()));
                }
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12;
            } else {
                if (buffers[2] + ((long) ((img.getHeight() / 2) * (img.getWidth() / 2))) != buffers[1]) {
                    this.actualBufferSize = new int[4];
                    this.actualBufferSize[0] = y.getRowStride();
                    this.actualBufferSize[1] = (int) ((buffers[2] - buffers[0]) / ((long) y.getRowStride()));
                    this.actualBufferSize[2] = u.getRowStride();
                    this.actualBufferSize[3] = (int) ((buffers[1] - buffers[2]) / ((long) u.getRowStride()));
                }
                return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P;
            }
        }
    }

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback */
    private class OnFrameCapturedCallback extends CaptureCallback {
        CameraCacheInfo mCCI;

        public OnFrameCapturedCallback(CameraCacheInfo cci) {
            this.mCCI = cci;
        }

        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            if (this.mCCI != null) {
                this.mCCI.lastResult = result;
            }
        }
    }

    /* access modifiers changed from: private */
    public native long getBufferAddress(ByteBuffer byteBuffer);

    /* access modifiers changed from: private */
    public native void newFrameAvailable(long j, int i, int i2, int i3, int[] iArr, int i4, ByteBuffer byteBuffer, Object obj);

    private boolean checkPermission() {
        if (this.mIsPermissionGranted == 0) {
            return true;
        }
        try {
            Activity activity = SystemTools.getActivityFromNative();
            this.mIsPermissionGranted = activity.getPackageManager().checkPermission("android.permission.CAMERA", activity.getPackageName());
            if (this.mIsPermissionGranted == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private int getCameraDeviceIndex(int camIndex, int type, int direction) {
        if (type != AR_CAMERA_TYPE_UNKNOWN) {
        }
        int camInfoDirection = -1;
        switch (direction) {
            case AR_CAMERA_DIRECTION_UNKNOWN /*268443664*/:
                break;
            case AR_CAMERA_DIRECTION_BACK /*268443665*/:
                camInfoDirection = 1;
                break;
            case AR_CAMERA_DIRECTION_FRONT /*268443666*/:
                camInfoDirection = 0;
                break;
            default:
                SystemTools.setSystemErrorCode(2);
                return -1;
        }
        try {
            String[] camIds = this.mCameraManager.getCameraIdList();
            int i = 0;
            while (i < camIds.length) {
                CameraCharacteristics cc = this.mCameraManager.getCameraCharacteristics(camIds[i]);
                if ((camInfoDirection < 0 || camInfoDirection == ((Integer) cc.get(CameraCharacteristics.LENS_FACING)).intValue()) && (camIndex < 0 || camIndex == i)) {
                    return i;
                }
                i++;
            }
        } catch (CameraAccessException e) {
        }
        SystemTools.setSystemErrorCode(6);
        return -1;
    }

    private CameraCacheInfo getCameraCacheInfo(int cameraCacheInfoIndex) {
        if (cameraCacheInfoIndex < 0 || cameraCacheInfoIndex >= this.mCameraCacheInfos.size()) {
            return null;
        }
        return (CameraCacheInfo) this.mCameraCacheInfos.get(cameraCacheInfoIndex);
    }

    private boolean setCustomCameraParams(CameraCacheInfo cci, String customData) {
        if (cci == null || cci.builder == null || cci.characteristics == null) {
            return false;
        }
        try {
            JSONObject jsonObj = new JSONObject(customData);
            Iterator<?> elements = jsonObj.keys();
            while (elements.hasNext()) {
                String key = (String) elements.next();
                try {
                    Object value = jsonObj.get(key);
                    Class<?> clazz = value.getClass();
                    if ((clazz != String.class && clazz != Integer.class) || mapStringToKey(key, cci.characteristics, value) == null) {
                        return false;
                    }
                    cci.builder.set(mapStringToKey(key, cci.characteristics, value), value);
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private <T> Key<T> mapStringToKey(String keyString, CameraCharacteristics cc, T t) {
        for (Key<?> key : cc.getAvailableCaptureRequestKeys()) {
            if (key.getName().equals(keyString)) {
                return key;
            }
        }
        return null;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo cci, int[] captureInfo, int[] overrideCaptureInfo) {
        Range range;
        int i;
        if (!(captureInfo == null && overrideCaptureInfo == null)) {
            cci.overrideWidth = overrideCaptureInfo != null ? overrideCaptureInfo[0] : captureInfo[0];
            cci.overrideHeight = overrideCaptureInfo != null ? overrideCaptureInfo[1] : captureInfo[1];
            if (overrideCaptureInfo != null) {
                i = overrideCaptureInfo[2];
            } else {
                i = captureInfo[2];
            }
            cci.overrideFormatAndroid = translateImageFormat(i, CONVERT_FORMAT_TO_ANDROID);
        }
        if (captureInfo == null) {
            return true;
        }
        if (cci == null || cci.builder == null || cci.characteristics == null) {
            DebugLog.LOGE(MODULENAME, "CamCacheInfo not properly initialized in setCaptureParams");
            return false;
        }
        cci.requestWidth = captureInfo[0];
        cci.requestHeight = captureInfo[1];
        cci.requestFormatAndroid = translateImageFormat(captureInfo[2], CONVERT_FORMAT_TO_ANDROID);
        cci.requestFramerate = captureInfo[3];
        Size[] sizes = ((StreamConfigurationMap) cci.characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(cci.requestFormatAndroid);
        if (sizes == null) {
            DebugLog.LOGD(MODULENAME, String.format("setCameraCaptureParams: format not supported. : %d", new Object[]{Integer.valueOf(captureInfo[2])}));
            return false;
        }
        boolean foundSize = false;
        for (Size s : sizes) {
            foundSize = s.equals(new Size(cci.requestWidth, cci.requestHeight));
            if (foundSize) {
                break;
            }
        }
        if (!foundSize) {
            DebugLog.LOGD(MODULENAME, String.format("setCameraCaptureParams: size not supported. : %d, %d", new Object[]{Integer.valueOf(cci.requestWidth), Integer.valueOf(cci.requestHeight)}));
            return false;
        }
        Range[] rangeArr = (Range[]) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        Range range2 = null;
        int bestRangeSize = Integer.MAX_VALUE;
        Range[] arr$ = rangeArr;
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                break;
            }
            range = arr$[i$];
            if (((Integer) range.getLower()).intValue() >= MAX_LOWEST_FPS_ALLOWED || ((Integer) range.getUpper()).intValue() >= MAX_HIGHEST_FPS_ALLOWED) {
                DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using first fps range as default instead of searching for perfect fit.", new Object[]{range.getLower(), range.getUpper()}));
                range2 = rangeArr[0];
            } else {
                if (range.contains(Integer.valueOf(cci.requestFramerate))) {
                    int size = ((Integer) range.getUpper()).intValue() - ((Integer) range.getLower()).intValue();
                    if (size < bestRangeSize) {
                        range2 = range;
                        bestRangeSize = size;
                    }
                }
                i$++;
            }
        }
        DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using first fps range as default instead of searching for perfect fit.", new Object[]{range.getLower(), range.getUpper()}));
        range2 = rangeArr[0];
        if (range2 == null) {
            DebugLog.LOGD(MODULENAME, String.format("setCameraCaptureParams: fps range not supported.", new Object[0]));
            return false;
        }
        cci.builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, range2);
        return true;
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cci) {
        cci.reader = ImageReader.newInstance(cci.requestWidth, cci.requestHeight, cci.requestFormatAndroid, 2);
        cci.imageSemaphore = new Semaphore(2);
        cci.images = new Image[2];
        cci.bufferWidth = cci.requestWidth == cci.overrideWidth ? cci.reader.getWidth() : cci.overrideWidth;
        cci.bufferHeight = cci.requestHeight == cci.overrideHeight ? cci.reader.getHeight() : cci.overrideHeight;
        cci.bufferFormatPL = translateImageFormat(cci.requestFormatAndroid == cci.overrideFormatAndroid ? cci.reader.getImageFormat() : cci.overrideFormatAndroid, CONVERT_FORMAT_TO_PL);
        cci.reader.setOnImageAvailableListener(new OnCameraDataAvailable(), cci.handler);
        if (cci.surfaces == null) {
            cci.surfaces = new LinkedList();
        }
        cci.surfaces.clear();
        cci.surfaces.add(cci.reader.getSurface());
        return true;
    }

    private void setCameraCapsBit(CameraCacheInfo cci, int capsIndex, int paramType, boolean value) {
        int baseValue;
        switch (capsIndex) {
            case 0:
            case 1:
                baseValue = AR_CAMERA_PARAMTYPE_BASE;
                break;
            case 2:
                baseValue = AR_CAMERA_PARAMVALUE_BASE;
                break;
            default:
                return;
        }
        int index = (int) (Math.log((double) ((baseValue ^ -1) & paramType)) / Math.log(2.0d));
        if (value) {
            int[] iArr = cci.caps;
            iArr[capsIndex] = iArr[capsIndex] | (1 << index);
            return;
        }
        int[] iArr2 = cci.caps;
        iArr2[capsIndex] = iArr2[capsIndex] & ((1 << index) ^ -1);
    }

    private int translateImageFormat(int fromValue, boolean conversionMode) {
        int i = 0;
        while (i < CAMERA_IMAGE_FORMAT_CONVERSIONTABLE.length / 2) {
            if (fromValue != (conversionMode == CONVERT_FORMAT_TO_PL ? CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i * 2] : CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i * 2) + 1])) {
                i++;
            } else if (conversionMode == CONVERT_FORMAT_TO_PL) {
                return CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i * 2) + 1];
            } else {
                return CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i * 2];
            }
        }
        if (conversionMode == CONVERT_FORMAT_TO_PL) {
            return AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getBitsPerPixel(int imgFormat) {
        switch (imgFormat) {
            case 4:
            case PIXEL_FORMAT.RGBA8888 /*16*/:
                return 16;
            case 17:
                return 12;
            case 842094169:
                return 12;
            default:
                return 0;
        }
    }

    private List<Integer> getSupportedPreviewFrameRates(CameraCharacteristics cc) {
        Range<Integer>[] arr$;
        Range<Integer>[] frameRateRanges = (Range[]) cc.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;
        for (Range<Integer> r : frameRateRanges) {
            lowest = Math.min(lowest, ((Integer) r.getLower()).intValue());
            highest = Math.max(highest, ((Integer) r.getUpper()).intValue());
        }
        List<Integer> supportedFrameRates = new LinkedList<>();
        if (lowest < 0 || lowest >= MAX_LOWEST_FPS_ALLOWED || highest < 0 || highest >= MAX_HIGHEST_FPS_ALLOWED) {
            DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using saner defaults instead.", new Object[]{Integer.valueOf(lowest), Integer.valueOf(highest)}));
            supportedFrameRates.add(Integer.valueOf(30));
        } else {
            for (int i = lowest; i <= highest; i++) {
                Range<Integer>[] arr$2 = frameRateRanges;
                int len$ = arr$2.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        break;
                    } else if (arr$2[i$].contains(Integer.valueOf(i))) {
                        supportedFrameRates.add(Integer.valueOf(i));
                        break;
                    } else {
                        i$++;
                    }
                }
            }
        }
        return supportedFrameRates;
    }

    private boolean checkCameraManager() {
        if (this.mCameraManager != null) {
            return true;
        }
        Activity activity = SystemTools.getActivityFromNative();
        if (activity == null) {
            return false;
        }
        Context context = activity.getApplication();
        if (context == null) {
            return false;
        }
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
        if (this.mCameraManager == null) {
            return false;
        }
        return true;
    }

    public boolean init() {
        this.mCameraCacheInfos = new Vector<>();
        this.mCameraCacheInfosInProgress = new Vector<>();
        this.mCameraCacheInfoIndexCache = new HashMap<>();
        return true;
    }

    public int getNumberOfCameras() {
        int i = -1;
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return i;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return i;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    return this.mCameraManager.getCameraIdList().length;
                } catch (Exception e) {
                }
            }
            SystemTools.setSystemErrorCode(6);
            return i;
        }
    }

    public int getOrientation(int cameraID) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    String[] cameraIds = this.mCameraManager.getCameraIdList();
                    if (cameraID < cameraIds.length) {
                        return ((Integer) this.mCameraManager.getCameraCharacteristics(cameraIds[cameraID]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                    }
                } catch (Exception e) {
                }
            }
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
    }

    public int getDirection(int cameraID) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            if (SystemTools.checkMinimumApiLevel(21)) {
                try {
                    String[] cameraIds = this.mCameraManager.getCameraIdList();
                    if (cameraID < cameraIds.length) {
                        switch (((Integer) this.mCameraManager.getCameraCharacteristics(cameraIds[cameraID]).get(CameraCharacteristics.LENS_FACING)).intValue()) {
                            case 0:
                                return AR_CAMERA_DIRECTION_FRONT;
                            case 1:
                                r4 = AR_CAMERA_DIRECTION_BACK;
                                return AR_CAMERA_DIRECTION_BACK;
                            default:
                                return AR_CAMERA_DIRECTION_UNKNOWN;
                        }
                    }
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            }
            r4 = AR_CAMERA_DIRECTION_BACK;
            return AR_CAMERA_DIRECTION_BACK;
        }
    }

    public int getDeviceID(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci != null) {
            return cci.deviceID;
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    public int open(long deviceHandle, int camIndex, int type, int direction, String customData, int[] captureInfo, int[] overrideCaptureInfo) {
        CameraCacheInfo cci;
        CameraCacheInfo cci2;
        DebugLog.LOGI(MODULENAME, String.format("open called with handle: %x, %d, type: %d, direction: %d", new Object[]{Long.valueOf(deviceHandle), Integer.valueOf(camIndex), Integer.valueOf(type), Integer.valueOf(direction)}));
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else {
            int cameraDeviceIndex = getCameraDeviceIndex(camIndex, type, direction);
            if (cameraDeviceIndex < 0) {
                return -1;
            }
            DebugLog.LOGD(MODULENAME, "Camera device index" + cameraDeviceIndex);
            int cameraCacheInfoIndex = -1;
            CameraCacheInfo cci3 = null;
            int cameraCacheInfoSize = this.mCameraCacheInfos.size();
            int i = 0;
            while (true) {
                if (i >= cameraCacheInfoSize) {
                    cci = cci3;
                    break;
                }
                cci3 = (CameraCacheInfo) this.mCameraCacheInfos.get(i);
                if (cci3.deviceID == cameraDeviceIndex) {
                    cameraCacheInfoIndex = i;
                    cci = cci3;
                    break;
                }
                i++;
            }
            if (cameraCacheInfoIndex < 0) {
                try {
                    cci2 = new CameraCacheInfo();
                    try {
                        cci2.deviceID = cameraDeviceIndex;
                        cci2.deviceHandle = deviceHandle;
                        cci2.deviceIDString = this.mCameraManager.getCameraIdList()[cci2.deviceID];
                        cci2.characteristics = this.mCameraManager.getCameraCharacteristics(cci2.deviceIDString);
                        cci2.device = null;
                        cci2.session = null;
                        cci2.builder = null;
                        cci2.surfaces = null;
                        cci2.reader = null;
                        cci2.images = null;
                        cci2.imageSemaphore = null;
                        cci2.thread = new HandlerThread(cci2.deviceIDString + "_camera_thread");
                        cci2.thread.start();
                        cci2.handler = new Handler(cci2.thread.getLooper());
                        cci2.overrideWidth = 0;
                        cci2.requestWidth = 0;
                        cci2.bufferWidth = 0;
                        cci2.overrideHeight = 0;
                        cci2.requestHeight = 0;
                        cci2.bufferHeight = 0;
                        cci2.bufferFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                        cci2.overrideFormatAndroid = 0;
                        cci2.requestFormatAndroid = 0;
                        cci2.caps = null;
                        cci2.status = AR_CAMERA_STATUS_UNINITIALIZED;
                        cci2.isAutoFocusing = false;
                    } catch (CameraAccessException e) {
                        SystemTools.setSystemErrorCode(6);
                        return -1;
                    }
                } catch (CameraAccessException e2) {
                    CameraCacheInfo cameraCacheInfo = cci;
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            } else {
                cci2 = cci;
            }
            boolean resultCameraOpened = false;
            int cameraOpenRetryCount = NUM_MAX_CAMERAOPEN_RETRY;
            while (true) {
                int cameraOpenRetryCount2 = cameraOpenRetryCount;
                try {
                    this.mOpenCloseSemaphore.acquire();
                    this.mCameraCacheInfosInProgress.add(cci2);
                    CameraManager cameraManager = this.mCameraManager;
                    String str = cci2.deviceIDString;
                    C00461 r0 = new StateCallback() {
                        public void onOpened(CameraDevice camera) {
                            try {
                                Iterator i$ = Camera2_Preview.this.mCameraCacheInfosInProgress.iterator();
                                while (i$.hasNext()) {
                                    CameraCacheInfo info = (CameraCacheInfo) i$.next();
                                    if (info.deviceIDString.equals(camera.getId())) {
                                        CameraCacheInfo _cci = info;
                                        _cci.device = camera;
                                        _cci.builder = camera.createCaptureRequest(1);
                                    }
                                }
                            } catch (CameraAccessException e) {
                                null.builder = null;
                                null.device = null;
                            } finally {
                                Camera2_Preview.this.mOpenCloseSemaphore.release();
                            }
                        }

                        public void onError(CameraDevice camera, int error) {
                            camera.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }

                        public void onDisconnected(CameraDevice camera) {
                            camera.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }
                    };
                    cameraManager.openCamera(str, r0, cci2.handler);
                    this.mOpenCloseSemaphore.acquire();
                    this.mCameraCacheInfosInProgress.remove(cci2);
                    this.mOpenCloseSemaphore.release();
                    resultCameraOpened = (cci2.device == null || cci2.builder == null) ? false : true;
                } catch (Exception e3) {
                }
                if (!resultCameraOpened && cameraOpenRetryCount2 > 0) {
                    try {
                        synchronized (this) {
                            wait(250);
                        }
                    } catch (Exception e4) {
                    }
                }
                if (resultCameraOpened) {
                    int i2 = cameraOpenRetryCount2;
                    break;
                }
                cameraOpenRetryCount = cameraOpenRetryCount2 - 1;
                if (cameraOpenRetryCount2 <= 0) {
                    break;
                }
            }
            if (cci2.device == null || cci2.builder == null) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
            boolean setCaptureInfo = (captureInfo != null && captureInfo.length > 0) || (overrideCaptureInfo != null && overrideCaptureInfo.length > 0);
            boolean setCustomData = customData != null && customData.length() > 0;
            if (setCaptureInfo || setCustomData) {
                if (setCaptureInfo) {
                    if (captureInfo != null && captureInfo.length != 5) {
                        SystemTools.setSystemErrorCode(2);
                        return -1;
                    } else if (!setCameraCaptureParams(cci2, captureInfo, overrideCaptureInfo)) {
                        SystemTools.setSystemErrorCode(6);
                        return -1;
                    }
                }
                if (setCustomData && !setCustomCameraParams(cci2, customData)) {
                    SystemTools.setSystemErrorCode(2);
                    return -1;
                }
            }
            cci2.status = AR_CAMERA_STATUS_OPENED;
            if (cameraCacheInfoIndex >= 0) {
                return cameraCacheInfoIndex;
            }
            this.mCameraCacheInfos.add(cci2);
            return this.mCameraCacheInfos.size() - 1;
        }
    }

    public boolean close(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        this.mCameraCacheInfoIndexCache.remove(cci.reader);
        boolean result = false;
        try {
            cci.session.close();
            cci.device.close();
            cci.reader.close();
            result = true;
        } catch (Exception e) {
        }
        cci.session = null;
        cci.reader = null;
        cci.images = null;
        cci.status = AR_CAMERA_STATUS_UNINITIALIZED;
        System.gc();
        return result;
    }

    public int[] getCameraCapabilities(int cameraCacheInfoIndex) {
        Size[] arr$;
        if (!checkCameraManager()) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        } else if (cci.caps != null) {
            return cci.caps;
        } else {
            try {
                Size[] supportedImageSizes = ((StreamConfigurationMap) this.mCameraManager.getCameraCharacteristics(this.mCameraManager.getCameraIdList()[cci.deviceID]).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(35);
                List<Integer> supportedFrameRates = getSupportedPreviewFrameRates(cci.characteristics);
                int[] supportedFocusModes = (int[]) cci.characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
                Arrays.sort(supportedFocusModes);
                int[] supportedExposureModes = (int[]) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
                Arrays.sort(supportedExposureModes);
                LinkedList<Integer> linkedList = new LinkedList<>();
                linkedList.add(Integer.valueOf(35));
                int numSupportedImageSizes = supportedImageSizes != null ? supportedImageSizes.length : 0;
                int numSupportedFrameRates = supportedFrameRates != null ? supportedFrameRates.size() : 0;
                int numSupportedImageFormats = linkedList != null ? linkedList.size() : 0;
                float[] focalLengths = (float[]) cci.characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                boolean isZoomSupported = focalLengths != null && focalLengths.length > 0;
                if (isZoomSupported) {
                    float[] arr$2 = focalLengths;
                    int len$ = arr$2.length;
                    for (int i$ = 0; i$ < len$; i$++) {
                        DebugLog.LOGD(MODULENAME, "Supported Focal Length is " + arr$2[i$] + "mm");
                    }
                }
                Boolean isFlashTorchSupported = (Boolean) cci.characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                if (isFlashTorchSupported == null) {
                    isFlashTorchSupported = Boolean.valueOf(false);
                }
                Integer numSupportedFocusRegions = (Integer) cci.characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
                boolean isFocusRegionSupported = numSupportedFocusRegions != null && numSupportedFocusRegions.intValue() > 0;
                Range<Integer> aeCompensationRange = (Range) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
                boolean isCompensationSupported = aeCompensationRange != null && !EMPTY_RANGE.equals(aeCompensationRange);
                Range<Integer> sensitivityRange = (Range) cci.characteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
                boolean isSensitivitySupported = sensitivityRange != null && !EMPTY_RANGE.equals(sensitivityRange);
                boolean isExposureTimeSupported = sensitivityRange != null && !EMPTY_RANGE.equals((Range) cci.characteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE));
                int[] videoStabilizationModes = (int[]) cci.characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
                int[] opticalStabilizationModes = (int[]) cci.characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
                boolean isStabilizationSupported = (videoStabilizationModes != null && videoStabilizationModes.length > 1) || (opticalStabilizationModes != null && opticalStabilizationModes.length > 1);
                cci.caps = new int[((numSupportedImageSizes * 2) + 6 + numSupportedFrameRates + numSupportedImageFormats)];
                cci.caps[0] = AR_CAMERA_PARAMTYPE_BASE;
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_TORCHMODE, isFlashTorchSupported.booleanValue());
                boolean z = supportedFocusModes.length > 0;
                r1 = AR_CAMERA_PARAMTYPE_FOCUSMODE;
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSMODE, z);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSVALUE, isZoomSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSREGION, isFocusRegionSupported);
                boolean z2 = supportedExposureModes.length > 0;
                r1 = AR_CAMERA_PARAMTYPE_EXPOSUREMODE;
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSUREMODE, z2);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, isCompensationSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE, isCompensationSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ISO, isSensitivitySupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ISORANGE, isSensitivitySupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSURETIME, isExposureTimeSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE, isExposureTimeSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ZOOMVALUE, isZoomSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ZOOMRANGE, isZoomSupported);
                setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, isStabilizationSupported);
                cci.caps[1] = AR_CAMERA_PARAMTYPE_BASE;
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_TORCHMODE, isFlashTorchSupported.booleanValue());
                boolean z3 = supportedFocusModes.length > 0;
                r1 = AR_CAMERA_PARAMTYPE_FOCUSMODE;
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_FOCUSMODE, z3);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_FOCUSREGION, isFocusRegionSupported);
                boolean z4 = supportedExposureModes.length > 0;
                r1 = AR_CAMERA_PARAMTYPE_EXPOSUREMODE;
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_EXPOSUREMODE, z4);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, isCompensationSupported);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_ISO, isSensitivitySupported);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_EXPOSURETIME, isExposureTimeSupported);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_ZOOMVALUE, isZoomSupported);
                setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, isStabilizationSupported);
                cci.caps[2] = AR_CAMERA_PARAMVALUE_BASE;
                if (isFlashTorchSupported.booleanValue()) {
                    setCameraCapsBit(cci, 2, AR_CAMERA_TORCHMODE_OFF, true);
                    setCameraCapsBit(cci, 2, AR_CAMERA_TORCHMODE_ON, true);
                }
                if (supportedFocusModes != null) {
                    boolean z5 = Arrays.binarySearch(supportedFocusModes, 1) != -1;
                    r1 = AR_CAMERA_FOCUSMODE_NORMAL;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_NORMAL, z5);
                    boolean z6 = Arrays.binarySearch(supportedFocusModes, 1) != -1;
                    r1 = AR_CAMERA_FOCUSMODE_AUTO;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_AUTO, z6);
                    boolean z7 = Arrays.binarySearch(supportedFocusModes, 3) != -1;
                    r1 = AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO, z7);
                    boolean z8 = Arrays.binarySearch(supportedFocusModes, 2) != -1;
                    r1 = AR_CAMERA_FOCUSMODE_MACRO;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_MACRO, z8);
                    boolean z9 = (Arrays.binarySearch(supportedFocusModes, 0) == -1 || CaptureRequest.LENS_FOCUS_DISTANCE == null) ? false : true;
                    r1 = AR_CAMERA_FOCUSMODE_INFINITY;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_INFINITY, z9);
                    boolean z10 = Arrays.binarySearch(supportedFocusModes, 0) != -1;
                    r1 = AR_CAMERA_FOCUSMODE_FIXED;
                    setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_FIXED, z10);
                }
                if (supportedExposureModes != null) {
                    boolean z11 = Arrays.binarySearch(supportedExposureModes, 0) != -1;
                    r1 = AR_CAMERA_EXPOSUREMODE_LOCKED;
                    setCameraCapsBit(cci, 2, AR_CAMERA_EXPOSUREMODE_LOCKED, z11);
                    boolean z12 = Arrays.binarySearch(supportedExposureModes, 0) != -1;
                    r1 = AR_CAMERA_EXPOSUREMODE_MANUAL;
                    setCameraCapsBit(cci, 2, AR_CAMERA_EXPOSUREMODE_MANUAL, z12);
                    boolean z13 = Arrays.binarySearch(supportedExposureModes, 1) != -1;
                    r1 = AR_CAMERA_EXPOSUREMODE_AUTO;
                    setCameraCapsBit(cci, 2, AR_CAMERA_EXPOSUREMODE_AUTO, z13);
                    boolean z14 = Arrays.binarySearch(supportedExposureModes, 1) != -1;
                    r1 = AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO;
                    setCameraCapsBit(cci, 2, AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO, z14);
                }
                cci.caps[3] = numSupportedImageSizes;
                cci.caps[4] = numSupportedFrameRates;
                cci.caps[5] = numSupportedImageFormats;
                int indexOffset = 6;
                if (numSupportedImageSizes > 0) {
                    for (Size size : supportedImageSizes) {
                        cci.caps[indexOffset] = size.getWidth();
                        cci.caps[indexOffset + 1] = size.getHeight();
                        indexOffset += 2;
                    }
                }
                if (numSupportedFrameRates > 0) {
                    for (Integer framerate : supportedFrameRates) {
                        cci.caps[indexOffset] = framerate.intValue();
                        indexOffset++;
                    }
                }
                if (numSupportedImageFormats > 0) {
                    for (Integer format : linkedList) {
                        cci.caps[indexOffset] = translateImageFormat(format.intValue(), true);
                        indexOffset++;
                    }
                }
                return cci.caps;
            } catch (CameraAccessException e) {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
        }
    }

    public boolean setCaptureInfo(int cameraCacheInfoIndex, int[] captureInfo, int[] overrideCaptureInfo) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (captureInfo.length != 5) {
            SystemTools.setSystemErrorCode(2);
            return false;
        } else if (setCameraCaptureParams(cci, captureInfo, overrideCaptureInfo)) {
            return true;
        } else {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public int[] getCaptureInfo(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        try {
            int[] captureInfo = new int[5];
            if (cci.reader != null) {
                captureInfo[0] = cci.reader.getWidth();
                captureInfo[1] = cci.reader.getHeight();
                captureInfo[2] = translateImageFormat(cci.reader.getImageFormat(), CONVERT_FORMAT_TO_PL);
            } else {
                captureInfo[0] = cci.requestWidth;
                captureInfo[1] = cci.requestHeight;
                captureInfo[2] = translateImageFormat(cci.requestFormatAndroid, CONVERT_FORMAT_TO_PL);
            }
            if (cci.builder != null) {
                captureInfo[3] = ((Integer) ((Range) cci.builder.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE)).getUpper()).intValue();
            } else {
                captureInfo[3] = cci.requestFramerate;
            }
            captureInfo[4] = 1;
            return captureInfo;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    public boolean start(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (!setupPreviewBuffer(cci)) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else {
            try {
                if (cci.session == null) {
                    this.mOpenCloseSemaphore.acquire();
                    cci.device.createCaptureSession(cci.surfaces, new CameraCaptureSession.StateCallback() {
                        public void onConfigured(CameraCaptureSession session) {
                            CameraCacheInfo _cci = null;
                            Iterator i$ = Camera2_Preview.this.mCameraCacheInfos.iterator();
                            while (true) {
                                if (!i$.hasNext()) {
                                    break;
                                }
                                CameraCacheInfo info = (CameraCacheInfo) i$.next();
                                if (info.deviceIDString.equals(session.getDevice().getId())) {
                                    _cci = info;
                                    break;
                                }
                            }
                            _cci.session = session;
                            for (Surface s : _cci.surfaces) {
                                _cci.builder.addTarget(s);
                            }
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }

                        public void onConfigureFailed(CameraCaptureSession session) {
                            session.close();
                            Camera2_Preview.this.mOpenCloseSemaphore.release();
                        }
                    }, cci.handler);
                    this.mOpenCloseSemaphore.acquire();
                    this.mOpenCloseSemaphore.release();
                    if (cci.session == null) {
                        SystemTools.setSystemErrorCode(6);
                        return false;
                    }
                }
                cci.session.setRepeatingRequest(cci.builder.build(), new OnFrameCapturedCallback(cci), cci.handler);
                cci.status = AR_CAMERA_STATUS_CAPTURE_RUNNING;
                this.mCameraCacheInfoIndexCache.put(cci.reader, Integer.valueOf(cameraCacheInfoIndex));
                return true;
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
    }

    public boolean stop(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        try {
            cci.session.abortCaptures();
            cci.status = AR_CAMERA_STATUS_OPENED;
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public boolean setBatchParameters(int cameraCacheInfoIndex, String customData) {
        if (customData == null) {
            return false;
        }
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (setCustomCameraParams(cci, customData)) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean setUntypedCameraParameter(int cameraCacheInfoIndex, String name, Object value) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null || cci.characteristics == null || name == null || value == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        List<Key<?>> captureRequestKeys = cci.characteristics.getAvailableCaptureRequestKeys();
        int i = 0;
        while (i < captureRequestKeys.size()) {
            Key key = (Key) captureRequestKeys.get(i);
            if (!key.getName().equals(name)) {
                i++;
            } else {
                Object result = cci.builder.get(key);
                if (!(result instanceof Integer) && !(result instanceof Float) && !(result instanceof Boolean) && !(result instanceof Byte) && !(result instanceof Long)) {
                    return false;
                }
                if ((result instanceof Byte) && (value instanceof Long)) {
                    value = new Byte(((Long) value).byteValue());
                }
                if ((result instanceof Integer) && (value instanceof Long)) {
                    value = new Integer(((Long) value).intValue());
                }
                if (!result.getClass().equals(value.getClass())) {
                    return false;
                }
                try {
                    cci.builder.set(key, value);
                    if (cci.session != null) {
                        cci.session.setRepeatingRequest(cci.builder.build(), new OnFrameCapturedCallback(cci), cci.handler);
                    }
                    return true;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            }
        }
        SystemTools.setSystemErrorCode(6);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Object getUntypedCameraParameter(int cameraCacheInfoIndex, String name) {
        Object obj;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null || cci.characteristics == null || name == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Object result = null;
        List<Key<?>> captureRequestKeys = cci.characteristics.getAvailableCaptureRequestKeys();
        int i = 0;
        while (true) {
            if (i >= captureRequestKeys.size()) {
                break;
            }
            Key key = (Key) captureRequestKeys.get(i);
            if (key.getName().equals(name)) {
                result = cci.builder.get(key);
                break;
            }
            i++;
        }
        List<CameraCharacteristics.Key<?>> cameraCharacteristicsKeys = cci.characteristics.getKeys();
        int i2 = 0;
        while (true) {
            if (i2 >= cameraCharacteristicsKeys.size()) {
                obj = result;
                break;
            }
            CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) cameraCharacteristicsKeys.get(i2);
            if (key2.getName().equals(name)) {
                obj = cci.characteristics.get(key2);
                break;
            }
            i2++;
        }
        if (obj == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        } else if ((obj instanceof Long) || (obj instanceof Float) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        } else {
            if (obj instanceof Integer) {
                return new Long(((Integer) obj).longValue());
            }
            if (obj instanceof Byte) {
                return new Long(((Byte) obj).longValue());
            }
            if (obj instanceof Range) {
                Comparable lower = ((Range) obj).getLower();
                Comparable upper = ((Range) obj).getUpper();
                if (lower instanceof Integer) {
                    return new long[]{((Integer) lower).longValue(), ((Integer) upper).longValue()};
                } else if (lower instanceof Long) {
                    return new long[]{((Long) lower).longValue(), ((Long) upper).longValue()};
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            } else {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int getUntypedCameraParameterType(int cameraCacheInfoIndex, String name) {
        Object obj;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null || cci.characteristics == null || name == null) {
            SystemTools.setSystemErrorCode(4);
            return -1;
        }
        Object paramValue = null;
        boolean paramFound = false;
        List<Key<?>> captureRequestKeys = cci.characteristics.getAvailableCaptureRequestKeys();
        int i = 0;
        while (true) {
            if (i >= captureRequestKeys.size()) {
                break;
            }
            Key key = (Key) captureRequestKeys.get(i);
            if (key.getName().equals(name)) {
                paramValue = cci.builder.get(key);
                paramFound = true;
                break;
            }
            i++;
        }
        List<CameraCharacteristics.Key<?>> cameraCharacteristicsKeys = cci.characteristics.getKeys();
        int i2 = 0;
        while (true) {
            if (i2 >= cameraCharacteristicsKeys.size()) {
                obj = paramValue;
                break;
            }
            CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) cameraCharacteristicsKeys.get(i2);
            if (key2.getName().equals(name)) {
                paramFound = true;
                obj = cci.characteristics.get(key2);
                break;
            }
            i2++;
        }
        if (!paramFound) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (obj == null) {
            return -1;
        } else {
            if (obj instanceof Integer) {
                return 1;
            }
            if (obj instanceof Byte) {
                return 1;
            }
            if (obj instanceof Long) {
                return 1;
            }
            if (obj instanceof Float) {
                return 2;
            }
            if (obj instanceof Boolean) {
                return 3;
            }
            if (obj instanceof String) {
                return 0;
            }
            if (obj instanceof Range) {
                Comparable lower = ((Range) obj).getLower();
                if (lower instanceof Integer) {
                    return 4;
                }
                if (lower instanceof Long) {
                    return 4;
                }
            }
            return -1;
        }
    }

    /* access modifiers changed from: 0000 */
    public int getNamedParameterCount(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null || cci.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return -1;
        }
        return cci.characteristics.getAvailableCaptureRequestKeys().size() + cci.characteristics.getKeys().size();
    }

    /* access modifiers changed from: 0000 */
    public String getNamedParameter(int cameraCacheInfoIndex, int index) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.builder == null || cci.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        List<Key<?>> captureRequestKeys = cci.characteristics.getAvailableCaptureRequestKeys();
        List<CameraCharacteristics.Key<?>> cameraCharacteristicsKeys = cci.characteristics.getKeys();
        if (index < captureRequestKeys.size()) {
            Key key = (Key) captureRequestKeys.get(index);
            if (key != null) {
                return key.getName();
            }
            return null;
        } else if (index - captureRequestKeys.size() < cameraCharacteristicsKeys.size()) {
            CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) cameraCharacteristicsKeys.get(index - captureRequestKeys.size());
            if (key2 != null) {
                return key2.getName();
            }
            return null;
        } else {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x03d9 A[Catch:{ Exception -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03e8 A[Catch:{ Exception -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0417 A[Catch:{ Exception -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0426 A[Catch:{ Exception -> 0x00a4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setTypedCameraParameter(int r57, int r58, java.lang.Object r59) {
        /*
            r56 = this;
            com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo r8 = r56.getCameraCacheInfo(r57)
            if (r8 == 0) goto L_0x0012
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder
            r48 = r0
            if (r48 == 0) goto L_0x0012
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics
            r48 = r0
            if (r48 != 0) goto L_0x001a
        L_0x0012:
            r48 = 4
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)
            r48 = 0
        L_0x0019:
            return r48
        L_0x001a:
            r9 = 0
            switch(r58) {
                case 536870913: goto L_0x0077;
                case 536870914: goto L_0x00c7;
                case 536870916: goto L_0x01ef;
                case 536870920: goto L_0x0217;
                case 536870928: goto L_0x0220;
                case 536870944: goto L_0x0394;
                case 536870976: goto L_0x0437;
                case 536871040: goto L_0x047e;
                case 536871168: goto L_0x0487;
                case 536871424: goto L_0x04dc;
                case 536871936: goto L_0x04e5;
                case 536872960: goto L_0x054e;
                case 536875008: goto L_0x0557;
                case 536879104: goto L_0x05c1;
                case 536887296: goto L_0x05ca;
                case 536903680: goto L_0x05d3;
                case 536936448: goto L_0x0634;
                case 537001984: goto L_0x063d;
                case 537133056: goto L_0x0646;
                case 537395200: goto L_0x064f;
                case 537919488: goto L_0x0658;
                case 538968064: goto L_0x0661;
                case 541065216: goto L_0x0021;
                case 553648128: goto L_0x066a;
                default: goto L_0x001e;
            }
        L_0x001e:
            r48 = 0
            goto L_0x0019
        L_0x0021:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x002d
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x002d:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r43 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r49 = r0
            android.hardware.camera2.CaptureRequest$Key r50 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT     // Catch:{ Exception -> 0x00a4 }
            if (r43 == 0) goto L_0x0074
            r48 = 3
        L_0x003d:
            java.lang.Integer r48 = java.lang.Integer.valueOf(r48)     // Catch:{ Exception -> 0x00a4 }
            r0 = r49
            r1 = r50
            r2 = r48
            r0.set(r1, r2)     // Catch:{ Exception -> 0x00a4 }
        L_0x004a:
            android.hardware.camera2.CameraCaptureSession r0 = r8.session
            r48 = r0
            if (r48 == 0) goto L_0x0071
            android.hardware.camera2.CameraCaptureSession r0 = r8.session     // Catch:{ CameraAccessException -> 0x070f }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ CameraAccessException -> 0x070f }
            r49 = r0
            android.hardware.camera2.CaptureRequest r49 = r49.build()     // Catch:{ CameraAccessException -> 0x070f }
            com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback r50 = new com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback     // Catch:{ CameraAccessException -> 0x070f }
            r0 = r50
            r1 = r56
            r0.<init>(r8)     // Catch:{ CameraAccessException -> 0x070f }
            android.os.Handler r0 = r8.handler     // Catch:{ CameraAccessException -> 0x070f }
            r51 = r0
            r48.setRepeatingRequest(r49, r50, r51)     // Catch:{ CameraAccessException -> 0x070f }
            if (r9 == 0) goto L_0x0071
            switch(r58) {
                case 536870914: goto L_0x0719;
                case 536870928: goto L_0x078b;
                default: goto L_0x0071;
            }
        L_0x0071:
            r48 = 1
            goto L_0x0019
        L_0x0074:
            r48 = 1
            goto L_0x003d
        L_0x0077:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0083
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0083:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r42 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            switch(r42) {
                case 805306369: goto L_0x00ae;
                case 805306370: goto L_0x0094;
                case 805306371: goto L_0x008c;
                case 805306372: goto L_0x00be;
                default: goto L_0x008c;
            }     // Catch:{ Exception -> 0x00a4 }
        L_0x008c:
            r48 = 3
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0094:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 2
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x00a4:
            r10 = move-exception
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)
            r48 = 0
            goto L_0x0019
        L_0x00ae:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.FLASH_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x00be:
            r48 = 3
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x00c7:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x00d4
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x00d4:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r41 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            int[] r41 = (int[]) r41     // Catch:{ Exception -> 0x00a4 }
            java.util.Arrays.sort(r41)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r18 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            switch(r18) {
                case 805306384: goto L_0x00f5;
                case 805306400: goto L_0x00f5;
                case 805306432: goto L_0x0122;
                case 805306496: goto L_0x014e;
                case 805306624: goto L_0x017b;
                case 805306880: goto L_0x01c3;
                default: goto L_0x00ec;
            }     // Catch:{ Exception -> 0x00a4 }
        L_0x00ec:
            r48 = 3
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x00f5:
            r48 = 1
            r0 = r41
            r1 = r48
            int r48 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ Exception -> 0x00a4 }
            r49 = -1
            r0 = r48
            r1 = r49
            if (r0 != r1) goto L_0x0110
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0110:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            r9 = 1
            goto L_0x004a
        L_0x0122:
            r48 = 3
            r0 = r41
            r1 = r48
            int r48 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ Exception -> 0x00a4 }
            r49 = -1
            r0 = r48
            r1 = r49
            if (r0 != r1) goto L_0x013d
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x013d:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 3
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x014e:
            r48 = 2
            r0 = r41
            r1 = r48
            int r48 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ Exception -> 0x00a4 }
            r49 = -1
            r0 = r48
            r1 = r49
            if (r0 != r1) goto L_0x0169
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0169:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 2
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            r9 = 1
            goto L_0x004a
        L_0x017b:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0188
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0188:
            r48 = 0
            r0 = r41
            r1 = r48
            int r48 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ Exception -> 0x00a4 }
            r49 = -1
            r0 = r48
            r1 = r49
            if (r0 != r1) goto L_0x01a3
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x01a3:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Float r50 = java.lang.Float.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x01c3:
            r48 = 0
            r0 = r41
            r1 = r48
            int r48 = java.util.Arrays.binarySearch(r0, r1)     // Catch:{ Exception -> 0x00a4 }
            r49 = -1
            r0 = r48
            r1 = r49
            if (r0 != r1) goto L_0x01de
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x01de:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x01ef:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x01fc
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x01fc:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            float r48 = r59.floatValue()     // Catch:{ Exception -> 0x00a4 }
            java.lang.Float r17 = java.lang.Float.valueOf(r48)     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            r1 = r49
            r2 = r17
            r0.set(r1, r2)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x0217:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0220:
            float[] r59 = (float[]) r59     // Catch:{ Exception -> 0x00a4 }
            r0 = r59
            float[] r0 = (float[]) r0     // Catch:{ Exception -> 0x00a4 }
            r20 = r0
            r0 = r20
            int r0 = r0.length     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            r49 = 5
            r0 = r48
            r1 = r49
            if (r0 == r1) goto L_0x023e
            r48 = 2
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x023e:
            r48 = 0
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 0
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 < 0) goto L_0x02a2
            r48 = 0
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 1065353216(0x3f800000, float:1.0)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 > 0) goto L_0x02a2
            r48 = 1
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 0
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 < 0) goto L_0x02a2
            r48 = 1
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 1065353216(0x3f800000, float:1.0)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 > 0) goto L_0x02a2
            r48 = 2
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 0
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 < 0) goto L_0x02a2
            r48 = 2
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 1065353216(0x3f800000, float:1.0)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 > 0) goto L_0x02a2
            r48 = 3
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 0
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 < 0) goto L_0x02a2
            r48 = 3
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 1065353216(0x3f800000, float:1.0)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 > 0) goto L_0x02a2
            r48 = 4
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 0
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 < 0) goto L_0x02a2
            r48 = 4
            r48 = r20[r48]     // Catch:{ Exception -> 0x00a4 }
            r49 = 1065353216(0x3f800000, float:1.0)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 <= 0) goto L_0x02ab
        L_0x02a2:
            r48 = 2
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x02ab:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r28 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Integer r28 = (java.lang.Integer) r28     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x02c3
            if (r28 == 0) goto L_0x02c3
            int r48 = r28.intValue()     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x02cc
        L_0x02c3:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x02cc:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r5 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            android.graphics.Rect r5 = (android.graphics.Rect) r5     // Catch:{ Exception -> 0x00a4 }
            if (r5 != 0) goto L_0x02e3
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x02e3:
            r48 = 1
            r0 = r48
            android.hardware.camera2.params.MeteringRectangle[] r0 = new android.hardware.camera2.params.MeteringRectangle[r0]     // Catch:{ Exception -> 0x00a4 }
            r19 = r0
            r48 = 0
            android.hardware.camera2.params.MeteringRectangle r49 = new android.hardware.camera2.params.MeteringRectangle     // Catch:{ Exception -> 0x00a4 }
            android.graphics.Rect r50 = new android.graphics.Rect     // Catch:{ Exception -> 0x00a4 }
            r51 = 0
            r51 = r20[r51]     // Catch:{ Exception -> 0x00a4 }
            int r52 = r5.width()     // Catch:{ Exception -> 0x00a4 }
            r0 = r52
            float r0 = (float) r0     // Catch:{ Exception -> 0x00a4 }
            r52 = r0
            float r51 = r51 * r52
            r0 = r51
            int r0 = (int) r0     // Catch:{ Exception -> 0x00a4 }
            r51 = r0
            int r52 = r5.width()     // Catch:{ Exception -> 0x00a4 }
            int r52 = r52 + -1
            int r51 = java.lang.Math.min(r51, r52)     // Catch:{ Exception -> 0x00a4 }
            r52 = 1
            r52 = r20[r52]     // Catch:{ Exception -> 0x00a4 }
            int r53 = r5.height()     // Catch:{ Exception -> 0x00a4 }
            r0 = r53
            float r0 = (float) r0     // Catch:{ Exception -> 0x00a4 }
            r53 = r0
            float r52 = r52 * r53
            r0 = r52
            int r0 = (int) r0     // Catch:{ Exception -> 0x00a4 }
            r52 = r0
            int r53 = r5.height()     // Catch:{ Exception -> 0x00a4 }
            int r53 = r53 + -1
            int r52 = java.lang.Math.min(r52, r53)     // Catch:{ Exception -> 0x00a4 }
            r53 = 2
            r53 = r20[r53]     // Catch:{ Exception -> 0x00a4 }
            int r54 = r5.width()     // Catch:{ Exception -> 0x00a4 }
            r0 = r54
            float r0 = (float) r0     // Catch:{ Exception -> 0x00a4 }
            r54 = r0
            float r53 = r53 * r54
            r0 = r53
            int r0 = (int) r0     // Catch:{ Exception -> 0x00a4 }
            r53 = r0
            int r54 = r5.width()     // Catch:{ Exception -> 0x00a4 }
            int r54 = r54 + -1
            int r53 = java.lang.Math.min(r53, r54)     // Catch:{ Exception -> 0x00a4 }
            r54 = 3
            r54 = r20[r54]     // Catch:{ Exception -> 0x00a4 }
            int r55 = r5.height()     // Catch:{ Exception -> 0x00a4 }
            r0 = r55
            float r0 = (float) r0     // Catch:{ Exception -> 0x00a4 }
            r55 = r0
            float r54 = r54 * r55
            r0 = r54
            int r0 = (int) r0     // Catch:{ Exception -> 0x00a4 }
            r54 = r0
            int r55 = r5.height()     // Catch:{ Exception -> 0x00a4 }
            int r55 = r55 + -1
            int r54 = java.lang.Math.min(r54, r55)     // Catch:{ Exception -> 0x00a4 }
            r50.<init>(r51, r52, r53, r54)     // Catch:{ Exception -> 0x00a4 }
            r51 = 4
            r51 = r20[r51]     // Catch:{ Exception -> 0x00a4 }
            r52 = 1148846080(0x447a0000, float:1000.0)
            float r51 = r51 * r52
            r52 = 0
            float r51 = r51 + r52
            r0 = r51
            int r0 = (int) r0     // Catch:{ Exception -> 0x00a4 }
            r51 = r0
            r49.<init>(r50, r51)     // Catch:{ Exception -> 0x00a4 }
            r19[r48] = r49     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            r1 = r49
            r2 = r19
            r0.set(r1, r2)     // Catch:{ Exception -> 0x00a4 }
            r9 = 1
            goto L_0x004a
        L_0x0394:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r14 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r6 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            int[] r6 = (int[]) r6     // Catch:{ Exception -> 0x00a4 }
            if (r6 == 0) goto L_0x03ac
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x03b5
        L_0x03ac:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x03b5:
            switch(r14) {
                case 805310464: goto L_0x03c1;
                case 805314560: goto L_0x03f9;
                case 805322752: goto L_0x03f9;
                case 805339136: goto L_0x03c1;
                default: goto L_0x03b8;
            }     // Catch:{ Exception -> 0x00a4 }
        L_0x03b8:
            r48 = 3
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x03c1:
            r21 = 0
            r7 = r6
            int r0 = r7.length     // Catch:{ Exception -> 0x00a4 }
            r24 = r0
            r23 = 0
        L_0x03c9:
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x03d7
            r26 = r7[r23]     // Catch:{ Exception -> 0x00a4 }
            if (r26 != 0) goto L_0x03e2
            r21 = 1
        L_0x03d5:
            if (r21 == 0) goto L_0x03e5
        L_0x03d7:
            if (r21 != 0) goto L_0x03e8
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x03e2:
            r21 = 0
            goto L_0x03d5
        L_0x03e5:
            int r23 = r23 + 1
            goto L_0x03c9
        L_0x03e8:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x03f9:
            r22 = 0
            r7 = r6
            int r0 = r7.length     // Catch:{ Exception -> 0x00a4 }
            r24 = r0
            r23 = 0
        L_0x0401:
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x0415
            r26 = r7[r23]     // Catch:{ Exception -> 0x00a4 }
            r48 = 1
            r0 = r26
            r1 = r48
            if (r0 != r1) goto L_0x0420
            r22 = 1
        L_0x0413:
            if (r22 == 0) goto L_0x0423
        L_0x0415:
            if (r22 != 0) goto L_0x0426
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0420:
            r22 = 0
            goto L_0x0413
        L_0x0423:
            int r23 = r23 + 1
            goto L_0x0401
        L_0x0426:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x0437:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r31 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            android.util.Range r31 = (android.util.Range) r31     // Catch:{ Exception -> 0x00a4 }
            if (r31 == 0) goto L_0x0449
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0452
        L_0x0449:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0452:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r4 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            java.lang.Integer r48 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x00a4 }
            r0 = r31
            r1 = r48
            boolean r48 = r0.contains(r1)     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x0475
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY     // Catch:{ Exception -> 0x00a4 }
            java.lang.Integer r50 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x0475:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x047e:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0487:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r32 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            android.util.Range r32 = (android.util.Range) r32     // Catch:{ Exception -> 0x00a4 }
            if (r32 == 0) goto L_0x0499
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x04a2
        L_0x0499:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x04a2:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            float r48 = r59.floatValue()     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            double r0 = (double) r0     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            r50 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            double r48 = r48 * r50
            long r48 = java.lang.Math.round(r48)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Long r4 = java.lang.Long.valueOf(r48)     // Catch:{ Exception -> 0x00a4 }
            r0 = r32
            boolean r48 = r0.contains(r4)     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x04d3
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            r1 = r49
            r0.set(r1, r4)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x04d3:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x04dc:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x04e5:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            float r16 = r59.floatValue()     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r12 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            android.util.Range r12 = (android.util.Range) r12     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r15 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            android.util.Rational r15 = (android.util.Rational) r15     // Catch:{ Exception -> 0x00a4 }
            android.util.Range<java.lang.Integer> r48 = EMPTY_RANGE     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            boolean r48 = r0.equals(r12)     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0517
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x0517
            if (r15 == 0) goto L_0x0517
            android.hardware.camera2.CameraCharacteristics$Key r48 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0520
        L_0x0517:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0520:
            float r48 = r15.floatValue()     // Catch:{ Exception -> 0x00a4 }
            float r48 = r16 / r48
            int r13 = java.lang.Math.round(r48)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Integer r48 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00a4 }
            r0 = r48
            boolean r48 = r12.contains(r0)     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x0545
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION     // Catch:{ Exception -> 0x00a4 }
            java.lang.Integer r50 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x0545:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x054e:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0557:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r45 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            switch(r45) {
                case 806354944: goto L_0x0569;
                case 807403520: goto L_0x0587;
                case 809500672: goto L_0x0587;
                default: goto L_0x0560;
            }     // Catch:{ Exception -> 0x00a4 }
        L_0x0560:
            r48 = 3
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0569:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x0576
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0576:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Boolean r50 = java.lang.Boolean.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x0587:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x05ac
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r48 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            java.lang.Boolean r48 = (java.lang.Boolean) r48     // Catch:{ Exception -> 0x00a4 }
            boolean r48 = r48.booleanValue()     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x05ac
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Boolean r50 = java.lang.Boolean.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
        L_0x05ac:
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x004a
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x05c1:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x05ca:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x05d3:
            java.lang.Number r59 = (java.lang.Number) r59     // Catch:{ Exception -> 0x00a4 }
            int r46 = r59.intValue()     // Catch:{ Exception -> 0x00a4 }
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r47 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            float[] r47 = (float[]) r47     // Catch:{ Exception -> 0x00a4 }
            if (r47 == 0) goto L_0x05eb
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH     // Catch:{ Exception -> 0x00a4 }
            if (r48 != 0) goto L_0x05f4
        L_0x05eb:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x05f4:
            r38 = 0
            r7 = r47
            int r0 = r7.length     // Catch:{ Exception -> 0x00a4 }
            r24 = r0
            r23 = 0
        L_0x05fd:
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x0626
            r30 = r7[r23]     // Catch:{ Exception -> 0x00a4 }
            r0 = r46
            float r0 = (float) r0     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            float r48 = r30 - r48
            float r48 = java.lang.Math.abs(r48)     // Catch:{ Exception -> 0x00a4 }
            r49 = 1008981770(0x3c23d70a, float:0.01)
            int r48 = (r48 > r49 ? 1 : (r48 == r49 ? 0 : -1))
            if (r48 >= 0) goto L_0x0631
            r38 = 1
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH     // Catch:{ Exception -> 0x00a4 }
            java.lang.Float r50 = java.lang.Float.valueOf(r30)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
        L_0x0626:
            if (r38 != 0) goto L_0x004a
            r48 = 2
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0631:
            int r23 = r23 + 1
            goto L_0x05fd
        L_0x0634:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x063d:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0646:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x064f:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0658:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x0661:
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x066a:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r29 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            int[] r29 = (int[]) r29     // Catch:{ Exception -> 0x00a4 }
            if (r29 == 0) goto L_0x06b9
            r0 = r29
            int r0 = r0.length     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            r49 = 1
            r0 = r48
            r1 = r49
            if (r0 <= r1) goto L_0x06b9
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x06b9
            r39 = 1
        L_0x068b:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES     // Catch:{ Exception -> 0x00a4 }
            java.lang.Object r44 = r48.get(r49)     // Catch:{ Exception -> 0x00a4 }
            int[] r44 = (int[]) r44     // Catch:{ Exception -> 0x00a4 }
            if (r44 == 0) goto L_0x06bc
            r0 = r44
            int r0 = r0.length     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            r49 = 1
            r0 = r48
            r1 = r49
            if (r0 <= r1) goto L_0x06bc
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            if (r48 == 0) goto L_0x06bc
            r40 = 1
        L_0x06ac:
            if (r39 != 0) goto L_0x06bf
            if (r40 != 0) goto L_0x06bf
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x00a4 }
            r48 = 0
            goto L_0x0019
        L_0x06b9:
            r39 = 0
            goto L_0x068b
        L_0x06bc:
            r40 = 0
            goto L_0x06ac
        L_0x06bf:
            java.lang.Boolean r59 = (java.lang.Boolean) r59     // Catch:{ Exception -> 0x00a4 }
            boolean r11 = r59.booleanValue()     // Catch:{ Exception -> 0x00a4 }
            if (r39 == 0) goto L_0x06d6
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
        L_0x06d6:
            if (r40 == 0) goto L_0x06e7
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 0
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
        L_0x06e7:
            if (r11 == 0) goto L_0x004a
            if (r39 == 0) goto L_0x06fc
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x06fc:
            if (r40 == 0) goto L_0x004a
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x00a4 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE     // Catch:{ Exception -> 0x00a4 }
            r50 = 1
            java.lang.Integer r50 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x00a4 }
            r48.set(r49, r50)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x004a
        L_0x070f:
            r10 = move-exception
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)
            r48 = 0
            goto L_0x0019
        L_0x0719:
            android.hardware.camera2.CameraCharacteristics r0 = r8.characteristics     // Catch:{ Exception -> 0x07a3 }
            r48 = r0
            android.hardware.camera2.CameraCharacteristics$Key r49 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x07a3 }
            java.lang.Object r25 = r48.get(r49)     // Catch:{ Exception -> 0x07a3 }
            java.lang.Integer r25 = (java.lang.Integer) r25     // Catch:{ Exception -> 0x07a3 }
            if (r25 == 0) goto L_0x078b
            android.hardware.camera2.CameraCharacteristics$Key r48 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF     // Catch:{ Exception -> 0x07a3 }
            if (r48 == 0) goto L_0x078b
            int r48 = r25.intValue()     // Catch:{ Exception -> 0x07a3 }
            if (r48 <= 0) goto L_0x078b
            android.hardware.camera2.CaptureRequest$Key r48 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x07a3 }
            if (r48 == 0) goto L_0x078b
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x07a3 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x07a3 }
            java.lang.Object r36 = r48.get(r49)     // Catch:{ Exception -> 0x07a3 }
            android.hardware.camera2.params.MeteringRectangle[] r36 = (android.hardware.camera2.params.MeteringRectangle[]) r36     // Catch:{ Exception -> 0x07a3 }
            if (r36 == 0) goto L_0x078b
            r0 = r36
            int r0 = r0.length     // Catch:{ Exception -> 0x07a3 }
            r48 = r0
            if (r48 <= 0) goto L_0x078b
            r0 = r36
            int r0 = r0.length     // Catch:{ Exception -> 0x07a3 }
            r48 = r0
            r0 = r48
            android.hardware.camera2.params.MeteringRectangle[] r0 = new android.hardware.camera2.params.MeteringRectangle[r0]     // Catch:{ Exception -> 0x07a3 }
            r27 = r0
            r34 = 0
            r7 = r36
            int r0 = r7.length     // Catch:{ Exception -> 0x07a3 }
            r24 = r0
            r23 = 0
            r35 = r34
        L_0x0760:
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x077c
            r33 = r7[r23]     // Catch:{ Exception -> 0x07a3 }
            int r34 = r35 + 1
            android.hardware.camera2.params.MeteringRectangle r48 = new android.hardware.camera2.params.MeteringRectangle     // Catch:{ Exception -> 0x07a3 }
            android.graphics.Rect r49 = r33.getRect()     // Catch:{ Exception -> 0x07a3 }
            r50 = 0
            r48.<init>(r49, r50)     // Catch:{ Exception -> 0x07a3 }
            r27[r35] = r48     // Catch:{ Exception -> 0x07a3 }
            int r23 = r23 + 1
            r35 = r34
            goto L_0x0760
        L_0x077c:
            android.hardware.camera2.CaptureRequest$Builder r0 = r8.builder     // Catch:{ Exception -> 0x07a3 }
            r48 = r0
            android.hardware.camera2.CaptureRequest$Key r49 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS     // Catch:{ Exception -> 0x07a3 }
            r0 = r48
            r1 = r49
            r2 = r27
            r0.set(r1, r2)     // Catch:{ Exception -> 0x07a3 }
        L_0x078b:
            com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner r37 = new com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner     // Catch:{ Exception -> 0x07a3 }
            r0 = r37
            r1 = r56
            r0.<init>(r8)     // Catch:{ Exception -> 0x07a3 }
            boolean r48 = r37.triggerAutofocus()     // Catch:{ Exception -> 0x07a3 }
            if (r48 != 0) goto L_0x0071
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)     // Catch:{ Exception -> 0x07a3 }
            r48 = 0
            goto L_0x0019
        L_0x07a3:
            r10 = move-exception
            r48 = 6
            com.vuforia.p000ar.p001pl.SystemTools.setSystemErrorCode(r48)
            r48 = 0
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.p000ar.p001pl.Camera2_Preview.setTypedCameraParameter(int, int, java.lang.Object):boolean");
    }

    /* access modifiers changed from: 0000 */
    public Object getTypedCameraParameter(int cameraCacheInfoIndex, int type) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.characteristics == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        CaptureResult captureResult = cci.lastResult;
        switch (type) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                if (captureResult == null) {
                    try {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    } catch (Exception e) {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                } else {
                    Integer flashMode = (Integer) captureResult.get(CaptureResult.FLASH_MODE);
                    if (flashMode == null || CaptureResult.FLASH_MODE == null) {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    } else if (flashMode.equals(Integer.valueOf(2))) {
                        return Integer.valueOf(AR_CAMERA_TORCHMODE_ON);
                    } else {
                        if (flashMode.equals(Integer.valueOf(0))) {
                            return Integer.valueOf(AR_CAMERA_TORCHMODE_OFF);
                        }
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer focusMode = (Integer) captureResult.get(CaptureResult.CONTROL_AF_MODE);
                if (focusMode == null || CaptureResult.CONTROL_AF_MODE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                } else if (focusMode.equals(Integer.valueOf(1))) {
                    return Integer.valueOf(cci.isAutoFocusing ? AR_CAMERA_FOCUSMODE_AUTO : AR_CAMERA_FOCUSMODE_NORMAL);
                } else if (focusMode.equals(Integer.valueOf(3))) {
                    return Integer.valueOf(AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO);
                } else {
                    if (focusMode.equals(Integer.valueOf(0))) {
                        Float focusDist = (Float) captureResult.get(CaptureResult.LENS_FOCUS_DISTANCE);
                        if (focusDist == null || CaptureResult.LENS_FOCUS_DISTANCE == null || !focusDist.equals(Float.valueOf(0.0f))) {
                            return Integer.valueOf(AR_CAMERA_FOCUSMODE_FIXED);
                        }
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_INFINITY);
                    } else if (focusMode.equals(Integer.valueOf(2))) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_MACRO);
                    } else {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Float focalLen = (Float) captureResult.get(CaptureResult.LENS_FOCAL_LENGTH);
                if (focalLen != null && CaptureResult.LENS_FOCAL_LENGTH != null) {
                    return focalLen;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Pair<Float, Float> focusRange = (Pair) captureResult.get(CaptureResult.LENS_FOCUS_RANGE);
                if (focusRange == null || CaptureResult.LENS_FOCUS_RANGE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new float[]{((Float) focusRange.first).floatValue(), ((Float) focusRange.second).floatValue()};
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer maxRegions = (Integer) cci.characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
                if (maxRegions == null || CameraCharacteristics.CONTROL_MAX_REGIONS_AF == null || maxRegions.intValue() <= 0 || CaptureResult.CONTROL_AF_REGIONS == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                MeteringRectangle[] regions = (MeteringRectangle[]) captureResult.get(CaptureResult.CONTROL_AF_REGIONS);
                if (regions == null || regions.length == 0) {
                    return null;
                }
                Rect activeArraySize = (Rect) cci.characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
                if (activeArraySize == null) {
                    SystemTools.setSystemErrorCode(6);
                    return Boolean.valueOf(false);
                }
                Rect focusRegionRect = regions[0].getRect();
                return new float[]{(float) (focusRegionRect.left / (activeArraySize.width() - 1)), (float) (focusRegionRect.top / (activeArraySize.height() - 1)), (float) (focusRegionRect.right / (activeArraySize.width() - 1)), (float) (focusRegionRect.bottom / (activeArraySize.height() - 1)), (float) ((regions[0].getMeteringWeight() + 0) / 1000)};
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer exposureMode = (Integer) captureResult.get(CaptureResult.CONTROL_AE_MODE);
                if (exposureMode == null || CaptureResult.CONTROL_AE_MODE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                } else if (exposureMode.equals(Integer.valueOf(0))) {
                    return Integer.valueOf(AR_CAMERA_EXPOSUREMODE_MANUAL);
                } else {
                    if (exposureMode.equals(Integer.valueOf(1))) {
                        return Integer.valueOf(AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO);
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_ISO /*536870976*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer iso = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
                if (iso != null && CaptureResult.SENSOR_SENSITIVITY != null) {
                    return Float.valueOf(iso.floatValue());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ISORANGE /*536871040*/:
                Range<Integer> isoRange = (Range) cci.characteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
                if (isoRange == null || CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new float[]{((Integer) isoRange.getLower()).floatValue(), ((Integer) isoRange.getUpper()).floatValue()};
            case AR_CAMERA_PARAMTYPE_EXPOSURETIME /*536871168*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Long et = (Long) captureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                if (et != null && CaptureResult.SENSOR_EXPOSURE_TIME != null) {
                    return Float.valueOf((float) (et.doubleValue() / 1.0E9d));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE /*536871424*/:
                Range<Long> etRange = (Range) cci.characteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                if (etRange == null || CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new float[]{(float) (((Long) etRange.getLower()).doubleValue() / 1.0E9d), (float) (((Long) etRange.getUpper()).doubleValue() / 1.0E9d)};
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536871936*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Integer expComp = (Integer) captureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
                Rational exposureStep = (Rational) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                if (expComp != null && CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION != null && exposureStep != null && CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP != null) {
                    return Float.valueOf(exposureStep.floatValue() * ((float) expComp.intValue()));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE /*536872960*/:
                Range<Integer> expCompRange = (Range) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
                Rational exposureStep2 = (Rational) cci.characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                if (expCompRange == null || CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE == null || exposureStep2 == null || CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new float[]{((float) ((Integer) expCompRange.getLower()).intValue()) * exposureStep2.floatValue(), ((float) ((Integer) expCompRange.getUpper()).intValue()) * exposureStep2.floatValue()};
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE /*536875008*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE /*536879104*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE /*536887296*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMVALUE /*536903680*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                Float focalLen2 = (Float) captureResult.get(CaptureResult.LENS_FOCAL_LENGTH);
                if (focalLen2 != null && CaptureResult.LENS_FOCAL_LENGTH != null) {
                    return focalLen2;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536936448*/:
                SystemTools.setSystemErrorCode(6);
                break;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*537001984*/:
                break;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE /*537133056*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTVALUE /*537395200*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTRANGE /*537919488*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ROTATION /*538968064*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION /*553648128*/:
                if (captureResult == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                boolean supported = false;
                Integer vidStabMode = (Integer) captureResult.get(CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE);
                if (!(vidStabMode == null || CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE == null)) {
                    supported = true;
                    if (vidStabMode.equals(Integer.valueOf(1))) {
                        return Boolean.valueOf(true);
                    }
                }
                Integer opticalStabMode = (Integer) captureResult.get(CaptureResult.LENS_OPTICAL_STABILIZATION_MODE);
                if (!(opticalStabMode == null || CaptureResult.LENS_OPTICAL_STABILIZATION_MODE == null)) {
                    supported = true;
                    if (opticalStabMode.equals(Integer.valueOf(1))) {
                        return Boolean.valueOf(true);
                    }
                }
                if (supported) {
                    return Boolean.valueOf(false);
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            default:
                return null;
        }
        SystemTools.setSystemErrorCode(6);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int getStatus(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci != null) {
            return cci.status;
        }
        SystemTools.setSystemErrorCode(4);
        return AR_CAMERA_STATUS_UNKNOWN;
    }
}
