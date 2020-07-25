package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0054a implements Runnable {

    /* renamed from: a */
    private final FMODAudioDevice f162a;

    /* renamed from: b */
    private final ByteBuffer f163b;

    /* renamed from: c */
    private final int f164c;

    /* renamed from: d */
    private final int f165d;

    /* renamed from: e */
    private final int f166e = 2;

    /* renamed from: f */
    private volatile Thread f167f;

    /* renamed from: g */
    private volatile boolean f168g;

    /* renamed from: h */
    private AudioRecord f169h;

    /* renamed from: i */
    private boolean f170i;

    C0054a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f162a = fMODAudioDevice;
        this.f164c = i;
        this.f165d = i2;
        this.f163b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m87d() {
        if (this.f169h != null) {
            if (this.f169h.getState() == 1) {
                this.f169h.stop();
            }
            this.f169h.release();
            this.f169h = null;
        }
        this.f163b.position(0);
        this.f170i = false;
    }

    /* renamed from: a */
    public final int mo836a() {
        return this.f163b.capacity();
    }

    /* renamed from: b */
    public final void mo837b() {
        if (this.f167f != null) {
            mo838c();
        }
        this.f168g = true;
        this.f167f = new Thread(this);
        this.f167f.start();
    }

    /* renamed from: c */
    public final void mo838c() {
        while (this.f167f != null) {
            this.f168g = false;
            try {
                this.f167f.join();
                this.f167f = null;
            } catch (InterruptedException e) {
            }
        }
    }

    public final void run() {
        int i;
        int i2 = 3;
        while (this.f168g) {
            if (!this.f170i && i2 > 0) {
                m87d();
                this.f169h = new AudioRecord(1, this.f164c, this.f165d, this.f166e, this.f163b.capacity());
                this.f170i = this.f169h.getState() == 1;
                if (this.f170i) {
                    this.f163b.position(0);
                    this.f169h.startRecording();
                    i = 3;
                    if (this.f170i || this.f169h.getRecordingState() != 3) {
                        i2 = i;
                    } else {
                        this.f162a.fmodProcessMicData(this.f163b, this.f169h.read(this.f163b, this.f163b.capacity()));
                        this.f163b.position(0);
                        i2 = i;
                    }
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f169h.getState() + ")");
                    i2--;
                    m87d();
                }
            }
            i = i2;
            if (this.f170i) {
            }
            i2 = i;
        }
        m87d();
    }
}
