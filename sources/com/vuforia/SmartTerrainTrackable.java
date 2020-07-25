package com.vuforia;

public class SmartTerrainTrackable extends Trackable {
    private long swigCPtr;

    protected SmartTerrainTrackable(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.SmartTerrainTrackable_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(SmartTerrainTrackable obj) {
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
                VuforiaJNI.delete_SmartTerrainTrackable(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof SmartTerrainTrackable) {
            return ((SmartTerrainTrackable) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.SmartTerrainTrackable_getClassType(), true);
    }

    public Mesh getMesh() {
        long cPtr = VuforiaJNI.SmartTerrainTrackable_getMesh(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Mesh(cPtr, false);
    }

    public int getRevision() {
        return VuforiaJNI.SmartTerrainTrackable_getRevision(this.swigCPtr, this);
    }

    public Matrix34F getLocalPose() {
        return new Matrix34F(VuforiaJNI.SmartTerrainTrackable_getLocalPose(this.swigCPtr, this), false);
    }

    public SmartTerrainTrackable getParent() {
        long cPtr = VuforiaJNI.SmartTerrainTrackable_getParent(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new SmartTerrainTrackable(cPtr, false);
    }

    public long getChildrenCount() {
        return VuforiaJNI.SmartTerrainTrackable_getChildrenCount(this.swigCPtr, this);
    }

    public SmartTerrainTrackable getChild(long idx) {
        long cPtr = VuforiaJNI.SmartTerrainTrackable_getChild(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new SmartTerrainTrackable(cPtr, false);
    }
}
