package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import com.unity3d.player.C0042i.C0043a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout {
    public static Activity currentActivity = null;

    /* renamed from: m */
    private static boolean f10m;

    /* renamed from: a */
    C0026c f11a = new C0026c(this, 0);

    /* renamed from: b */
    C0036f f12b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f13c = false;

    /* renamed from: d */
    private boolean f14d = true;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C0041h f15e = new C0041h();

    /* renamed from: f */
    private final ConcurrentLinkedQueue f16f = new ConcurrentLinkedQueue();

    /* renamed from: g */
    private BroadcastReceiver f17g = null;

    /* renamed from: h */
    private boolean f18h = false;

    /* renamed from: i */
    private C0024a f19i = new C0024a(this, 0);

    /* renamed from: j */
    private TelephonyManager f20j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public ContextWrapper f21k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public SurfaceView f22l;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public boolean f23n;

    /* renamed from: o */
    private Bundle f24o = new Bundle();
    /* access modifiers changed from: private */

    /* renamed from: p */
    public C0042i f25p;

    /* renamed from: q */
    private boolean f26q = false;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public ProgressBar f27r = null;

    /* renamed from: s */
    private Runnable f28s = new Runnable() {
        public final void run() {
            int k = UnityPlayer.this.nativeActivityIndicatorStyle();
            if (k >= 0) {
                if (UnityPlayer.this.f27r == null) {
                    UnityPlayer.this.f27r = new ProgressBar(UnityPlayer.this.f21k, null, new int[]{16842874, 16843401, 16842873, 16843400}[k]);
                    UnityPlayer.this.f27r.setIndeterminate(true);
                    UnityPlayer.this.f27r.setLayoutParams(new LayoutParams(-2, -2, 51));
                    UnityPlayer.this.addView(UnityPlayer.this.f27r);
                }
                UnityPlayer.this.f27r.setVisibility(0);
                UnityPlayer.this.bringChildToFront(UnityPlayer.this.f27r);
            }
        }
    };

    /* renamed from: t */
    private Runnable f29t = new Runnable() {
        public final void run() {
            if (UnityPlayer.this.f27r != null) {
                UnityPlayer.this.f27r.setVisibility(8);
                UnityPlayer.this.removeView(UnityPlayer.this.f27r);
                UnityPlayer.this.f27r = null;
            }
        }
    };

    /* renamed from: com.unity3d.player.UnityPlayer$4 */
    class C00184 extends BroadcastReceiver {

        /* renamed from: a */
        final /* synthetic */ UnityPlayer f57a;

        public void onReceive(Context context, Intent intent) {
            this.f57a.m21b();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$a */
    private class C0024a extends PhoneStateListener {
        private C0024a() {
        }

        /* synthetic */ C0024a(UnityPlayer unityPlayer, byte b) {
            this();
        }

        public final void onCallStateChanged(int i, String str) {
            boolean z = true;
            UnityPlayer unityPlayer = UnityPlayer.this;
            if (i != 1) {
                z = false;
            }
            unityPlayer.nativeMuteMasterAudio(z);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$b */
    enum C0025b {
        PAUSE,
        RESUME,
        QUIT,
        FOCUS_GAINED,
        FOCUS_LOST,
        NEXT_FRAME
    }

    /* renamed from: com.unity3d.player.UnityPlayer$c */
    private class C0026c extends Thread {

        /* renamed from: a */
        Handler f84a;

        /* renamed from: b */
        boolean f85b;

        private C0026c() {
            this.f85b = false;
        }

        /* synthetic */ C0026c(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        private void m51a(C0025b bVar) {
            Message.obtain(this.f84a, 2269, bVar).sendToTarget();
        }

        /* renamed from: a */
        public final void mo61a() {
            m51a(C0025b.QUIT);
        }

        /* renamed from: a */
        public final void mo62a(boolean z) {
            m51a(z ? C0025b.FOCUS_GAINED : C0025b.FOCUS_LOST);
        }

        /* renamed from: b */
        public final void mo63b() {
            m51a(C0025b.RESUME);
        }

        /* renamed from: c */
        public final void mo64c() {
            m51a(C0025b.PAUSE);
        }

        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.f84a = new Handler(new Callback() {
                public final boolean handleMessage(Message message) {
                    if (message.what != 2269) {
                        return false;
                    }
                    C0025b bVar = (C0025b) message.obj;
                    if (bVar == C0025b.QUIT) {
                        Looper.myLooper().quit();
                    } else if (bVar == C0025b.RESUME) {
                        C0026c.this.f85b = true;
                    } else if (bVar == C0025b.PAUSE) {
                        C0026c.this.f85b = false;
                        UnityPlayer.this.executeGLThreadJobs();
                    } else if (bVar == C0025b.FOCUS_LOST) {
                        if (!C0026c.this.f85b) {
                            UnityPlayer.this.executeGLThreadJobs();
                        }
                    } else if (bVar == C0025b.NEXT_FRAME) {
                        UnityPlayer.this.executeGLThreadJobs();
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.m21b();
                        }
                    }
                    if (C0026c.this.f85b) {
                        Message.obtain(C0026c.this.f84a, 2269, C0025b.NEXT_FRAME).sendToTarget();
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$d */
    private abstract class C0028d implements Runnable {
        private C0028d() {
        }

        /* synthetic */ C0028d(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        public abstract void mo58a();

        public final void run() {
            if (!UnityPlayer.this.isFinishing()) {
                mo58a();
            }
        }
    }

    static {
        new C0040g().mo121a();
        f10m = false;
        f10m = loadLibraryStatic("main");
    }

    public UnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
        if (contextWrapper instanceof Activity) {
            currentActivity = (Activity) contextWrapper;
        }
        this.f21k = contextWrapper;
        m12a();
        if (C0035e.f110b) {
            if (currentActivity != null) {
                C0035e.f111c.mo107a(currentActivity, new Runnable() {
                    public final void run() {
                        UnityPlayer.this.mo8a((Runnable) new Runnable() {
                            public final void run() {
                                UnityPlayer.this.f15e.mo126d();
                                UnityPlayer.this.m30e();
                            }
                        });
                    }
                });
            } else {
                this.f15e.mo126d();
            }
        }
        m14a(this.f21k.getApplicationInfo());
        if (!C0041h.m73c()) {
            AlertDialog create = new Builder(this.f21k).setTitle("Failure to initialize!").setPositiveButton("OK", new OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.this.m21b();
                }
            }).setMessage("Your hardware does not support this application, sorry!").create();
            create.setCancelable(false);
            create.show();
            return;
        }
        initJni(contextWrapper);
        nativeFile(this.f21k.getPackageCodePath());
        m34g();
        this.f22l = new SurfaceView(contextWrapper);
        this.f22l.getHolder().setFormat(2);
        this.f22l.getHolder().addCallback(new SurfaceHolder.Callback() {
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                UnityPlayer.this.m13a(0, surfaceHolder.getSurface());
            }

            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.m13a(0, surfaceHolder.getSurface());
            }

            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.m13a(0, (Surface) null);
            }
        });
        this.f22l.setFocusable(true);
        this.f22l.setFocusableInTouchMode(true);
        addView(this.f22l);
        this.f23n = false;
        nativeInitWWW(WWW.class);
        nativeInitWebRequest(UnityWebRequest.class);
        m36h();
        this.f20j = (TelephonyManager) this.f21k.getSystemService("phone");
        this.f11a.start();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (!C0041h.m73c()) {
            C0032c.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
        } else {
            nativeUnitySendMessage(str, str2, str3);
        }
    }

    /* renamed from: a */
    private static String m11a(String str) {
        byte[] bArr;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(str);
            long length = new File(str).length();
            fileInputStream.skip(length - Math.min(length, 65558));
            byte[] bArr2 = new byte[1024];
            for (int i = 0; i != -1; i = fileInputStream.read(bArr2)) {
                instance.update(bArr2, 0, i);
            }
            bArr = instance.digest();
        } catch (FileNotFoundException e) {
            bArr = null;
        } catch (IOException e2) {
            bArr = null;
        } catch (NoSuchAlgorithmException e3) {
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private void m12a() {
        try {
            File file = new File(this.f21k.getPackageCodePath(), "assets/bin/Data/settings.xml");
            InputStream open = file.exists() ? new FileInputStream(file) : this.f21k.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(open, null);
            String str = null;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    str2 = newPullParser.getName();
                    String str3 = str;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str = str3;
                } else if (eventType == 3) {
                    str2 = null;
                } else if (eventType == 4 && str != null) {
                    if (str2.equalsIgnoreCase("integer")) {
                        this.f24o.putInt(str, Integer.parseInt(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("string")) {
                        this.f24o.putString(str, newPullParser.getText());
                    } else if (str2.equalsIgnoreCase("bool")) {
                        this.f24o.putBoolean(str, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("float")) {
                        this.f24o.putFloat(str, Float.parseFloat(newPullParser.getText()));
                    }
                    str = null;
                }
            }
        } catch (Exception e) {
            C0032c.Log(6, "Unable to locate player settings. " + e.getLocalizedMessage());
            m21b();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m13a(int i, Surface surface) {
        if (!this.f13c) {
            m25b(0, surface);
        }
    }

    /* renamed from: a */
    private static void m14a(ApplicationInfo applicationInfo) {
        if (f10m && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0041h.m71a();
        }
    }

    /* renamed from: a */
    private void m15a(C0028d dVar) {
        if (!isFinishing()) {
            m24b((Runnable) dVar);
        }
    }

    /* renamed from: a */
    private static String[] m20a(Context context) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        try {
            int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + packageName);
                if (file.exists()) {
                    if (i > 0) {
                        String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                        if (new File(str).isFile()) {
                            vector.add(str);
                        }
                    }
                    if (i > 0) {
                        String str2 = file + File.separator + "patch." + i + "." + packageName + ".obb";
                        if (new File(str2).isFile()) {
                            vector.add(str2);
                        }
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        } catch (NameNotFoundException e) {
            return new String[0];
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m21b() {
        if ((this.f21k instanceof Activity) && !((Activity) this.f21k).isFinishing()) {
            ((Activity) this.f21k).finish();
        }
    }

    /* renamed from: b */
    private void m24b(Runnable runnable) {
        if (C0041h.m73c()) {
            if (Thread.currentThread() == this.f11a) {
                runnable.run();
            } else {
                this.f16f.add(runnable);
            }
        }
    }

    /* renamed from: b */
    private boolean m25b(int i, Surface surface) {
        if (!C0041h.m73c()) {
            return false;
        }
        nativeRecreateGfxState(i, surface);
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m27c() {
        reportSoftInputStr(null, 1, true);
        if (this.f15e.mo129g()) {
            if (C0041h.m73c()) {
                final Semaphore semaphore = new Semaphore(0);
                if (isFinishing()) {
                    m24b((Runnable) new Runnable() {
                        public final void run() {
                            UnityPlayer.this.m28d();
                            semaphore.release();
                        }
                    });
                } else {
                    m24b((Runnable) new Runnable() {
                        public final void run() {
                            if (UnityPlayer.this.nativePause()) {
                                UnityPlayer.this.f23n = true;
                                UnityPlayer.this.m28d();
                                semaphore.release(2);
                                return;
                            }
                            semaphore.release();
                        }
                    });
                }
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0032c.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    C0032c.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.f15e.mo125c(false);
            this.f15e.mo124b(true);
            if (this.f18h) {
                this.f20j.listen(this.f19i, 0);
            }
            this.f11a.mo64c();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m28d() {
        nativeDone();
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m30e() {
        if (this.f15e.mo128f()) {
            this.f15e.mo125c(true);
            if (C0041h.m73c()) {
                m34g();
            }
            m24b((Runnable) new Runnable() {
                public final void run() {
                    UnityPlayer.this.nativeResume();
                }
            });
            this.f11a.mo63b();
        }
    }

    /* renamed from: f */
    private static void m33f() {
        if (C0041h.m73c()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            C0041h.m72b();
        }
    }

    /* renamed from: g */
    private void m34g() {
        String[] a;
        if (this.f24o.getBoolean("useObb")) {
            for (String str : m20a((Context) this.f21k)) {
                String a2 = m11a(str);
                if (this.f24o.getBoolean(a2)) {
                    nativeFile(str);
                }
                this.f24o.remove(a2);
            }
        }
    }

    /* renamed from: h */
    private void m36h() {
        if (this.f21k instanceof Activity) {
            ((Activity) this.f21k).getWindow().setFlags(1024, 1024);
        }
    }

    private final native void initJni(Context context);

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            C0032c.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            C0032c.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final native int nativeActivityIndicatorStyle();

    private final native void nativeDone();

    private final native void nativeFile(String str);

    /* access modifiers changed from: private */
    public final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWWW(Class cls);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* access modifiers changed from: private */
    public final native boolean nativePause();

    private final native void nativeRecreateGfxState(int i, Surface surface);

    /* access modifiers changed from: private */
    public final native boolean nativeRender();

    /* access modifiers changed from: private */
    public final native void nativeResume();

    /* access modifiers changed from: private */
    public final native void nativeSetInputCanceled(boolean z);

    /* access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo8a(Runnable runnable) {
        if (this.f21k instanceof Activity) {
            ((Activity) this.f21k).runOnUiThread(runnable);
        } else {
            C0032c.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    /* access modifiers changed from: protected */
    public void addPhoneCallListener() {
        this.f18h = true;
        this.f20j.listen(this.f19i, 32);
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f22l instanceof SurfaceView) {
            this.f22l.getHolder().setSizeFromLayout();
        }
        if (this.f25p != null) {
            this.f25p.updateVideoLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void disableLogger() {
        C0032c.f104a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.f13c = surface != null;
            mo8a((Runnable) new Runnable() {
                public final void run() {
                    if (UnityPlayer.this.f13c) {
                        UnityPlayer.this.removeView(UnityPlayer.this.f22l);
                    } else {
                        UnityPlayer.this.addView(UnityPlayer.this.f22l);
                    }
                }
            });
        }
        return m25b(i, surface);
    }

    /* access modifiers changed from: protected */
    public void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f16f.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    public Bundle getSettings() {
        return this.f24o;
    }

    /* access modifiers changed from: protected */
    public int getSplashMode() {
        return this.f24o.getInt("splash_mode");
    }

    public View getView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void hideSoftInput() {
        final C00206 r0 = new Runnable() {
            public final void run() {
                if (UnityPlayer.this.f12b != null) {
                    UnityPlayer.this.f12b.dismiss();
                    UnityPlayer.this.f12b = null;
                }
            }
        };
        if (C0035e.f109a) {
            m15a((C0028d) new C0028d() {
                /* renamed from: a */
                public final void mo58a() {
                    UnityPlayer.this.mo8a(r0);
                }
            });
        } else {
            mo8a((Runnable) r0);
        }
    }

    public void init(int i, boolean z) {
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return nativeInjectEvent(inputEvent);
    }

    /* access modifiers changed from: protected */
    public boolean isFinishing() {
        if (!this.f23n) {
            boolean z = (this.f21k instanceof Activity) && ((Activity) this.f21k).isFinishing();
            this.f23n = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void kill() {
        Process.killProcess(Process.myPid());
    }

    /* access modifiers changed from: protected */
    public boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.f25p != null) {
            this.f26q = this.f25p.mo131a();
            if (!this.f26q) {
                this.f25p.pause();
                return;
            }
            return;
        }
        m27c();
    }

    public void quit() {
        this.f23n = true;
        if (!this.f15e.mo127e()) {
            pause();
        }
        this.f11a.mo61a();
        try {
            this.f11a.join(4000);
        } catch (InterruptedException e) {
            this.f11a.interrupt();
        }
        if (this.f17g != null) {
            this.f21k.unregisterReceiver(this.f17g);
        }
        this.f17g = null;
        if (C0041h.m73c()) {
            removeAllViews();
        }
        kill();
        m33f();
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m15a((C0028d) new C0028d() {
            /* renamed from: a */
            public final void mo58a() {
                if (z) {
                    UnityPlayer.this.nativeSetInputCanceled(true);
                } else if (str != null) {
                    UnityPlayer.this.nativeSetInputString(str);
                }
                if (i == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    public void resume() {
        this.f15e.mo124b(false);
        if (this.f25p == null) {
            m30e();
        } else if (!this.f26q) {
            this.f25p.start();
        }
    }

    /* access modifiers changed from: protected */
    public void setSoftInputStr(final String str) {
        mo8a((Runnable) new Runnable() {
            public final void run() {
                if (UnityPlayer.this.f12b != null && str != null) {
                    UnityPlayer.this.f12b.mo110a(str);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        final String str3 = str;
        final int i2 = i;
        final boolean z5 = z;
        final boolean z6 = z2;
        final boolean z7 = z3;
        final boolean z8 = z4;
        final String str4 = str2;
        mo8a((Runnable) new Runnable() {
            public final void run() {
                UnityPlayer.this.f12b = new C0036f(UnityPlayer.this.f21k, this, str3, i2, z5, z6, z7, str4);
                UnityPlayer.this.f12b.show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        final Semaphore semaphore = new Semaphore(0);
        final AtomicInteger atomicInteger = new AtomicInteger(-1);
        final String str2 = str;
        final int i6 = i;
        final int i7 = i2;
        final int i8 = i3;
        final boolean z2 = z;
        final int i9 = i4;
        final int i10 = i5;
        mo8a((Runnable) new Runnable() {
            public final void run() {
                if (UnityPlayer.this.f25p != null) {
                    C0032c.Log(5, "Video already playing");
                    atomicInteger.set(2);
                    semaphore.release();
                    return;
                }
                UnityPlayer.this.f25p = new C0042i(UnityPlayer.this.f21k, str2, i6, i7, i8, z2, (long) i9, (long) i10, new C0043a() {
                    /* renamed from: a */
                    public final void mo41a(int i) {
                        atomicInteger.set(i);
                        if (i == 3) {
                            if (UnityPlayer.this.f22l.getParent() == null) {
                                UnityPlayer.this.addView(UnityPlayer.this.f22l);
                            }
                            if (UnityPlayer.this.f25p != null) {
                                UnityPlayer.this.f25p.destroyPlayer();
                                UnityPlayer.this.removeView(UnityPlayer.this.f25p);
                                UnityPlayer.this.f25p = null;
                            }
                            UnityPlayer.this.resume();
                        }
                        if (i != 0) {
                            semaphore.release();
                        }
                    }
                });
                UnityPlayer.this.addView(UnityPlayer.this.f25p);
            }
        });
        boolean z3 = false;
        try {
            semaphore.acquire();
            z3 = atomicInteger.get() != 2;
        } catch (InterruptedException e) {
        }
        C0032c.Log(2, "Video returned " + (z3 ? "OK" : "FAIL"));
        if (!z3) {
            C0032c.Log(4, "Video failed");
            if (this.f25p != null) {
                mo8a((Runnable) new Runnable() {
                    public final void run() {
                        if (UnityPlayer.this.f22l.getParent() == null) {
                            UnityPlayer.this.addView(UnityPlayer.this.f22l);
                        }
                        if (UnityPlayer.this.f25p != null) {
                            UnityPlayer.this.f25p.destroyPlayer();
                            UnityPlayer.this.removeView(UnityPlayer.this.f25p);
                            UnityPlayer.this.f25p = null;
                        }
                        UnityPlayer.this.resume();
                    }
                });
            }
        } else if (this.f25p != null) {
            mo8a((Runnable) new Runnable() {
                public final void run() {
                    if (UnityPlayer.this.f25p != null) {
                        UnityPlayer.this.m27c();
                        UnityPlayer.this.f25p.requestFocus();
                        UnityPlayer.this.removeView(UnityPlayer.this.f22l);
                    }
                }
            });
        }
        return z3;
    }

    /* access modifiers changed from: protected */
    public void startActivityIndicator() {
        mo8a(this.f28s);
    }

    /* access modifiers changed from: protected */
    public void stopActivityIndicator() {
        mo8a(this.f29t);
    }

    public void windowFocusChanged(final boolean z) {
        this.f15e.mo123a(z);
        if (z && this.f12b != null) {
            reportSoftInputStr(null, 1, false);
        }
        m24b((Runnable) new Runnable() {
            public final void run() {
                UnityPlayer.this.nativeFocusChanged(z);
            }
        });
        this.f11a.mo62a(z);
        m30e();
    }
}
