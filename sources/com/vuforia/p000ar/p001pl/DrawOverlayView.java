package com.vuforia.p000ar.p001pl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.nio.ByteBuffer;

/* renamed from: com.vuforia.ar.pl.DrawOverlayView */
public class DrawOverlayView extends View {
    private static final String MODULENAME = "DrawOverlayView";
    private Drawable drawable = null;
    private double mLeft;
    private float[] mScale;
    private int[] mSize;
    private double mTop;
    private DisplayMetrics metrics;
    private Bitmap overlayBitmap;

    public DrawOverlayView(Context context) {
        super(context);
    }

    public DrawOverlayView(Context context, byte[] byteArray, int left, int top, float[] scale, int[] size) {
        super(context);
        this.mLeft = (double) left;
        this.mTop = (double) top;
        this.mScale = scale;
        this.mSize = size;
        byte[] byteArrayRGBA = new byte[(byteArray.length * 4)];
        for (int i = 0; i < this.mSize[0] * this.mSize[1]; i++) {
            byteArrayRGBA[i * 4] = byteArray[i];
            byteArrayRGBA[(i * 4) + 1] = byteArray[i];
            byteArrayRGBA[(i * 4) + 2] = byteArray[i];
            byteArrayRGBA[(i * 4) + 3] = -1;
        }
        this.overlayBitmap = Bitmap.createBitmap(this.mSize[0], this.mSize[1], Config.ARGB_8888);
        this.overlayBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(byteArrayRGBA));
        this.drawable = new BitmapDrawable(this.overlayBitmap);
        this.metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.overlayBitmap == null) {
            super.dispatchDraw(canvas);
            return;
        }
        double maxTop = ((double) this.metrics.heightPixels) - ((double) (((float) this.drawable.getIntrinsicHeight()) * this.mScale[1]));
        if (maxTop < this.mTop) {
            this.mTop = maxTop;
        }
        this.drawable.setBounds((int) this.mLeft, (int) this.mTop, (int) (this.mLeft + ((double) (((float) this.drawable.getIntrinsicWidth()) * this.metrics.density * this.mScale[0]))), (int) (this.mTop + ((double) (((float) this.drawable.getIntrinsicHeight()) * this.metrics.density * this.mScale[1]))));
        this.drawable.setAlpha(100);
        this.drawable.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public void addOverlay(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setVisibility(0);
    }

    public void removeOverlay(Activity activity, View childView) {
        try {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(childView);
        } catch (Exception e) {
        }
    }
}
