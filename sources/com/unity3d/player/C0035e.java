package com.unity3d.player;

import android.os.Build.VERSION;

/* renamed from: com.unity3d.player.e */
public final class C0035e {

    /* renamed from: a */
    static final boolean f109a = (VERSION.SDK_INT >= 21);

    /* renamed from: b */
    static final boolean f110b;

    /* renamed from: c */
    static final C0031b f111c;

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 23) {
            z = false;
        }
        f110b = z;
        f111c = z ? new C0033d() : null;
    }
}
