package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import com.vuforia.PIXEL_FORMAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.vuforia.ar.pl.Camera1_Preview */
public class Camera1_Preview implements PreviewCallback {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
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
    private static final int[] CAMERA_IMAGE_FORMAT_CONVERSIONTABLE = {16, AR_CAMERA_IMAGE_FORMAT_NV16, 17, AR_CAMERA_IMAGE_FORMAT_NV21, 4, AR_CAMERA_IMAGE_FORMAT_RGB565, 842094169, AR_CAMERA_IMAGE_FORMAT_YV12};
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = true;
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final String MODULENAME = "Camera1_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_CAPTURE_BUFFERS_TO_ADD = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final String SAMSUNG_PARAM_FAST_FPS_MODE = "fast-fps-mode";
    private static final String SAMSUNG_PARAM_VRMODE = "vrmode";
    private static final String SAMSUNG_PARAM_VRMODE_SUPPORTED = "vrmode-supported";
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    private Vector<CameraCacheInfo> cameraCacheInfo = null;
    /* access modifiers changed from: private */
    public HashMap<Camera, Integer> cameraCacheInfoIndexCache = null;
    private SurfaceManager surfaceManager = null;

    /* renamed from: com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo */
    public class CameraCacheInfo {
        byte[][] buffer;
        int bufferFormatPL;
        int bufferHeight;
        int bufferSize;
        int bufferWidth;
        Camera camera;
        int[] caps;
        long deviceHandle;
        int deviceID;
        boolean isAutoFocusing;
        boolean isHalDriven;
        int overrideFormatAndroid;
        int overrideHeight;
        int overrideWidth;
        int requestFormatAndroid;
        int requestHeight;
        int requestWidth;
        int status;
        CameraSurface surface;
        SurfaceTexture surfaceTexture;

        public CameraCacheInfo() {
        }
    }

    private native void newFrameAvailable(long j, int i, int i2, int i3, int i4, byte[] bArr, long j2);

    private boolean checkPermission() {
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity != null && activity.getPackageManager().checkPermission("android.permission.CAMERA", activity.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private int getCameraDeviceIndex(int camIndex, int type, int direction) {
        if (type != AR_CAMERA_TYPE_UNKNOWN) {
        }
        if (SystemTools.checkMinimumApiLevel(9)) {
            int camInfoDirection = -1;
            switch (direction) {
                case AR_CAMERA_DIRECTION_UNKNOWN /*268443664*/:
                    break;
                case AR_CAMERA_DIRECTION_BACK /*268443665*/:
                    camInfoDirection = 0;
                    break;
                case AR_CAMERA_DIRECTION_FRONT /*268443666*/:
                    camInfoDirection = 1;
                    break;
                default:
                    SystemTools.setSystemErrorCode(2);
                    return -1;
            }
            int num = Camera.getNumberOfCameras();
            int i = 0;
            while (i < num) {
                CameraInfo cameraInfo = new CameraInfo();
                try {
                    Camera.getCameraInfo(i, cameraInfo);
                    if ((camInfoDirection < 0 || camInfoDirection == cameraInfo.facing) && (camIndex < 0 || camIndex == i)) {
                        return i;
                    }
                } catch (Exception e) {
                }
                i++;
            }
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (direction == AR_CAMERA_DIRECTION_FRONT) {
            SystemTools.setSystemErrorCode(2);
            return -1;
        } else if (camIndex < 1) {
            return 0;
        } else {
            SystemTools.setSystemErrorCode(2);
            return -1;
        }
    }

    private Parameters getCameraParameters(Camera camera) {
        Parameters params = null;
        try {
            return camera.getParameters();
        } catch (Exception e) {
            return params;
        }
    }

    /* access modifiers changed from: private */
    public CameraCacheInfo getCameraCacheInfo(int cameraCacheInfoIndex) {
        if (cameraCacheInfoIndex < 0 || cameraCacheInfoIndex >= this.cameraCacheInfo.size()) {
            return null;
        }
        return (CameraCacheInfo) this.cameraCacheInfo.get(cameraCacheInfoIndex);
    }

    private boolean setCustomCameraParams(Parameters cameraParams, String customData) {
        try {
            JSONObject jsonObj = new JSONObject(customData);
            Iterator<?> elements = jsonObj.keys();
            while (elements.hasNext()) {
                String key = (String) elements.next();
                try {
                    Object value = jsonObj.get(key);
                    if (value.getClass() == String.class) {
                        cameraParams.set(key, (String) value);
                    } else if (value.getClass() == Integer.class) {
                        cameraParams.set(key, ((Integer) value).intValue());
                    } else {
                        JSONObject jSONObject = jsonObj;
                        return false;
                    }
                } catch (JSONException e) {
                    JSONObject jSONObject2 = jsonObj;
                    return false;
                }
            }
            JSONObject jSONObject3 = jsonObj;
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private boolean setCameraPreviewFps(int fps, Parameters params) {
        List<int[]> supportedFpsRanges = params.getSupportedPreviewFpsRange();
        int min_fps = fps * 1000;
        int[] selected_range = null;
        if ((fps == 60 || fps == 120) && "true".equalsIgnoreCase(params.get(SAMSUNG_PARAM_VRMODE_SUPPORTED))) {
            selected_range = new int[2];
            params.set(SAMSUNG_PARAM_VRMODE, 1);
            params.setRecordingHint(true);
            params.set("focus-mode", "continuous-video");
            if (fps == 60) {
                params.set(SAMSUNG_PARAM_FAST_FPS_MODE, 1);
                selected_range[0] = 60000;
                selected_range[1] = 60000;
            }
            if (fps == 120) {
                params.set(SAMSUNG_PARAM_FAST_FPS_MODE, 2);
                selected_range[0] = 120000;
                selected_range[1] = 120000;
            }
        } else {
            if (!(!"true".equalsIgnoreCase(params.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) || params.get(SAMSUNG_PARAM_FAST_FPS_MODE) == null || params.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) == 0)) {
                params.set(SAMSUNG_PARAM_VRMODE, 0);
                params.set(SAMSUNG_PARAM_FAST_FPS_MODE, 0);
            }
            for (int[] range : supportedFpsRanges) {
                if (range[0] == min_fps && range[1] - range[0] < Integer.MAX_VALUE) {
                    selected_range = range;
                }
            }
        }
        if (selected_range == null) {
            return false;
        }
        params.setPreviewFpsRange(selected_range[0], selected_range[1]);
        return true;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo camCacheInfo, Parameters camParams, int[] captureInfo, int[] overrideCaptureInfo) {
        boolean previewSurfaceEnabled;
        if (!(captureInfo == null && overrideCaptureInfo == null)) {
            camCacheInfo.overrideWidth = overrideCaptureInfo != null ? overrideCaptureInfo[0] : captureInfo[0];
            camCacheInfo.overrideHeight = overrideCaptureInfo != null ? overrideCaptureInfo[1] : captureInfo[1];
            camCacheInfo.overrideFormatAndroid = translateImageFormat(overrideCaptureInfo != null ? overrideCaptureInfo[2] : captureInfo[2], CONVERT_FORMAT_TO_ANDROID);
        }
        if (captureInfo == null) {
            return true;
        }
        camCacheInfo.requestWidth = captureInfo[0];
        camCacheInfo.requestHeight = captureInfo[1];
        camCacheInfo.requestFormatAndroid = translateImageFormat(captureInfo[2], CONVERT_FORMAT_TO_ANDROID);
        int framerate = captureInfo[3];
        try {
            if (camCacheInfo.requestWidth > 0 && camCacheInfo.requestHeight > 0) {
                camParams.setPreviewSize(camCacheInfo.requestWidth, camCacheInfo.requestHeight);
            }
            if (framerate > 0) {
                if (!SystemTools.checkMinimumApiLevel(8)) {
                    camParams.setPreviewFrameRate(framerate);
                } else if (!setCameraPreviewFps(framerate, camParams)) {
                    camParams.setPreviewFrameRate(framerate);
                }
            }
            if (camCacheInfo.requestFormatAndroid != 0) {
                camParams.setPreviewFormat(camCacheInfo.requestFormatAndroid);
            }
            if (captureInfo[4] > 0) {
                previewSurfaceEnabled = true;
            } else {
                previewSurfaceEnabled = false;
            }
            if (previewSurfaceEnabled) {
                if (SystemTools.checkMinimumApiLevel(11)) {
                    try {
                        camCacheInfo.surfaceTexture = new SurfaceTexture(-1);
                        try {
                            camCacheInfo.camera.setPreviewTexture(camCacheInfo.surfaceTexture);
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        return false;
                    }
                } else if (this.surfaceManager == null) {
                    return false;
                } else {
                    if (!this.surfaceManager.addCameraSurface(camCacheInfo)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e3) {
            return false;
        }
    }

    private boolean checkSamsungHighFPS(CameraCacheInfo cci) {
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        if ("true".equalsIgnoreCase(cameraParams.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) && cci.requestWidth > 0 && cci.requestHeight > 0 && cameraParams.get(SAMSUNG_PARAM_FAST_FPS_MODE) != null && cameraParams.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) != 0 && !(cci.requestWidth == cameraParams.getPreviewSize().width && cci.requestHeight == cameraParams.getPreviewSize().height)) {
            DebugLog.LOGW(MODULENAME, "Detected Samsung high fps camera driver bug.");
            DebugLog.LOGW(MODULENAME, "Preview size doesn't match request; width " + cci.requestWidth + "!=" + cameraParams.getPreviewSize().width + " or height " + cci.requestHeight + "!=" + cameraParams.getPreviewSize().height);
            setCameraPreviewFps(30, cameraParams);
            cameraParams.setPreviewSize(cci.requestWidth, cci.requestHeight);
            try {
                cci.camera.setParameters(cameraParams);
                Parameters cameraParams2 = getCameraParameters(cci.camera);
                if (!(cci.requestWidth == cameraParams2.getPreviewSize().width && cci.requestHeight == cameraParams2.getPreviewSize().height)) {
                    DebugLog.LOGE(MODULENAME, "Unable to workaround Samsung high fps camera driver bug.");
                    DebugLog.LOGE(MODULENAME, "Preview size doesn't match request; width " + cci.requestWidth + "!=" + cameraParams2.getPreviewSize().width + " or height " + cci.requestHeight + "!=" + cameraParams2.getPreviewSize().height);
                    return false;
                }
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
        return true;
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cci) {
        int bitsPerPixel;
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            return false;
        }
        try {
            cci.bufferWidth = cci.requestWidth == cci.overrideWidth ? cameraParams.getPreviewSize().width : cci.overrideWidth;
            cci.bufferHeight = cci.requestHeight == cci.overrideHeight ? cameraParams.getPreviewSize().height : cci.overrideHeight;
            int bufferFormatAndroid = cci.requestFormatAndroid == cci.overrideFormatAndroid ? cameraParams.getPreviewFormat() : cci.overrideFormatAndroid;
            cci.bufferFormatPL = translateImageFormat(bufferFormatAndroid, CONVERT_FORMAT_TO_PL);
            try {
                PixelFormat pixelFormatInfo = new PixelFormat();
                PixelFormat.getPixelFormatInfo(bufferFormatAndroid, pixelFormatInfo);
                bitsPerPixel = pixelFormatInfo.bitsPerPixel;
            } catch (Exception e) {
                bitsPerPixel = getBitsPerPixel(bufferFormatAndroid);
                if (bitsPerPixel == 0) {
                    return false;
                }
            }
            int bufferSize = (((cci.bufferWidth * cci.bufferHeight) * bitsPerPixel) / 8) + 4096;
            if (bufferSize <= cci.bufferSize) {
                cci.camera.setPreviewCallbackWithBuffer(this);
                return true;
            }
            cci.buffer = new byte[2][];
            for (int i = 0; i < 2; i++) {
                cci.buffer[i] = new byte[bufferSize];
                if (i < 2) {
                    cci.camera.addCallbackBuffer(cci.buffer[i]);
                }
            }
            cci.bufferSize = bufferSize;
            cci.camera.setPreviewCallbackWithBuffer(this);
            System.gc();
            return true;
        } catch (Exception e2) {
            return false;
        }
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

    public void onPreviewFrame(byte[] capturedBuffer, Camera camera) {
        long frameTimeStamp = System.nanoTime();
        if (SystemTools.checkMinimumApiLevel(18)) {
        }
        Object intObj = this.cameraCacheInfoIndexCache.get(camera);
        if (intObj == null) {
            if (SystemTools.checkMinimumApiLevel(18)) {
            }
            return;
        }
        int cameraCacheInfoIndex = ((Integer) intObj).intValue();
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            if (SystemTools.checkMinimumApiLevel(18)) {
            }
            return;
        }
        newFrameAvailable(cci.deviceHandle, cameraCacheInfoIndex, cci.bufferWidth, cci.bufferHeight, cci.bufferFormatPL, capturedBuffer, frameTimeStamp);
        camera.addCallbackBuffer(capturedBuffer);
        if (SystemTools.checkMinimumApiLevel(18)) {
        }
    }

    public boolean init() {
        this.cameraCacheInfo = new Vector<>();
        this.cameraCacheInfoIndexCache = new HashMap<>();
        return true;
    }

    public void setSurfaceManager(SurfaceManager sm) {
        this.surfaceManager = sm;
    }

    public int getNumberOfCameras() {
        int i;
        int i2 = -1;
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return i2;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            try {
                return Camera.getNumberOfCameras();
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return i2;
            }
        } else {
            try {
                if (SystemTools.getActivityFromNative().getPackageManager().hasSystemFeature("android.hardware.camera")) {
                    i = 1;
                } else {
                    i = 0;
                }
                return i;
            } catch (Exception e2) {
                SystemTools.setSystemErrorCode(6);
                return i2;
            }
        }
    }

    public int getOrientation(int cameraID) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            CameraInfo cameraInfo = new CameraInfo();
            try {
                Camera.getCameraInfo(cameraID, cameraInfo);
                return cameraInfo.orientation;
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        } else {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
    }

    public int getDirection(int cameraID) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            CameraInfo cameraInfo = new CameraInfo();
            try {
                Camera.getCameraInfo(cameraID, cameraInfo);
                switch (cameraInfo.facing) {
                    case 0:
                        r2 = AR_CAMERA_DIRECTION_BACK;
                        return AR_CAMERA_DIRECTION_BACK;
                    case 1:
                        return AR_CAMERA_DIRECTION_FRONT;
                    default:
                        return AR_CAMERA_DIRECTION_UNKNOWN;
                }
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
        } else {
            r2 = AR_CAMERA_DIRECTION_BACK;
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
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
        int cameraDeviceIndex = getCameraDeviceIndex(camIndex, type, direction);
        if (cameraDeviceIndex < 0) {
            return -1;
        }
        int cameraCacheInfoIndex = -1;
        CameraCacheInfo cci = null;
        int cameraCacheInfoSize = this.cameraCacheInfo.size();
        int i = 0;
        while (true) {
            if (i >= cameraCacheInfoSize) {
                break;
            }
            cci = (CameraCacheInfo) this.cameraCacheInfo.get(i);
            if (cci.deviceID == cameraDeviceIndex) {
                cameraCacheInfoIndex = i;
                break;
            }
            i++;
        }
        if (cameraCacheInfoIndex < 0) {
            cci = new CameraCacheInfo();
            cci.deviceID = cameraDeviceIndex;
            cci.deviceHandle = deviceHandle;
            cci.camera = null;
            cci.surface = null;
            cci.buffer = null;
            cci.overrideWidth = 0;
            cci.requestWidth = 0;
            cci.bufferWidth = 0;
            cci.overrideHeight = 0;
            cci.requestHeight = 0;
            cci.bufferHeight = 0;
            cci.bufferFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            cci.overrideFormatAndroid = 0;
            cci.requestFormatAndroid = 0;
            cci.caps = null;
            cci.status = AR_CAMERA_STATUS_UNINITIALIZED;
            cci.isAutoFocusing = false;
            cci.isHalDriven = false;
        }
        cci.bufferSize = 0;
        boolean resultCameraOpened = false;
        int cameraOpenRetryCount = NUM_MAX_CAMERAOPEN_RETRY;
        while (true) {
            int cameraOpenRetryCount2 = cameraOpenRetryCount;
            try {
                if (SystemTools.checkMinimumApiLevel(9)) {
                    cci.camera = Camera.open(cci.deviceID);
                } else if (cci.deviceID == 0) {
                    cci.camera = Camera.open();
                }
                resultCameraOpened = cci.camera != null;
            } catch (Exception e) {
            }
            if (!resultCameraOpened && cameraOpenRetryCount2 > 0) {
                try {
                    synchronized (this) {
                        wait(250);
                    }
                } catch (Exception e2) {
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
        if (cci.camera == null) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
        boolean setCaptureInfo = (captureInfo != null && captureInfo.length > 0) || (overrideCaptureInfo != null && overrideCaptureInfo.length > 0);
        boolean setCustomData = customData != null && customData.length() > 0;
        if (setCaptureInfo || setCustomData) {
            Parameters cameraParams = getCameraParameters(cci.camera);
            if (cameraParams == null) {
                SystemTools.setSystemErrorCode(6);
                return -1;
            }
            if (setCaptureInfo) {
                if (captureInfo != null && captureInfo.length != 5) {
                    SystemTools.setSystemErrorCode(2);
                    return -1;
                } else if (!setCameraCaptureParams(cci, cameraParams, captureInfo, overrideCaptureInfo)) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            }
            if (!setCustomData || setCustomCameraParams(cameraParams, customData)) {
                try {
                    cci.camera.setParameters(cameraParams);
                    if (!checkSamsungHighFPS(cci)) {
                        return -1;
                    }
                } catch (Exception e3) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                }
            } else {
                SystemTools.setSystemErrorCode(2);
                return -1;
            }
        }
        cci.status = AR_CAMERA_STATUS_OPENED;
        if (cameraCacheInfoIndex < 0) {
            this.cameraCacheInfo.add(cci);
            cameraCacheInfoIndex = this.cameraCacheInfo.size() - 1;
        }
        this.cameraCacheInfoIndexCache.put(cci.camera, Integer.valueOf(cameraCacheInfoIndex));
        return cameraCacheInfoIndex;
    }

    public int registerHalCamera(int camIndex, int type, int direction, String customData, int[] captureInfo, int[] overrideCaptureInfo) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(6);
            return -1;
        }
        int cameraDeviceIndex = getCameraDeviceIndex(camIndex, type, direction);
        if (cameraDeviceIndex < 0) {
            return -1;
        }
        int cameraCacheInfoIndex = -1;
        CameraCacheInfo cci = null;
        int cameraCacheInfoSize = this.cameraCacheInfo.size();
        int i = 0;
        while (true) {
            if (i >= cameraCacheInfoSize) {
                break;
            }
            cci = (CameraCacheInfo) this.cameraCacheInfo.get(i);
            if (cci.deviceID == cameraDeviceIndex && cci.isHalDriven) {
                cameraCacheInfoIndex = i;
                break;
            }
            i++;
        }
        if (cameraCacheInfoIndex < 0) {
            cci = new CameraCacheInfo();
            cci.deviceID = cameraDeviceIndex;
            cci.camera = null;
            cci.surface = null;
            cci.buffer = null;
            cci.overrideWidth = 0;
            cci.requestWidth = 0;
            cci.bufferWidth = 0;
            cci.overrideHeight = 0;
            cci.requestHeight = 0;
            cci.bufferHeight = 0;
            cci.bufferFormatPL = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            cci.overrideFormatAndroid = 0;
            cci.requestFormatAndroid = 0;
            cci.caps = null;
            cci.status = AR_CAMERA_STATUS_UNINITIALIZED;
            cci.isAutoFocusing = false;
            cci.isHalDriven = true;
        }
        cci.bufferSize = 0;
        if (cameraCacheInfoIndex >= 0) {
            return cameraCacheInfoIndex;
        }
        this.cameraCacheInfo.add(cci);
        int cameraCacheInfoIndex2 = this.cameraCacheInfo.size() - 1;
        DebugLog.LOGD(MODULENAME, "New HAL camera cache info added to cache");
        return cameraCacheInfoIndex2;
    }

    public boolean close(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (cci.isHalDriven) {
            DebugLog.LOGW(MODULENAME, "We shouldn't be here for HAL driven camera!");
            return true;
        } else {
            this.cameraCacheInfoIndexCache.remove(cci.camera);
            boolean result = false;
            try {
                cci.camera.release();
                result = true;
            } catch (Exception e) {
            }
            cci.camera = null;
            cci.buffer = null;
            cci.status = AR_CAMERA_STATUS_UNINITIALIZED;
            System.gc();
            return result;
        }
    }

    public int[] getCameraCapabilities(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        } else if (cci.caps != null) {
            return cci.caps;
        } else {
            Parameters cameraParams = getCameraParameters(cci.camera);
            if (cameraParams == null) {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
            List<Size> supportedImageSizes = cameraParams.getSupportedPreviewSizes();
            List<Integer> supportedFrameRates = cameraParams.getSupportedPreviewFrameRates();
            List<Integer> supportedImageFormats = cameraParams.getSupportedPreviewFormats();
            List<String> supportedFlashModes = cameraParams.getSupportedFlashModes();
            List<String> supportedFocusModes = cameraParams.getSupportedFocusModes();
            int numSupportedImageSizes = supportedImageSizes != null ? supportedImageSizes.size() : 0;
            int numSupportedFrameRates = supportedFrameRates != null ? supportedFrameRates.size() : 0;
            int numSupportedImageFormats = supportedImageFormats != null ? supportedImageFormats.size() : 0;
            cci.caps = new int[((numSupportedImageSizes * 2) + 6 + numSupportedFrameRates + numSupportedImageFormats)];
            cci.caps[0] = AR_CAMERA_PARAMTYPE_BASE;
            boolean z = supportedFlashModes != null ? supportedFlashModes.contains("torch") || supportedFlashModes.contains("on") : false;
            r1 = AR_CAMERA_PARAMTYPE_TORCHMODE;
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_TORCHMODE, z);
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE, SystemTools.checkMinimumApiLevel(8));
            boolean z2 = SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported();
            r1 = AR_CAMERA_PARAMTYPE_ZOOMVALUE;
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ZOOMVALUE, z2);
            boolean z3 = SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported();
            r1 = AR_CAMERA_PARAMTYPE_ZOOMRANGE;
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_ZOOMRANGE, z3);
            setCameraCapsBit(cci, 0, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cci.caps[1] = AR_CAMERA_PARAMTYPE_BASE;
            boolean z4 = supportedFlashModes != null ? supportedFlashModes.contains("torch") || supportedFlashModes.contains("on") : false;
            r1 = AR_CAMERA_PARAMTYPE_TORCHMODE;
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_TORCHMODE, z4);
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            boolean z5 = SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported();
            r1 = AR_CAMERA_PARAMTYPE_ZOOMVALUE;
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_ZOOMVALUE, z5);
            setCameraCapsBit(cci, 1, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cci.caps[2] = AR_CAMERA_PARAMVALUE_BASE;
            if (supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on"))) {
                setCameraCapsBit(cci, 2, AR_CAMERA_TORCHMODE_OFF, true);
                setCameraCapsBit(cci, 2, AR_CAMERA_TORCHMODE_ON, true);
            }
            if (supportedFocusModes != null) {
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_NORMAL, true);
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_AUTO, supportedFocusModes.contains("auto"));
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO, supportedFocusModes.contains("continuous-video"));
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_MACRO, supportedFocusModes.contains("macro"));
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_INFINITY, supportedFocusModes.contains("infinity"));
                setCameraCapsBit(cci, 2, AR_CAMERA_FOCUSMODE_FIXED, supportedFocusModes.contains("fixed"));
            }
            cci.caps[3] = numSupportedImageSizes;
            cci.caps[4] = numSupportedFrameRates;
            cci.caps[5] = numSupportedImageFormats;
            int indexOffset = 6;
            if (numSupportedImageSizes > 0) {
                ListIterator<Size> iterSizes = supportedImageSizes.listIterator();
                while (iterSizes.hasNext()) {
                    Size size = (Size) iterSizes.next();
                    cci.caps[indexOffset] = size.width;
                    cci.caps[indexOffset + 1] = size.height;
                    indexOffset += 2;
                }
            }
            if (numSupportedFrameRates > 0) {
                ListIterator<Integer> iterFramerates = supportedFrameRates.listIterator();
                while (iterFramerates.hasNext()) {
                    cci.caps[indexOffset] = ((Integer) iterFramerates.next()).intValue();
                    indexOffset++;
                }
            }
            if (numSupportedImageFormats > 0) {
                ListIterator<Integer> iterFormats = supportedImageFormats.listIterator();
                while (iterFormats.hasNext()) {
                    cci.caps[indexOffset] = translateImageFormat(((Integer) iterFormats.next()).intValue(), true);
                    indexOffset++;
                }
            }
            return cci.caps;
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
        } else {
            Parameters cameraParams = getCameraParameters(cci.camera);
            if (cameraParams == null) {
                SystemTools.setSystemErrorCode(6);
                return false;
            } else if (!setCameraCaptureParams(cci, cameraParams, captureInfo, overrideCaptureInfo)) {
                SystemTools.setSystemErrorCode(6);
                return false;
            } else {
                try {
                    cci.camera.setParameters(cameraParams);
                    if (checkSamsungHighFPS(cci)) {
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            }
        }
    }

    public int[] getCaptureInfo(int cameraCacheInfoIndex) {
        int i = 0;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        try {
            int[] captureInfo = new int[5];
            captureInfo[0] = cameraParams.getPreviewSize().width;
            captureInfo[1] = cameraParams.getPreviewSize().height;
            captureInfo[2] = translateImageFormat(cameraParams.getPreviewFormat(), CONVERT_FORMAT_TO_PL);
            captureInfo[3] = cameraParams.getPreviewFrameRate();
            if (!(cci.surface == null && cci.surfaceTexture == null)) {
                i = 1;
            }
            captureInfo[4] = i;
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
                cci.camera.startPreview();
                cci.status = AR_CAMERA_STATUS_CAPTURE_RUNNING;
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
            cci.camera.stopPreview();
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
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        } else if (!setCustomCameraParams(cameraParams, customData)) {
            return false;
        } else {
            cci.camera.setParameters(cameraParams);
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean setUntypedCameraParameter(int cameraCacheInfoIndex, String name, String value) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        try {
            cameraParams.set(name, value);
            cci.camera.setParameters(cameraParams);
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public String getUntypedCameraParameter(int cameraCacheInfoIndex, String name) {
        String parameterValue = null;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
        } else {
            Parameters cameraParams = getCameraParameters(cci.camera);
            if (cameraParams == null) {
                SystemTools.setSystemErrorCode(6);
            } else {
                parameterValue = cameraParams.get(name);
                if (parameterValue == null) {
                    SystemTools.setSystemErrorCode(6);
                }
            }
        }
        return parameterValue;
    }

    /* access modifiers changed from: 0000 */
    public String getFlattenedParameters(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return BuildConfig.FLAVOR;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams != null) {
            return cameraParams.flatten();
        }
        SystemTools.setSystemErrorCode(6);
        return BuildConfig.FLAVOR;
    }

    /* access modifiers changed from: 0000 */
    public boolean setTypedCameraParameter(int cameraCacheInfoIndex, int type, Object value) {
        boolean z;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return false;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        boolean doPostSetAction = false;
        switch (type) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                switch (((Number) value).intValue()) {
                    case AR_CAMERA_TORCHMODE_OFF /*805306369*/:
                        cameraParams.setFlashMode("off");
                        break;
                    case AR_CAMERA_TORCHMODE_ON /*805306370*/:
                        if (!cameraParams.getSupportedFlashModes().contains("torch")) {
                            cameraParams.setFlashMode("on");
                            break;
                        } else {
                            cameraParams.setFlashMode("torch");
                            break;
                        }
                    case AR_CAMERA_TORCHMODE_AUTO /*805306372*/:
                        SystemTools.setSystemErrorCode(3);
                        return false;
                    default:
                        SystemTools.setSystemErrorCode(3);
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                cci.camera.cancelAutoFocus();
                switch (((Number) value).intValue()) {
                    case AR_CAMERA_FOCUSMODE_NORMAL /*805306384*/:
                        if (!cameraParams.getSupportedFocusModes().contains(FOCUS_MODE_NORMAL)) {
                            cameraParams.setFocusMode("auto");
                            doPostSetAction = true;
                            break;
                        } else {
                            cameraParams.setFocusMode(FOCUS_MODE_NORMAL);
                            break;
                        }
                    case AR_CAMERA_FOCUSMODE_AUTO /*805306400*/:
                        cameraParams.setFocusMode("auto");
                        doPostSetAction = true;
                        break;
                    case AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO /*805306432*/:
                        if (cameraParams.getSupportedFocusModes().contains("continuous-video")) {
                            cameraParams.setFocusMode("continuous-video");
                            break;
                        } else {
                            SystemTools.setSystemErrorCode(6);
                            return false;
                        }
                    case AR_CAMERA_FOCUSMODE_MACRO /*805306496*/:
                        cameraParams.setFocusMode("macro");
                        break;
                    case AR_CAMERA_FOCUSMODE_INFINITY /*805306624*/:
                        cameraParams.setFocusMode("infinity");
                        break;
                    case AR_CAMERA_FOCUSMODE_FIXED /*805306880*/:
                        cameraParams.setFocusMode("fixed");
                        break;
                    default:
                        SystemTools.setSystemErrorCode(3);
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                if (SystemTools.checkMinimumApiLevel(14)) {
                    float[] focusRegion = (float[]) value;
                    if (focusRegion.length == 5) {
                        if (focusRegion[0] >= 0.0f && focusRegion[0] <= 1.0f && focusRegion[1] >= 0.0f && focusRegion[1] <= 1.0f && focusRegion[2] >= 0.0f && focusRegion[2] <= 1.0f && focusRegion[3] >= 0.0f && focusRegion[3] <= 1.0f && focusRegion[4] >= 0.0f && focusRegion[4] <= 1.0f) {
                            Rect focusRect = new Rect(((int) (((double) focusRegion[0]) * 2000.0d)) - 1000, ((int) (((double) focusRegion[1]) * 2000.0d)) - 1000, ((int) (((double) focusRegion[2]) * 2000.0d)) - 1000, ((int) (((double) focusRegion[3]) * 2000.0d)) - 1000);
                            List<Area> focusAreaList = new ArrayList<>();
                            focusAreaList.add(new Area(focusRect, (int) (((double) focusRegion[4]) * 1000.0d)));
                            if (cameraParams.getMaxNumFocusAreas() > 0) {
                                cameraParams.setFocusAreas(focusAreaList);
                                break;
                            }
                        } else {
                            SystemTools.setSystemErrorCode(2);
                            return false;
                        }
                    } else {
                        SystemTools.setSystemErrorCode(2);
                        return false;
                    }
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
                break;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                switch (((Number) value).intValue()) {
                    case AR_CAMERA_EXPOSUREMODE_LOCKED /*805310464*/:
                        if (cameraParams.isAutoExposureLockSupported()) {
                            cameraParams.setAutoExposureLock(true);
                            break;
                        }
                        break;
                    case AR_CAMERA_EXPOSUREMODE_AUTO /*805314560*/:
                    case AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO /*805322752*/:
                        if (cameraParams.isAutoExposureLockSupported()) {
                            cameraParams.setAutoExposureLock(false);
                            break;
                        }
                        break;
                    default:
                        SystemTools.setSystemErrorCode(3);
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_ISO /*536870976*/:
                try {
                    String isoValStr = Integer.toString(((Number) value).intValue());
                    String supportedIsoValues = cameraParams.get("iso-values");
                    if (supportedIsoValues != null) {
                        String[] supportedIsoValuesParts = supportedIsoValues.split(",");
                        int x = 0;
                        while (true) {
                            if (x < supportedIsoValuesParts.length) {
                                if (supportedIsoValuesParts[x].toLowerCase().contains(isoValStr.toLowerCase())) {
                                    isoValStr = supportedIsoValuesParts[x];
                                } else {
                                    x++;
                                }
                            }
                        }
                    }
                    cameraParams.set("iso", isoValStr);
                    break;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536871936*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    float expValue = ((Number) value).floatValue();
                    float expStep = cameraParams.getExposureCompensationStep();
                    if (expStep != 0.0f) {
                        cameraParams.setExposureCompensation(Math.round(expValue / expStep));
                        break;
                    } else {
                        SystemTools.setSystemErrorCode(6);
                        return false;
                    }
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE /*536872960*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE /*536875008*/:
                int whiteBalanceMode = ((Number) value).intValue();
                switch (whiteBalanceMode) {
                    case AR_CAMERA_WHITEBALANCEMODE_LOCKED /*806354944*/:
                        if (cameraParams.isAutoWhiteBalanceLockSupported()) {
                            cameraParams.setAutoWhiteBalanceLock(true);
                            break;
                        }
                        break;
                    case AR_CAMERA_WHITEBALANCEMODE_AUTO /*807403520*/:
                    case AR_CAMERA_WHITEBALANCEMODE_CONTINUOUSAUTO /*809500672*/:
                        if (cameraParams.isAutoWhiteBalanceLockSupported()) {
                            cameraParams.setAutoWhiteBalanceLock(false);
                            break;
                        }
                        break;
                    default:
                        SystemTools.setSystemErrorCode(3);
                        SystemTools.logSystemError("Cannot set unknown white balance mode (" + whiteBalanceMode + ")");
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE /*536879104*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE /*536887296*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_ZOOMVALUE /*536903680*/:
                if (SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported()) {
                    cameraParams.setZoom(((Number) value).intValue());
                    break;
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
                break;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536936448*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*537001984*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE /*537133056*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_CONTRASTVALUE /*537395200*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_CONTRASTRANGE /*537919488*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_ROTATION /*538968064*/:
                SystemTools.setSystemErrorCode(6);
                return false;
            case AR_CAMERA_PARAMTYPE_RECORDING_HINT /*541065216*/:
                int val = ((Number) value).intValue();
                if (!SystemTools.checkMinimumApiLevel(14)) {
                    cameraParams.set("recording-hint", val != 0 ? "true" : "false");
                    break;
                } else {
                    if (val != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    cameraParams.setRecordingHint(z);
                    break;
                }
            case AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION /*553648128*/:
                if (!((Boolean) value).booleanValue()) {
                    cameraParams.setVideoStabilization(false);
                    break;
                } else {
                    cameraParams.setVideoStabilization(true);
                    break;
                }
            default:
                return false;
        }
        try {
            cci.camera.setParameters(cameraParams);
            if (doPostSetAction) {
                switch (type) {
                    case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                        try {
                            cci.isAutoFocusing = true;
                            Camera camera = cci.camera;
                            C00451 r0 = new AutoFocusCallback() {
                                public void onAutoFocus(boolean success, Camera camera) {
                                    Object intObj = Camera1_Preview.this.cameraCacheInfoIndexCache.get(camera);
                                    if (intObj != null) {
                                        CameraCacheInfo cci = Camera1_Preview.this.getCameraCacheInfo(((Integer) intObj).intValue());
                                        if (cci != null) {
                                            cci.isAutoFocusing = false;
                                        }
                                    }
                                }
                            };
                            camera.autoFocus(r0);
                            break;
                        } catch (Exception e2) {
                            SystemTools.setSystemErrorCode(6);
                            return false;
                        }
                }
            }
            return true;
        } catch (Exception e3) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public Object getTypedCameraParameter(int cameraCacheInfoIndex, int type) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
        switch (type) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                try {
                    String flashMode = cameraParams.getFlashMode();
                    if (flashMode.equals("torch") || flashMode.equals("on")) {
                        return Integer.valueOf(AR_CAMERA_TORCHMODE_ON);
                    }
                    if (flashMode.equals("off")) {
                        return Integer.valueOf(AR_CAMERA_TORCHMODE_OFF);
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                String focusMode = cameraParams.getFocusMode();
                if (focusMode.equals("auto")) {
                    return Integer.valueOf(cci.isAutoFocusing ? AR_CAMERA_FOCUSMODE_AUTO : AR_CAMERA_FOCUSMODE_NORMAL);
                } else if (focusMode.equals("continuous-video")) {
                    return Integer.valueOf(AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO);
                } else {
                    if (focusMode.equals("infinity")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_INFINITY);
                    }
                    if (focusMode.equals("macro")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_MACRO);
                    }
                    if (focusMode.equals("fixed")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_FIXED);
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Float.valueOf(cameraParams.getFocalLength());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                if (SystemTools.checkMinimumApiLevel(9)) {
                    float[] focusDistances = new float[3];
                    cameraParams.getFocusDistances(focusDistances);
                    return new float[]{focusDistances[0], focusDistances[2]};
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                if (SystemTools.checkMinimumApiLevel(14) && cameraParams.getMaxNumFocusAreas() > 0) {
                    List<Area> focusAreas = cameraParams.getFocusAreas();
                    if (focusAreas.size() > 0) {
                        Area focusArea = (Area) focusAreas.get(0);
                        return new float[]{(float) focusArea.rect.left, (float) focusArea.rect.top, (float) focusArea.rect.right, (float) focusArea.rect.bottom, (float) focusArea.weight};
                    }
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536871936*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Float.valueOf(cameraParams.getExposureCompensationStep() * ((float) cameraParams.getExposureCompensation()));
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE /*536872960*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return new float[]{cameraParams.getExposureCompensationStep() * ((float) cameraParams.getMinExposureCompensation()), cameraParams.getExposureCompensationStep() * ((float) cameraParams.getMaxExposureCompensation())};
                }
                SystemTools.setSystemErrorCode(6);
                return null;
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
                if (SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported()) {
                    return Integer.valueOf(cameraParams.getZoom());
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536936448*/:
                if (!SystemTools.checkMinimumApiLevel(8) || !cameraParams.isZoomSupported()) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                return new int[]{0, cameraParams.getMaxZoom()};
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*537001984*/:
                SystemTools.setSystemErrorCode(6);
                return null;
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
                if (cameraParams.getVideoStabilization()) {
                    return Boolean.valueOf(true);
                }
                return Boolean.valueOf(false);
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
