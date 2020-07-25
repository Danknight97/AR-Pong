package com.vuforia;

public class ReconstructionFromTarget extends Reconstruction {
    private long swigCPtr;

    protected ReconstructionFromTarget(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ReconstructionFromTarget_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ReconstructionFromTarget obj) {
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
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ReconstructionFromTarget) {
            return ((ReconstructionFromTarget) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ReconstructionFromTarget_getClassType(), true);
    }

    public boolean setInitializationTarget(Trackable trackable, Box3D occluderVolume) {
        return VuforiaJNI.ReconstructionFromTarget_setInitializationTarget__SWIG_0(this.swigCPtr, this, Trackable.getCPtr(trackable), trackable, Box3D.getCPtr(occluderVolume), occluderVolume);
    }

    public boolean setInitializationTarget(Trackable trackable, Box3D occluderVolume, Matrix34F offsetToOccluderPose) {
        return VuforiaJNI.ReconstructionFromTarget_setInitializationTarget__SWIG_1(this.swigCPtr, this, Trackable.getCPtr(trackable), trackable, Box3D.getCPtr(occluderVolume), occluderVolume, Matrix34F.getCPtr(offsetToOccluderPose), offsetToOccluderPose);
    }

    public Trackable getInitializationTarget() {
        long cPtr = VuforiaJNI.ReconstructionFromTarget_getInitializationTarget(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        Trackable tmp = new Trackable(cPtr, false);
        if (tmp.isOfType(ImageTarget.getClassType())) {
            return new ImageTarget(cPtr, false);
        }
        if (tmp.isOfType(CylinderTarget.getClassType())) {
            return new CylinderTarget(cPtr, false);
        }
        if (tmp.isOfType(MultiTarget.getClassType())) {
            return new MultiTarget(cPtr, false);
        }
        if (tmp.isOfType(VuMarkTarget.getClassType())) {
            return new VuMarkTarget(cPtr, false);
        }
        if (tmp.isOfType(VuMarkTemplate.getClassType())) {
            return new VuMarkTemplate(cPtr, false);
        }
        if (tmp.isOfType(ObjectTarget.getClassType())) {
            return new ObjectTarget(cPtr, false);
        }
        if (tmp.isOfType(Word.getClassType())) {
            return new Word(cPtr, false);
        }
        if (tmp.isOfType(Surface.getClassType())) {
            return new Surface(cPtr, false);
        }
        if (tmp.isOfType(Prop.getClassType())) {
            return new Prop(cPtr, false);
        }
        if (tmp.isOfType(DeviceTrackable.getClassType())) {
            return new DeviceTrackable(cPtr, false);
        }
        return null;
    }
}
