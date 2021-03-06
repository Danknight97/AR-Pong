package com.vuforia;

public class SmartTerrainBuilder {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SmartTerrainBuilder(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(SmartTerrainBuilder obj) {
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
                VuforiaJNI.delete_SmartTerrainBuilder(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof SmartTerrainBuilder) {
            return ((SmartTerrainBuilder) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.SmartTerrainBuilder_getClassType(), true);
    }

    public Type getType() {
        return new Type(VuforiaJNI.SmartTerrainBuilder_getType(this.swigCPtr, this), true);
    }

    public boolean isOfType(Type type) {
        return VuforiaJNI.SmartTerrainBuilder_isOfType(this.swigCPtr, this, Type.getCPtr(type), type);
    }

    public Reconstruction createReconstruction(Type type) {
        long cPtr = VuforiaJNI.SmartTerrainBuilder_createReconstruction(this.swigCPtr, this, Type.getCPtr(type), type);
        if (cPtr == 0) {
            return null;
        }
        return new ReconstructionFromTarget(cPtr, false);
    }

    public boolean destroyReconstruction(Reconstruction reco) {
        return VuforiaJNI.SmartTerrainBuilder_destroyReconstruction(this.swigCPtr, this, Reconstruction.getCPtr(reco), reco);
    }

    public long getNumReconstructions() {
        return VuforiaJNI.SmartTerrainBuilder_getNumReconstructions(this.swigCPtr, this);
    }

    public boolean addReconstruction(Reconstruction obj) {
        return VuforiaJNI.SmartTerrainBuilder_addReconstruction(this.swigCPtr, this, Reconstruction.getCPtr(obj), obj);
    }

    public boolean removeReconstruction(long index) {
        return VuforiaJNI.SmartTerrainBuilder_removeReconstruction(this.swigCPtr, this, index);
    }

    public Reconstruction getReconstruction(long index) {
        long cPtr = VuforiaJNI.SmartTerrainBuilder_getReconstruction(this.swigCPtr, this, index);
        if (cPtr == 0) {
            return null;
        }
        return new ReconstructionFromTarget(cPtr, false);
    }

    public boolean init() {
        return VuforiaJNI.SmartTerrainBuilder_init(this.swigCPtr, this);
    }

    public boolean deinit() {
        return VuforiaJNI.SmartTerrainBuilder_deinit(this.swigCPtr, this);
    }
}
