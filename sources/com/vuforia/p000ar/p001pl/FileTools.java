package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;
import java.io.File;

/* renamed from: com.vuforia.ar.pl.FileTools */
public class FileTools {
    private static final int FILE_PATHTYPEINDEX_ABSOLUTE = -1;
    private static final int FILE_PATHTYPEINDEX_ANDROID_ASSETS = 0;
    private static final int FILE_PATHTYPEINDEX_ANDROID_DATALOCAL = 4;
    private static final int FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE = 3;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPCACHE = 2;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE = 1;
    private static final String MODULENAME = "FileTools";

    public static String getAbsolutePath(int pathTypeIndex, String relativePath) {
        String basePath;
        switch (pathTypeIndex) {
            case 1:
                Activity activity = SystemTools.getActivityFromNative();
                if (activity != null) {
                    File fileDir = activity.getFilesDir();
                    if (fileDir != null) {
                        basePath = fileDir.getAbsolutePath();
                        break;
                    } else {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case 2:
                Activity activity2 = SystemTools.getActivityFromNative();
                if (activity2 != null) {
                    File cacheDir = activity2.getCacheDir();
                    if (cacheDir != null) {
                        basePath = cacheDir.getAbsolutePath();
                        break;
                    } else {
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            case 3:
                File externalStorageDir = Environment.getExternalStorageDirectory();
                if (externalStorageDir != null) {
                    basePath = externalStorageDir.getAbsolutePath();
                    break;
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            default:
                SystemTools.setSystemErrorCode(6);
                return null;
        }
        if (!(basePath == null || relativePath == null)) {
            if (basePath.length() > 0 && basePath.charAt(basePath.length() - 1) != '/') {
                basePath = basePath + "/";
            }
            basePath = basePath + relativePath;
        }
        return basePath;
    }

    public static boolean mediastorage_isAvailable() {
        String state = Environment.getExternalStorageState();
        return "mounted".equals(state) || "mounted_ro".equals(state);
    }

    public static AssetManager get_assetmanager() {
        Activity activity = SystemTools.getActivityFromNative();
        if (activity != null) {
            return activity.getAssets();
        }
        SystemTools.setSystemErrorCode(6);
        return null;
    }
}
