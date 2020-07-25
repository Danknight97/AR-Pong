package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.vuforia.ar.pl.EpsonBT200Controller */
public class EpsonBT200Controller {
    private static final String MODULENAME = "EpsonBT200Controller";
    private boolean stereoEnabled = false;

    public EpsonBT200Controller() {
        final Activity activity = SystemTools.getActivityFromNative();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Window win = activity.getWindow();
                    LayoutParams winParams = win.getAttributes();
                    winParams.flags |= Integer.MIN_VALUE;
                    win.setAttributes(winParams);
                }
            });
        }
    }

    public boolean setStereo(boolean enable) {
        int newDisplayMode = 0;
        if (enable) {
            newDisplayMode = 1;
        }
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity == null) {
                return false;
            }
            Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
            boolean result = ((Boolean) Display.class.getDeclaredMethod("setDisplayMode", new Class[]{Integer.TYPE}).invoke(display, new Object[]{Integer.valueOf(newDisplayMode)})).booleanValue();
            if (!result) {
                return result;
            }
            this.stereoEnabled = enable;
            return result;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e2) {
            return false;
        } catch (InvocationTargetException e3) {
            return false;
        }
    }

    public boolean getStereo() {
        return this.stereoEnabled;
    }
}
