package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.i */
public final class C0042i extends FrameLayout implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {

    /* renamed from: a */
    private static boolean f125a = false;

    /* renamed from: b */
    private final Context f126b;

    /* renamed from: c */
    private final SurfaceView f127c;

    /* renamed from: d */
    private final SurfaceHolder f128d;

    /* renamed from: e */
    private final String f129e;

    /* renamed from: f */
    private final int f130f;

    /* renamed from: g */
    private final int f131g;

    /* renamed from: h */
    private final boolean f132h;

    /* renamed from: i */
    private final long f133i;

    /* renamed from: j */
    private final long f134j;

    /* renamed from: k */
    private final FrameLayout f135k;

    /* renamed from: l */
    private final Display f136l;

    /* renamed from: m */
    private int f137m;

    /* renamed from: n */
    private int f138n;

    /* renamed from: o */
    private int f139o;

    /* renamed from: p */
    private int f140p;

    /* renamed from: q */
    private MediaPlayer f141q;

    /* renamed from: r */
    private MediaController f142r;

    /* renamed from: s */
    private boolean f143s = false;

    /* renamed from: t */
    private boolean f144t = false;

    /* renamed from: u */
    private int f145u = 0;

    /* renamed from: v */
    private boolean f146v = false;

    /* renamed from: w */
    private boolean f147w = false;

    /* renamed from: x */
    private C0043a f148x;

    /* renamed from: y */
    private volatile int f149y = 0;

    /* renamed from: com.unity3d.player.i$a */
    public interface C0043a {
        /* renamed from: a */
        void mo41a(int i);
    }

    protected C0042i(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0043a aVar) {
        super(context);
        this.f148x = aVar;
        this.f126b = context;
        this.f135k = this;
        this.f127c = new SurfaceView(context);
        this.f128d = this.f127c.getHolder();
        this.f128d.addCallback(this);
        this.f128d.setType(3);
        this.f135k.setBackgroundColor(i);
        this.f135k.addView(this.f127c);
        this.f136l = ((WindowManager) this.f126b.getSystemService("window")).getDefaultDisplay();
        this.f129e = str;
        this.f130f = i2;
        this.f131g = i3;
        this.f132h = z;
        this.f133i = j;
        this.f134j = j2;
        if (f125a) {
            m82a("fileName: " + this.f129e);
        }
        if (f125a) {
            m82a("backgroundColor: " + i);
        }
        if (f125a) {
            m82a("controlMode: " + this.f130f);
        }
        if (f125a) {
            m82a("scalingMode: " + this.f131g);
        }
        if (f125a) {
            m82a("isURL: " + this.f132h);
        }
        if (f125a) {
            m82a("videoOffset: " + this.f133i);
        }
        if (f125a) {
            m82a("videoLength: " + this.f134j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m81a(int i) {
        this.f149y = i;
        if (this.f148x != null) {
            this.f148x.mo41a(this.f149y);
        }
    }

    /* renamed from: a */
    private static void m82a(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* renamed from: b */
    private void m83b() {
        if (this.f141q != null) {
            this.f141q.setDisplay(this.f128d);
            if (!this.f146v) {
                if (f125a) {
                    m82a("Resuming playback");
                }
                this.f141q.start();
                return;
            }
            return;
        }
        m81a(0);
        doCleanUp();
        try {
            this.f141q = new MediaPlayer();
            if (this.f132h) {
                this.f141q.setDataSource(this.f126b, Uri.parse(this.f129e));
            } else if (this.f134j != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f129e);
                this.f141q.setDataSource(fileInputStream.getFD(), this.f133i, this.f134j);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f129e);
                    this.f141q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f129e);
                    this.f141q.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.f141q.setDisplay(this.f128d);
            this.f141q.setScreenOnWhilePlaying(true);
            this.f141q.setOnBufferingUpdateListener(this);
            this.f141q.setOnCompletionListener(this);
            this.f141q.setOnPreparedListener(this);
            this.f141q.setOnVideoSizeChangedListener(this);
            this.f141q.setAudioStreamType(3);
            this.f141q.prepare();
            if (this.f130f == 0 || this.f130f == 1) {
                this.f142r = new MediaController(this.f126b);
                this.f142r.setMediaPlayer(this);
                this.f142r.setAnchorView(this);
                this.f142r.setEnabled(true);
                this.f142r.show();
            }
        } catch (Exception e2) {
            if (f125a) {
                m82a("error: " + e2.getMessage() + e2);
            }
            m81a(2);
        }
    }

    /* renamed from: c */
    private void m84c() {
        if (!isPlaying()) {
            m81a(1);
            if (f125a) {
                m82a("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f146v) {
                start();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo131a() {
        return this.f146v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f125a) {
            m82a("destroyPlayer");
        }
        if (!this.f146v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        if (this.f141q != null) {
            this.f141q.release();
            this.f141q = null;
        }
        this.f139o = 0;
        this.f140p = 0;
        this.f144t = false;
        this.f143s = false;
    }

    public final int getBufferPercentage() {
        if (this.f132h) {
            return this.f145u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        if (this.f141q == null) {
            return 0;
        }
        return this.f141q.getCurrentPosition();
    }

    public final int getDuration() {
        if (this.f141q == null) {
            return 0;
        }
        return this.f141q.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f144t && this.f143s;
        return this.f141q == null ? !z : this.f141q.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f125a) {
            m82a("onBufferingUpdate percent:" + i);
        }
        this.f145u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f125a) {
            m82a("onCompletion called");
        }
        destroyPlayer();
        m81a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f130f != 2 || i == 0 || keyEvent.isSystem())) {
            return this.f142r != null ? this.f142r.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        }
        destroyPlayer();
        m81a(3);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f125a) {
            m82a("onPrepared called");
        }
        this.f144t = true;
        if (this.f144t && this.f143s) {
            m84c();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f130f != 2 || action != 0) {
            return this.f142r != null ? this.f142r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        m81a(3);
        return true;
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f125a) {
            m82a("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f143s = true;
            this.f139o = i;
            this.f140p = i2;
            if (this.f144t && this.f143s) {
                m84c();
            }
        } else if (f125a) {
            m82a("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.f141q != null) {
            if (this.f147w) {
                this.f141q.pause();
            }
            this.f146v = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f141q != null) {
            this.f141q.seekTo(i);
        }
    }

    public final void start() {
        if (f125a) {
            m82a("Start");
        }
        if (this.f141q != null) {
            if (this.f147w) {
                this.f141q.start();
            }
            this.f146v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f125a) {
            m82a("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f137m != i2 || this.f138n != i3) {
            this.f137m = i2;
            this.f138n = i3;
            if (this.f147w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f125a) {
            m82a("surfaceCreated called");
        }
        this.f147w = true;
        m83b();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f125a) {
            m82a("surfaceDestroyed called");
        }
        this.f147w = false;
    }

    /* access modifiers changed from: protected */
    public final void updateVideoLayout() {
        if (f125a) {
            m82a("updateVideoLayout");
        }
        if (this.f141q != null) {
            if (this.f137m == 0 || this.f138n == 0) {
                WindowManager windowManager = (WindowManager) this.f126b.getSystemService("window");
                this.f137m = windowManager.getDefaultDisplay().getWidth();
                this.f138n = windowManager.getDefaultDisplay().getHeight();
            }
            int i = this.f137m;
            int i2 = this.f138n;
            float f = ((float) this.f139o) / ((float) this.f140p);
            float f2 = ((float) this.f137m) / ((float) this.f138n);
            if (this.f131g == 1) {
                if (f2 <= f) {
                    i2 = (int) (((float) this.f137m) / f);
                } else {
                    i = (int) (((float) this.f138n) * f);
                }
            } else if (this.f131g == 2) {
                if (f2 >= f) {
                    i2 = (int) (((float) this.f137m) / f);
                } else {
                    i = (int) (((float) this.f138n) * f);
                }
            } else if (this.f131g == 0) {
                i = this.f139o;
                i2 = this.f140p;
            }
            if (f125a) {
                m82a("frameWidth = " + i + "; frameHeight = " + i2);
            }
            this.f135k.updateViewLayout(this.f127c, new LayoutParams(i, i2, 17));
        }
    }
}
