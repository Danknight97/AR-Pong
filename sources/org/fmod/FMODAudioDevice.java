package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {

    /* renamed from: h */
    private static int f151h = 0;

    /* renamed from: i */
    private static int f152i = 1;

    /* renamed from: j */
    private static int f153j = 2;

    /* renamed from: k */
    private static int f154k = 3;

    /* renamed from: a */
    private volatile Thread f155a = null;

    /* renamed from: b */
    private volatile boolean f156b = false;

    /* renamed from: c */
    private AudioTrack f157c = null;

    /* renamed from: d */
    private boolean f158d = false;

    /* renamed from: e */
    private ByteBuffer f159e = null;

    /* renamed from: f */
    private byte[] f160f = null;

    /* renamed from: g */
    private volatile C0054a f161g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.f157c != null) {
            if (this.f157c.getState() == 1) {
                this.f157c.stop();
            }
            this.f157c.release();
            this.f157c = null;
        }
        this.f159e = null;
        this.f160f = null;
        this.f158d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* access modifiers changed from: 0000 */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f155a != null && this.f155a.isAlive();
    }

    public void run() {
        int i;
        int i2 = 3;
        while (this.f156b) {
            if (this.f158d || i2 <= 0) {
                i = i2;
            } else {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f151h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f152i);
                int fmodGetInfo3 = fmodGetInfo(f153j);
                if (fmodGetInfo2 * fmodGetInfo3 * 4 > round) {
                    round = fmodGetInfo3 * fmodGetInfo2 * 4;
                }
                this.f157c = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                this.f158d = this.f157c.getState() == 1;
                if (this.f158d) {
                    this.f159e = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f160f = new byte[this.f159e.capacity()];
                    this.f157c.play();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f157c.getState() + ")");
                    releaseAudioTrack();
                    i = i2 - 1;
                }
            }
            if (!this.f158d) {
                i2 = i;
            } else if (fmodGetInfo(f154k) == 1) {
                fmodProcess(this.f159e);
                this.f159e.get(this.f160f, 0, this.f159e.capacity());
                this.f157c.write(this.f160f, 0, this.f159e.capacity());
                this.f159e.position(0);
                i2 = i;
            } else {
                releaseAudioTrack();
                i2 = i;
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f155a != null) {
            stop();
        }
        this.f155a = new Thread(this, "FMODAudioDevice");
        this.f155a.setPriority(10);
        this.f156b = true;
        this.f155a.start();
        if (this.f161g != null) {
            this.f161g.mo837b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f161g == null) {
            this.f161g = new C0054a(this, i, i2);
            this.f161g.mo837b();
        }
        return this.f161g.mo836a();
    }

    public synchronized void stop() {
        while (this.f155a != null) {
            this.f156b = false;
            try {
                this.f155a.join();
                this.f155a = null;
            } catch (InterruptedException e) {
            }
        }
        if (this.f161g != null) {
            this.f161g.mo838c();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.f161g != null) {
            this.f161g.mo838c();
            this.f161g = null;
        }
    }
}
