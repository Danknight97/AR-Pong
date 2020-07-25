package bitter.jnibridge;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge {

    /* renamed from: bitter.jnibridge.JNIBridge$a */
    private static class C0000a implements InvocationHandler {

        /* renamed from: a */
        private Object f0a = new Object[0];

        /* renamed from: b */
        private long f1b;

        public C0000a(long j) {
            this.f1b = j;
        }

        /* renamed from: a */
        public final void mo1a() {
            synchronized (this.f0a) {
                this.f1b = 0;
            }
        }

        public final void finalize() {
            synchronized (this.f0a) {
                if (this.f1b != 0) {
                    JNIBridge.delete(this.f1b);
                }
            }
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) {
            Object invoke;
            synchronized (this.f0a) {
                invoke = this.f1b == 0 ? null : JNIBridge.invoke(this.f1b, method.getDeclaringClass(), method, objArr);
            }
            return invoke;
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        ((C0000a) Proxy.getInvocationHandler(obj)).mo1a();
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new C0000a(j));
    }
}
