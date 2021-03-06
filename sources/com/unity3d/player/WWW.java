package com.unity3d.player;

import com.vuforia.p000ar.p001pl.BuildConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

class WWW extends Thread {

    /* renamed from: a */
    private int f93a = 0;

    /* renamed from: b */
    private int f94b;

    /* renamed from: c */
    private String f95c;

    /* renamed from: d */
    private byte[] f96d;

    /* renamed from: e */
    private Map f97e;

    WWW(int i, String str, byte[] bArr, Map map) {
        this.f94b = i;
        this.f95c = str;
        this.f96d = bArr;
        this.f97e = map;
    }

    private static native void doneCallback(int i);

    private static native void errorCallback(int i, String str);

    private static native boolean headerCallback(int i, String str);

    private static native void progressCallback(int i, float f, float f2, double d, int i2);

    private static native boolean readCallback(int i, byte[] bArr, int i2);

    /* access modifiers changed from: protected */
    public boolean headerCallback(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(str2);
        sb.append("\n\r");
        return headerCallback(this.f94b, sb.toString());
    }

    /* access modifiers changed from: protected */
    public boolean headerCallback(Map map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            for (String str : (List) entry.getValue()) {
                sb.append((String) entry.getKey());
                sb.append(": ");
                sb.append(str);
                sb.append("\r\n");
            }
            if (entry.getKey() == null) {
                for (String str2 : (List) entry.getValue()) {
                    sb.append("Status: ");
                    sb.append(str2);
                    sb.append("\r\n");
                }
            }
        }
        return headerCallback(this.f94b, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void progressCallback(int i, int i2, int i3, int i4, long j, long j2) {
        float f;
        float f2;
        double d;
        if (i4 > 0) {
            f = ((float) i3) / ((float) i4);
            f2 = 1.0f;
            double max = ((double) Math.max(i4 - i3, 0)) / ((1000.0d * ((double) i3)) / Math.max((double) (j - j2), 0.1d));
            if (Double.isInfinite(max) || Double.isNaN(max)) {
                max = 0.0d;
            }
            d = max;
        } else if (i2 > 0) {
            f = 0.0f;
            f2 = (float) (i / i2);
            d = 0.0d;
        } else {
            return;
        }
        progressCallback(this.f94b, f2, f, d, i4);
    }

    /* access modifiers changed from: protected */
    public boolean readCallback(byte[] bArr, int i) {
        return readCallback(this.f94b, bArr, i);
    }

    public void run() {
        try {
            runSafe();
        } catch (Throwable th) {
            errorCallback(this.f94b, "Error: " + th.toString());
        }
    }

    public void runSafe() {
        String str;
        InputStream inputStream;
        InputStream inputStream2;
        boolean z;
        int i = this.f93a + 1;
        this.f93a = i;
        if (i > 5) {
            errorCallback(this.f94b, "Too many redirects");
            return;
        }
        try {
            URL url = new URL(this.f95c);
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                SSLSocketFactory a = C0029a.m58a();
                if (a != null) {
                    ((HttpsURLConnection) openConnection).setSSLSocketFactory(a);
                }
            }
            if (!url.getProtocol().equalsIgnoreCase("file") || url.getHost() == null || url.getHost().length() == 0) {
                if (this.f97e != null) {
                    for (Entry entry : this.f97e.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.f96d != null) {
                    openConnection.setDoOutput(true);
                    try {
                        OutputStream outputStream = openConnection.getOutputStream();
                        int i2 = 0;
                        while (i2 < this.f96d.length) {
                            int min = Math.min(1428, this.f96d.length - i2);
                            outputStream.write(this.f96d, i2, min);
                            i2 += min;
                            progressCallback(i2, this.f96d.length, 0, 0, 0, 0);
                        }
                    } catch (Exception e) {
                        errorCallback(this.f94b, e.toString());
                        return;
                    }
                }
                if (openConnection instanceof HttpURLConnection) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                    try {
                        int responseCode = httpURLConnection.getResponseCode();
                        Map headerFields = httpURLConnection.getHeaderFields();
                        if (headerFields != null && (responseCode == 301 || responseCode == 302)) {
                            List list = (List) headerFields.get("Location");
                            if (list != null && !list.isEmpty()) {
                                httpURLConnection.disconnect();
                                this.f95c = (String) list.get(0);
                                run();
                                return;
                            }
                        }
                    } catch (IOException e2) {
                        errorCallback(this.f94b, e2.toString());
                        return;
                    }
                }
                Map headerFields2 = openConnection.getHeaderFields();
                boolean headerCallback = headerCallback(headerFields2);
                if ((headerFields2 == null || !headerFields2.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                    headerCallback = headerCallback || headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                }
                if ((headerFields2 == null || !headerFields2.containsKey("content-type")) && openConnection.getContentType() != null) {
                    headerCallback = headerCallback || headerCallback("content-type", openConnection.getContentType());
                }
                if (headerCallback) {
                    errorCallback(this.f94b, this.f95c + " aborted");
                    return;
                }
                int i3 = openConnection.getContentLength() > 0 ? openConnection.getContentLength() : 0;
                int i4 = (url.getProtocol().equalsIgnoreCase("file") || url.getProtocol().equalsIgnoreCase("jar")) ? i3 == 0 ? 32768 : Math.min(i3, 32768) : 1428;
                int i5 = 0;
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    byte[] bArr = new byte[i4];
                    String str2 = BuildConfig.FLAVOR;
                    if (openConnection instanceof HttpURLConnection) {
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                        InputStream errorStream = httpURLConnection2.getErrorStream();
                        str = httpURLConnection2.getResponseCode() + ": " + httpURLConnection2.getResponseMessage();
                        inputStream = errorStream;
                    } else {
                        str = str2;
                        inputStream = null;
                    }
                    if (inputStream == null) {
                        z = false;
                        inputStream2 = openConnection.getInputStream();
                    } else {
                        inputStream2 = inputStream;
                        z = true;
                    }
                    for (int i6 = 0; i6 != -1; i6 = inputStream2.read(bArr)) {
                        if (readCallback(bArr, i6)) {
                            errorCallback(this.f94b, this.f95c + " aborted");
                            return;
                        }
                        if (!z) {
                            i5 += i6;
                            progressCallback(0, 0, i5, i3, System.currentTimeMillis(), currentTimeMillis);
                        }
                    }
                    if (z) {
                        errorCallback(this.f94b, str);
                    }
                    progressCallback(0, 0, i5, i5, 0, 0);
                    doneCallback(this.f94b);
                } catch (Exception e3) {
                    errorCallback(this.f94b, e3.toString());
                }
            } else {
                errorCallback(this.f94b, url.getHost() + url.getFile() + " is not an absolute path!");
            }
        } catch (MalformedURLException e4) {
            errorCallback(this.f94b, e4.toString());
        } catch (IOException e5) {
            errorCallback(this.f94b, e5.toString());
        }
    }
}
