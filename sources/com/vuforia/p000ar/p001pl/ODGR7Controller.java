package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.osterhoutgroup.api.ext.ExtendDisplay;

/* renamed from: com.vuforia.ar.pl.ODGR7Controller */
public class ODGR7Controller {
    private static final String MODULENAME = "ODGR7Controller";
    private boolean stereoEnabled = false;

    private void logMetrics(String when, Activity activity, Window w) {
        DisplayMetrics metrics = new DisplayMetrics();
        ExtendDisplay.getDisplayMetrics(activity, w, metrics);
        int i = metrics.widthPixels;
        int i2 = metrics.heightPixels;
    }

    public boolean setStereo(final boolean enable) {
        final Activity activity = SystemTools.getActivityFromNative();
        if (activity == null) {
            return false;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                ODGR7Controller.this.doSetStereo(activity, enable);
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public void doSetStereo(Activity activity, boolean enable) {
        Window window = activity.getWindow();
        if (window != null) {
            logMetrics("Before extendWindow", activity, window);
            ExtendDisplay.extendWindow(window, enable);
            logMetrics("After extendWindow", activity, window);
            setStereoSurfaces(window, enable);
            this.stereoEnabled = enable;
        }
    }

    public boolean getStereo() {
        return this.stereoEnabled;
    }

    private void setStereoSurfaces(Window window, boolean enable) {
        configureSurfaceViews((ViewGroup) window.getDecorView(), enable);
    }

    private void configureSurfaceViews(ViewGroup vg, boolean enable) {
        int numChildren = vg.getChildCount();
        for (int i = 0; i < numChildren; i++) {
            View v = vg.getChildAt(i);
            if (v instanceof SurfaceView) {
                SurfaceView sv = (SurfaceView) v;
                if (sv.getHolder().getSurface().isValid()) {
                    ExtendDisplay.extendSurface(sv, enable);
                }
            } else if (v instanceof ViewGroup) {
                configureSurfaceViews((ViewGroup) v, enable);
            }
        }
    }
}
