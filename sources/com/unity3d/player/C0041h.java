package com.unity3d.player;

/* renamed from: com.unity3d.player.h */
final class C0041h {

    /* renamed from: a */
    private static boolean f120a = false;

    /* renamed from: b */
    private boolean f121b;

    /* renamed from: c */
    private boolean f122c;

    /* renamed from: d */
    private boolean f123d;

    /* renamed from: e */
    private boolean f124e;

    C0041h() {
        this.f121b = !C0035e.f110b;
        this.f122c = false;
        this.f123d = false;
        this.f124e = true;
    }

    /* renamed from: a */
    static void m71a() {
        f120a = true;
    }

    /* renamed from: b */
    static void m72b() {
        f120a = false;
    }

    /* renamed from: c */
    static boolean m73c() {
        return f120a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo123a(boolean z) {
        this.f122c = z;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo124b(boolean z) {
        this.f124e = z;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final void mo125c(boolean z) {
        this.f123d = z;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final void mo126d() {
        this.f121b = true;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final boolean mo127e() {
        return this.f124e;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final boolean mo128f() {
        return f120a && this.f122c && this.f121b && !this.f124e && !this.f123d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final boolean mo129g() {
        return this.f123d;
    }

    public final String toString() {
        return super.toString();
    }
}
