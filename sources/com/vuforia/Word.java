package com.vuforia;

public class Word extends Trackable {
    private long swigCPtr;

    protected Word(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.Word_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Word obj) {
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
                VuforiaJNI.delete_Word(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            return ((Word) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.Word_getClassType(), true);
    }

    public String getStringU() {
        short[] codes = VuforiaJNI.Word_getStringU(this.swigCPtr, this);
        if (codes == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(codes.length);
        for (short code : codes) {
            result.appendCodePoint(code);
        }
        return result.toString();
    }

    public int getLength() {
        return VuforiaJNI.Word_getLength(this.swigCPtr, this);
    }

    public int getNumCodeUnits() {
        return VuforiaJNI.Word_getNumCodeUnits(this.swigCPtr, this);
    }

    public Vec2F getSize() {
        return new Vec2F(VuforiaJNI.Word_getSize(this.swigCPtr, this), true);
    }

    public Image getMask() {
        long cPtr = VuforiaJNI.Word_getMask(this.swigCPtr, this);
        if (cPtr == 0) {
            return null;
        }
        return new Image(cPtr, false);
    }

    public Rectangle getLetterBoundingBox(int idx) {
        long cPtr = VuforiaJNI.Word_getLetterBoundingBox(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        return new Rectangle(cPtr, false);
    }
}
