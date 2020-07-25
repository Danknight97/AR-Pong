package com.vuforia;

public class ImageTarget extends ObjectTarget {
    private long swigCPtr;

    protected ImageTarget(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ImageTarget_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageTarget obj) {
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
                VuforiaJNI.delete_ImageTarget(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageTarget) {
            return ((ImageTarget) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ImageTarget_getClassType(), true);
    }

    public int getNumVirtualButtons() {
        return VuforiaJNI.ImageTarget_getNumVirtualButtons(this.swigCPtr, this);
    }

    public VirtualButton getVirtualButton(int idx) {
        long cPtr = VuforiaJNI.ImageTarget_getVirtualButton__SWIG_0(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new VirtualButton(cPtr, false);
    }

    public VirtualButton getVirtualButton(String name) {
        long cPtr = VuforiaJNI.ImageTarget_getVirtualButton__SWIG_1(this.swigCPtr, this, name);
        if (cPtr == 0) {
            return null;
        }
        return new VirtualButton(cPtr, false);
    }

    public VirtualButton createVirtualButton(String name, Area area) {
        long cPtr = VuforiaJNI.ImageTarget_createVirtualButton(this.swigCPtr, this, name, Area.getCPtr(area), area);
        if (cPtr == 0) {
            return null;
        }
        return new VirtualButton(cPtr, false);
    }

    public boolean destroyVirtualButton(VirtualButton button) {
        return VuforiaJNI.ImageTarget_destroyVirtualButton(this.swigCPtr, this, VirtualButton.getCPtr(button), button);
    }

    public String getMetaData() {
        return VuforiaJNI.ImageTarget_getMetaData(this.swigCPtr, this);
    }
}
