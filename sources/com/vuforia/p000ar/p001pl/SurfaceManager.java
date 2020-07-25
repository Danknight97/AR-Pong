package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.vuforia.p000ar.p001pl.Camera1_Preview.CameraCacheInfo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.vuforia.ar.pl.SurfaceManager */
public class SurfaceManager {
    private static final String MODULENAME = "SurfaceManager";
    Lock addSurfaceLock = new ReentrantLock();
    View cameraSurfaceParentView = null;
    CameraCacheInfo cciForSurface;
    GLSurfaceView glSurfaceView = null;
    int glSurfaceViewChildPosition = 0;
    boolean renderWhenDirtyEnabled = false;
    Lock viewLock = new ReentrantLock();

    private GLSurfaceView searchForGLSurfaceView(View rootView) {
        GLSurfaceView result = null;
        this.glSurfaceViewChildPosition = 0;
        try {
            ViewGroup rootViewGroup = (ViewGroup) rootView;
            int numChildren = rootViewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= numChildren) {
                    break;
                }
                View childView = rootViewGroup.getChildAt(i);
                if (childView instanceof GLSurfaceView) {
                    result = (GLSurfaceView) childView;
                    this.glSurfaceViewChildPosition = i;
                    break;
                }
                if (childView instanceof ViewGroup) {
                    result = searchForGLSurfaceView(childView);
                    if (result != null) {
                        break;
                    }
                }
                i++;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean applyRenderWhenDirty() {
        int i = 0;
        if (this.glSurfaceView == null) {
            return false;
        }
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (!this.renderWhenDirtyEnabled) {
            i = 1;
        }
        gLSurfaceView.setRenderMode(i);
        return true;
    }

    /* access modifiers changed from: private */
    public void setupCameraSurface(CameraCacheInfo cci) {
        if (cci.surface == null) {
            Activity a = SystemTools.getActivityFromNative();
            if (a != null) {
                cci.surface = new CameraSurface(a);
            } else {
                return;
            }
        } else if (cci.surface.getParent() != null && ViewGroup.class.isInstance(cci.surface.getParent())) {
            ((ViewGroup) cci.surface.getParent()).removeView(cci.surface);
        }
        cci.surface.setCamera(cci.camera);
    }

    public boolean retrieveGLSurfaceView() {
        boolean z;
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity == null) {
                return false;
            }
            View decorView = activity.getWindow().getDecorView();
            this.glSurfaceView = searchForGLSurfaceView(decorView);
            if (this.glSurfaceView == null) {
                this.cameraSurfaceParentView = decorView;
            } else {
                this.cameraSurfaceParentView = (View) this.glSurfaceView.getParent();
            }
            if (this.glSurfaceView != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setEnableRenderWhenDirty(boolean enabled) {
        this.renderWhenDirtyEnabled = enabled;
        return applyRenderWhenDirty();
    }

    public void requestRender() {
        if (this.glSurfaceView != null) {
            this.glSurfaceView.requestRender();
        }
    }

    public boolean addCameraSurface(CameraCacheInfo cci) {
        Activity activity = SystemTools.getActivityFromNative();
        if (activity == null) {
            return false;
        }
        this.cciForSurface = cci;
        boolean didExceptionHappen = false;
        this.viewLock.lock();
        try {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    SurfaceManager.this.addSurfaceLock.lock();
                    SurfaceManager.this.retrieveGLSurfaceView();
                    try {
                        SurfaceManager.this.setupCameraSurface(SurfaceManager.this.cciForSurface);
                        ((ViewGroup) SurfaceManager.this.cameraSurfaceParentView).addView(SurfaceManager.this.cciForSurface.surface, SurfaceManager.this.glSurfaceViewChildPosition + 1, new LayoutParams(-1, -1));
                        SurfaceManager.this.cciForSurface.surface.setVisibility(0);
                    } catch (Exception e) {
                    } finally {
                        SurfaceManager.this.addSurfaceLock.unlock();
                    }
                }
            });
        } catch (Exception e) {
            didExceptionHappen = true;
        } finally {
            this.viewLock.unlock();
        }
        if (!didExceptionHappen) {
            return true;
        }
        return false;
    }
}
