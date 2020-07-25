package com.vuforia;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ViewerParametersList implements Iterable<ViewerParameters> {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    private class VPIterator implements Iterator<ViewerParameters> {
        private ViewerParameters next = null;

        VPIterator() {
            if (ViewerParametersList.this.size() > 0) {
                this.next = ViewerParametersList.this.begin();
            }
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public ViewerParameters next() throws NoSuchElementException {
            if (this.next != null) {
                ViewerParameters toReturn = this.next;
                this.next = ViewerParametersList.this.next(toReturn);
                return toReturn;
            }
            throw new NoSuchElementException();
        }

        public void remove() throws UnsupportedOperationException, IllegalStateException {
            throw new UnsupportedOperationException();
        }
    }

    protected ViewerParametersList(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ViewerParametersList obj) {
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
                VuforiaJNI.delete_ViewerParametersList(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public Iterator<ViewerParameters> iterator() {
        return new VPIterator();
    }

    public static ViewerParametersList getListForAuthoringTools() {
        return new ViewerParametersList(VuforiaJNI.ViewerParametersList_getListForAuthoringTools(), false);
    }

    public void setSDKFilter(String filter) {
        VuforiaJNI.ViewerParametersList_setSDKFilter(this.swigCPtr, this, filter);
    }

    public long size() {
        return VuforiaJNI.ViewerParametersList_size(this.swigCPtr, this);
    }

    public ViewerParameters get(long idx) {
        long cPtr = VuforiaJNI.ViewerParametersList_get__SWIG_0(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new ViewerParameters(cPtr, false);
    }

    public ViewerParameters get(String name, String manufacturer) {
        long cPtr = VuforiaJNI.ViewerParametersList_get__SWIG_1(this.swigCPtr, this, name, manufacturer);
        if (cPtr == 0) {
            return null;
        }
        return new ViewerParameters(cPtr, false);
    }

    /* access modifiers changed from: private */
    public ViewerParameters begin() {
        long cPtr = VuforiaJNI.ViewerParametersList_begin(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new ViewerParameters(cPtr, false);
    }

    private ViewerParameters end() {
        long cPtr = VuforiaJNI.ViewerParametersList_end(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new ViewerParameters(cPtr, false);
    }

    /* access modifiers changed from: private */
    public ViewerParameters next(ViewerParameters last) {
        long cPtr = VuforiaJNI.ViewerParametersList_next(this.swigCPtr, this, ViewerParameters.getCPtr(last), last);
        if (cPtr == 0) {
            return null;
        }
        return new ViewerParameters(cPtr, false);
    }
}
