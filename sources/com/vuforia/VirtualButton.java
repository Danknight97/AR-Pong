package com.vuforia;

public class VirtualButton {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class SENSITIVITY {
        public static final int HIGH = 0;
        public static final int LOW = 2;
        public static final int MEDIUM = 1;

        private SENSITIVITY() {
        }
    }

    protected VirtualButton(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VirtualButton obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof VirtualButton) {
            return ((VirtualButton) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public boolean setArea(Area area) {
        return VuforiaJNI.VirtualButton_setArea(this.swigCPtr, this, Area.getCPtr(area), area);
    }

    public Area getArea() {
        long cPtr = VuforiaJNI.VirtualButton_getArea(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        switch (new Area(cPtr, false).getType()) {
            case 0:
                return new Rectangle(cPtr, false);
            case 1:
                return new RectangleInt(cPtr, false);
            default:
                return null;
        }
    }

    public boolean setSensitivity(int sensitivity) {
        return VuforiaJNI.VirtualButton_setSensitivity(this.swigCPtr, this, sensitivity);
    }

    public boolean setEnabled(boolean enabled) {
        return VuforiaJNI.VirtualButton_setEnabled(this.swigCPtr, this, enabled);
    }

    public boolean isEnabled() {
        return VuforiaJNI.VirtualButton_isEnabled(this.swigCPtr, this);
    }

    public String getName() {
        return VuforiaJNI.VirtualButton_getName(this.swigCPtr, this);
    }

    public int getID() {
        return VuforiaJNI.VirtualButton_getID(this.swigCPtr, this);
    }
}
