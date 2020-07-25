package com.vuforia;

public class SmartTerrainTracker extends Tracker {
    private long swigCPtr;

    protected SmartTerrainTracker(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.SmartTerrainTracker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(SmartTerrainTracker obj) {
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
                VuforiaJNI.delete_SmartTerrainTracker(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SmartTerrainTracker) {
            return ((SmartTerrainTracker) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.SmartTerrainTracker_getClassType(), true);
    }

    public boolean setScaleToMillimeter(float scaleFactor) {
        return VuforiaJNI.SmartTerrainTracker_setScaleToMillimeter(this.swigCPtr, this, scaleFactor);
    }

    public float getScaleToMillimeter() {
        return VuforiaJNI.SmartTerrainTracker_getScaleToMillimeter(this.swigCPtr, this);
    }

    public SmartTerrainBuilder getSmartTerrainBuilder() {
        return new SmartTerrainBuilder(VuforiaJNI.SmartTerrainTracker_getSmartTerrainBuilder(this.swigCPtr, this), false);
    }
}
