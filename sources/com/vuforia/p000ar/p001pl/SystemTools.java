package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

/* renamed from: com.vuforia.ar.pl.SystemTools */
public class SystemTools {
    public static final int AR_DEVICE_ORIENTATION_0 = 268455954;
    public static final int AR_DEVICE_ORIENTATION_180 = 268455955;
    public static final int AR_DEVICE_ORIENTATION_270 = 268455957;
    public static final int AR_DEVICE_ORIENTATION_90 = 268455956;
    public static final int AR_DEVICE_ORIENTATION_UNKNOWN = 268455952;
    public static final int AR_ERROR_INVALID_ENUM = 3;
    public static final int AR_ERROR_INVALID_HANDLE = 4;
    public static final int AR_ERROR_INVALID_OPERATION = 5;
    public static final int AR_ERROR_INVALID_VALUE = 2;
    public static final int AR_ERROR_NONE = 0;
    public static final int AR_ERROR_OPERATION_CANCELED = 7;
    public static final int AR_ERROR_OPERATION_FAILED = 6;
    public static final int AR_ERROR_OPERATION_TIMEOUT = 8;
    public static final int AR_ERROR_UNKNOWN = 1;
    public static final int AR_RENDERING_TEXTURE_ROTATION_AUTO = 268455953;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT = 268455956;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT = 268455957;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT = 268455954;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN = 268455955;
    public static final int AR_VIDEOTEXTURE_ROTATION_UNKNOWN = 268455952;
    private static final String MODULENAME = "SystemTools";

    public static native Activity getActivityFromNative();

    public static native void logSystemError(String str);

    public static native void setSystemErrorCode(int i);

    public static boolean checkMinimumApiLevel(int apiLevel) {
        return VERSION.SDK_INT >= apiLevel;
    }

    public static void sendKillSignal() {
        sendKillSignal(0, true);
    }

    public static void sendKillSignal(int errorCode) {
        sendKillSignal(errorCode, true);
    }

    public static void sendKillSignal(boolean enableLifecycleEvents) {
        sendKillSignal(0, enableLifecycleEvents);
    }

    public static void sendKillSignal(final int errorCode, final boolean enableLifecycleEvents) {
        final Activity activity = getActivityFromNative();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (enableLifecycleEvents) {
                        try {
                            Method method = activity.getClass().getDeclaredMethod("onPause", new Class[0]);
                            method.setAccessible(true);
                            method.invoke(activity, new Object[0]);
                            Method method2 = activity.getClass().getDeclaredMethod("onDestroy", new Class[0]);
                            method2.setAccessible(true);
                            method2.invoke(activity, new Object[0]);
                        } catch (Exception e) {
                            SystemTools.logSystemError("Error attempting to call onPause and onDestroy, will proceed with exiting");
                            if (!(e == null || e.getCause() == null)) {
                                SystemTools.logSystemError(e.getCause().toString());
                                for (StackTraceElement i : e.getStackTrace()) {
                                    SystemTools.logSystemError(i.toString());
                                }
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e2) {
                        }
                    }
                    System.exit(errorCode);
                }
            });
        }
    }

    public static Method retrieveClassMethod(Class<?> cls, String name, Class<?>... parameterTypes) {
        Method classMethod = null;
        try {
            classMethod = cls.getMethod(name, parameterTypes);
        } catch (Exception e) {
        }
        if (classMethod != null) {
        }
        return classMethod;
    }

    public static int getDeviceOrientation(Activity activity) {
        int displayRotation;
        if (activity == null) {
            return 268455952;
        }
        Configuration configuration = activity.getResources().getConfiguration();
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            displayRotation = display.getRotation();
        } else {
            displayRotation = display.getOrientation();
        }
        if (displayRotation == 0) {
            return 268455954;
        }
        if (displayRotation == 1) {
            return 268455956;
        }
        if (displayRotation == 2) {
            return 268455955;
        }
        if (displayRotation == 3) {
            return 268455957;
        }
        return 268455952;
    }

    public static int getActivityOrientation(Activity activity) {
        int displayRotation;
        if (activity == null) {
            return 268455952;
        }
        Configuration config = activity.getResources().getConfiguration();
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            displayRotation = display.getRotation();
        } else {
            displayRotation = display.getOrientation();
        }
        switch (config.orientation) {
            case 1:
            case 3:
                return (displayRotation == 0 || displayRotation == 3) ? 268455954 : 268455955;
            case 2:
                return (displayRotation == 0 || displayRotation == 1) ? 268455956 : 268455957;
            default:
                return 268455952;
        }
    }

    public static String getNativeLibraryPath(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            ApplicationInfo appInfo = activity.getApplicationInfo();
            if (appInfo == null) {
                return null;
            }
            if (!checkMinimumApiLevel(9)) {
                return "/data/data/" + activity.getPackageName() + "/lib/";
            }
            String path = appInfo.nativeLibraryDir;
            if (path == null || path.length() <= 0 || path.charAt(path.length() - 1) == '/') {
                return path;
            }
            return path + '/';
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] getActivitySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        if (screenWidth <= 0 || screenHeight <= 0) {
            return null;
        }
        return new int[]{screenWidth, screenHeight};
    }

    public static float[] getDisplayDpi(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        if (checkMinimumApiLevel(17)) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        if (xdpi <= 0.0f || ydpi <= 0.0f) {
            return null;
        }
        return new float[]{xdpi, ydpi};
    }

    public static int[] getDisplaySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        Point displaySize = new Point();
        try {
            activity.getWindowManager().getDefaultDisplay().getRealSize(displaySize);
            if (displaySize.x > 0 && displaySize.y > 0) {
                int[] displaySizes = new int[2];
                if (displaySize.y > displaySize.x) {
                    displaySizes[0] = displaySize.y;
                    displaySizes[1] = displaySize.x;
                    return displaySizes;
                }
                displaySizes[0] = displaySize.x;
                displaySizes[1] = displaySize.y;
                return displaySizes;
            }
        } catch (NoSuchMethodError e) {
            DebugLog.LOGE(MODULENAME, "Display.getRealSize is not supported on this platform");
        }
        return null;
    }
}
