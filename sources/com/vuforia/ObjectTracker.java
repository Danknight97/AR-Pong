package com.vuforia;

public class ObjectTracker extends Tracker {
    private long swigCPtr;

    protected ObjectTracker(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ObjectTracker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ObjectTracker obj) {
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
                VuforiaJNI.delete_ObjectTracker(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ObjectTracker) {
            return ((ObjectTracker) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ObjectTracker_getClassType(), true);
    }

    public DataSet createDataSet() {
        long cPtr = VuforiaJNI.ObjectTracker_createDataSet(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new DataSet(cPtr, false);
    }

    public boolean destroyDataSet(DataSet dataset) {
        return VuforiaJNI.ObjectTracker_destroyDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public boolean activateDataSet(DataSet dataset) {
        return VuforiaJNI.ObjectTracker_activateDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public boolean deactivateDataSet(DataSet dataset) {
        return VuforiaJNI.ObjectTracker_deactivateDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public DataSet getActiveDataSet(int idx) {
        long cPtr = VuforiaJNI.ObjectTracker_getActiveDataSet(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new DataSet(cPtr, false);
    }

    public int getActiveDataSetCount() {
        return VuforiaJNI.ObjectTracker_getActiveDataSetCount(this.swigCPtr, this);
    }

    public ImageTargetBuilder getImageTargetBuilder() {
        long cPtr = VuforiaJNI.ObjectTracker_getImageTargetBuilder(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new ImageTargetBuilder(cPtr, false);
    }

    public TargetFinder getTargetFinder() {
        long cPtr = VuforiaJNI.ObjectTracker_getTargetFinder(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new TargetFinder(cPtr, false);
    }

    public boolean persistExtendedTracking(boolean on) {
        return VuforiaJNI.ObjectTracker_persistExtendedTracking(this.swigCPtr, this, on);
    }

    public boolean resetExtendedTracking() {
        return VuforiaJNI.ObjectTracker_resetExtendedTracking(this.swigCPtr, this);
    }
}
