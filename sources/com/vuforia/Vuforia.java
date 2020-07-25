package com.vuforia;

import android.app.Activity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Vuforia {
    public static final int GL_20 = 1;
    public static final int GL_30 = 8;
    public static final int INIT_DEVICE_NOT_SUPPORTED = -2;
    public static final int INIT_ERROR = -1;
    public static final int INIT_EXTERNAL_DEVICE_NOT_DETECTED = -10;
    public static final int INIT_LICENSE_ERROR_CANCELED_KEY = -8;
    public static final int INIT_LICENSE_ERROR_INVALID_KEY = -5;
    public static final int INIT_LICENSE_ERROR_MISSING_KEY = -4;
    public static final int INIT_LICENSE_ERROR_NO_NETWORK_PERMANENT = -6;
    public static final int INIT_LICENSE_ERROR_NO_NETWORK_TRANSIENT = -7;
    public static final int INIT_LICENSE_ERROR_PRODUCT_TYPE_MISMATCH = -9;
    public static final int INIT_NO_CAMERA_ACCESS = -3;
    private static boolean initializedJava = false;
    private static UpdateCallback sUpdateCallback = null;
    /* access modifiers changed from: private */
    public static UpdateCallbackInterface sUpdateCallbackInterface = null;
    protected static Map<Integer, Object> sUserDataMap = new ConcurrentHashMap(16, 0.75f, 4);

    public interface UpdateCallbackInterface {
        void Vuforia_onUpdate(State state);
    }

    private static native void privateSetInitParameters(Activity activity, int i, String str);

    static {
        if (!loadLibrary("Vuforia")) {
            System.exit(1);
        }
    }

    protected static boolean wasInitializedJava() {
        return initializedJava;
    }

    protected static void setHint() {
        setHint(-858996736, 2796202);
    }

    public static void setInitParameters(Activity activity, int flags, String licenseKey) {
        if (!initializedJava) {
            setHint();
            initializedJava = true;
        }
        privateSetInitParameters(activity, flags, licenseKey);
    }

    private static boolean loadLibrary(String nLibName) {
        try {
            System.loadLibrary(nLibName);
            System.out.println("Native library lib" + nLibName + ".so loaded");
            return true;
        } catch (UnsatisfiedLinkError e) {
            System.err.println("The library lib" + nLibName + ".so could not be loaded");
            return false;
        } catch (SecurityException e2) {
            System.err.println("The library lib" + nLibName + ".so was not allowed to be loaded");
            return false;
        }
    }

    protected static UpdateCallback registerLocalReference(UpdateCallbackInterface cbi) {
        if (cbi == null) {
            sUpdateCallback = null;
            sUpdateCallbackInterface = null;
            return null;
        }
        sUpdateCallbackInterface = cbi;
        sUpdateCallback = new UpdateCallback() {
            public void Vuforia_onUpdate(State s) {
                Vuforia.sUpdateCallbackInterface.Vuforia_onUpdate(s);
                s.delete();
            }
        };
        return sUpdateCallback;
    }

    protected static short[] convertStringToShortArray(String str) {
        int idx;
        if (str == null) {
            return null;
        }
        short[] codes = new short[(str.codePointCount(0, str.length()) + 1)];
        int length = str.length();
        int offset = 0;
        int idx2 = 0;
        while (offset < length) {
            int codePoint = str.codePointAt(offset);
            if (codePoint > 65535) {
                idx = idx2 + 1;
                codes[idx2] = (short) (codePoint >> 16);
            } else {
                idx = idx2;
            }
            idx2 = idx + 1;
            codes[idx] = (short) codePoint;
            offset += Character.charCount(codePoint);
        }
        codes[codes.length - 1] = 0;
        return codes;
    }

    protected static boolean updateUserDataMap(Integer trackableId, Object userData) {
        if (trackableId == null) {
            return false;
        }
        if (userData == null) {
            sUserDataMap.remove(trackableId);
        } else {
            sUserDataMap.put(trackableId, userData);
        }
        return true;
    }

    protected static Object retreiveFromUserDataMap(Integer trackableId) {
        if (!sUserDataMap.containsKey(trackableId)) {
            return null;
        }
        return sUserDataMap.get(trackableId);
    }

    public static int init() {
        return VuforiaJNI.init();
    }

    public static boolean isInitialized() {
        return VuforiaJNI.isInitialized();
    }

    public static void deinit() {
        VuforiaJNI.deinit();
    }

    public static boolean setHint(long hint, int value) {
        return VuforiaJNI.setHint(hint, value);
    }

    public static void registerCallback(UpdateCallbackInterface object) {
        VuforiaJNI.registerCallback(UpdateCallback.getCPtr(registerLocalReference(object)), sUpdateCallback);
    }

    public static boolean setFrameFormat(int format, boolean enabled) {
        return VuforiaJNI.setFrameFormat(format, enabled);
    }

    public static int getBitsPerPixel(int format) {
        return VuforiaJNI.getBitsPerPixel(format);
    }

    public static boolean requiresAlpha() {
        return VuforiaJNI.requiresAlpha();
    }

    public static int getBufferSize(int width, int height, int format) {
        return VuforiaJNI.getBufferSize(width, height, format);
    }

    public static void onResume() {
        VuforiaJNI.onResume();
    }

    public static void onPause() {
        VuforiaJNI.onPause();
    }

    public static void onSurfaceCreated() {
        VuforiaJNI.onSurfaceCreated();
    }

    public static void onSurfaceChanged(int width, int height) {
        VuforiaJNI.onSurfaceChanged(width, height);
    }

    public static String getLibraryVersion() {
        return VuforiaJNI.getLibraryVersion();
    }
}
