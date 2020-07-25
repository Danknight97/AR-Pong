package com.vuforia;

import java.nio.ByteBuffer;

public class Surface extends SmartTerrainTrackable {
    private long swigCPtr;

    protected Surface(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.Surface_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Surface obj) {
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
                VuforiaJNI.delete_Surface(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Surface) {
            return ((Surface) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.Surface_getClassType(), true);
    }

    public Mesh getNavMesh() {
        long cPtr = VuforiaJNI.Surface_getNavMesh(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Mesh(cPtr, false);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(VuforiaJNI.Surface_getBoundingBox(this.swigCPtr, this), false);
    }

    public int getNumMeshBoundaries() {
        return VuforiaJNI.Surface_getNumMeshBoundaries(this.swigCPtr, this);
    }

    public ByteBuffer getMeshBoundaries() {
        return VuforiaJNI.Surface_getMeshBoundaries(this.swigCPtr, this);
    }
}
