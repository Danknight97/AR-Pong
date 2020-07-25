package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.c */
final class C0032c {

    /* renamed from: a */
    protected static boolean f104a = false;

    protected static void Log(int i, String str) {
        if (!f104a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
