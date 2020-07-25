package com.unity3d.player;

import android.os.Build.VERSION;
import com.vuforia.p000ar.p001pl.BuildConfig;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.unity3d.player.a */
public final class C0029a extends SSLSocketFactory {

    /* renamed from: c */
    private static volatile SSLSocketFactory f98c;

    /* renamed from: d */
    private static final Object[] f99d = new Object[0];

    /* renamed from: e */
    private static final boolean f100e;

    /* renamed from: a */
    private final SSLSocketFactory f101a;

    /* renamed from: b */
    private final C0030a f102b = new C0030a();

    /* renamed from: com.unity3d.player.a$a */
    class C0030a implements HandshakeCompletedListener {
        C0030a() {
        }

        public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            SSLSession session = handshakeCompletedEvent.getSession();
            String cipherSuite = session.getCipherSuite();
            String protocol = session.getProtocol();
            String str = BuildConfig.FLAVOR;
            try {
                str = session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException e) {
            }
            C0032c.Log(2, "Connected to " + str + " using " + protocol + " protocol and " + cipherSuite + " cipher.");
        }
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 20) {
            z = true;
        }
        f100e = z;
    }

    private C0029a() {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, null, null);
        this.f101a = instance.getSocketFactory();
    }

    /* renamed from: a */
    private static Socket m57a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket) && f100e) {
            ((SSLSocket) socket).setEnabledProtocols(((SSLSocket) socket).getSupportedProtocols());
        }
        return socket;
    }

    /* renamed from: a */
    public static SSLSocketFactory m58a() {
        synchronized (f99d) {
            if (f98c != null) {
                SSLSocketFactory sSLSocketFactory = f98c;
                return sSLSocketFactory;
            }
            try {
                C0029a aVar = new C0029a();
                f98c = aVar;
                return aVar;
            } catch (Exception e) {
                C0032c.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + e.getMessage() + ")");
                return null;
            }
        }
    }

    public final Socket createSocket() {
        return m57a(this.f101a.createSocket());
    }

    public final Socket createSocket(String str, int i) {
        return m57a(this.f101a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m57a(this.f101a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m57a(this.f101a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m57a(this.f101a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m57a(this.f101a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.f101a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f101a.getSupportedCipherSuites();
    }
}
