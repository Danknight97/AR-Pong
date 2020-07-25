package com.vuforia;

public class Prop extends SmartTerrainTrackable {
    private long swigCPtr;

    protected Prop(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.Prop_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Prop obj) {
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
                VuforiaJNI.delete_Prop(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Prop) {
            return ((Prop) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.Prop_getClassType(), true);
    }

    public Obb3D getBoundingBox() {
        return new Obb3D(VuforiaJNI.Prop_getBoundingBox(this.swigCPtr, this), false);
    }

    public Vec2F getLocalPosition() {
        return new Vec2F(VuforiaJNI.Prop_getLocalPosition(this.swigCPtr, this), false);
    }
}
