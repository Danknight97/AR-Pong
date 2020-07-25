package com.vuforia;

public class ViewList {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ViewList(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ViewList obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_ViewList(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof ViewList) {
            return ((ViewList) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public long getNumViews() {
        return VuforiaJNI.ViewList_getNumViews(this.swigCPtr, this);
    }

    public int getView(int idx) {
        return VuforiaJNI.ViewList_getView(this.swigCPtr, this, idx);
    }

    public boolean contains(int view) {
        return VuforiaJNI.ViewList_contains(this.swigCPtr, this, view);
    }
}
