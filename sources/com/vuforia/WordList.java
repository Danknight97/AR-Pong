package com.vuforia;

public class WordList extends NonCopyable {
    private long swigCPtr;

    public static final class FILTER_MODE {
        public static final int FILTER_MODE_BLACK_LIST = 1;
        public static final int FILTER_MODE_NONE = 0;
        public static final int FILTER_MODE_WHITE_LIST = 2;

        private FILTER_MODE() {
        }
    }

    protected WordList(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.WordList_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(WordList obj) {
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
                VuforiaJNI.delete_WordList(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WordList) {
            return ((WordList) obj).swigCPtr == this.swigCPtr;
        }
        return false;
    }

    public boolean loadWordList(String path, int storageType) {
        return VuforiaJNI.WordList_loadWordList(this.swigCPtr, this, path, storageType);
    }

    public int addWordsFromFile(String path, int storageType) {
        return VuforiaJNI.WordList_addWordsFromFile(this.swigCPtr, this, path, storageType);
    }

    public boolean addWordU(String word) {
        return VuforiaJNI.WordList_addWordU(this.swigCPtr, this, Vuforia.convertStringToShortArray(word));
    }

    public boolean removeWordU(String word) {
        return VuforiaJNI.WordList_removeWordU(this.swigCPtr, this, Vuforia.convertStringToShortArray(word));
    }

    public boolean containsWordU(String word) {
        return VuforiaJNI.WordList_containsWordU(this.swigCPtr, this, Vuforia.convertStringToShortArray(word));
    }

    public boolean unloadAllLists() {
        return VuforiaJNI.WordList_unloadAllLists(this.swigCPtr, this);
    }

    public boolean setFilterMode(int mode) {
        return VuforiaJNI.WordList_setFilterMode(this.swigCPtr, this, mode);
    }

    public int getFilterMode() {
        return VuforiaJNI.WordList_getFilterMode(this.swigCPtr, this);
    }

    public boolean addWordToFilterListU(String word) {
        return VuforiaJNI.WordList_addWordToFilterListU(this.swigCPtr, this, Vuforia.convertStringToShortArray(word));
    }

    public boolean removeWordFromFilterListU(String word) {
        return VuforiaJNI.WordList_removeWordFromFilterListU(this.swigCPtr, this, Vuforia.convertStringToShortArray(word));
    }

    public boolean clearFilterList() {
        return VuforiaJNI.WordList_clearFilterList(this.swigCPtr, this);
    }

    public boolean loadFilterList(String path, int storageType) {
        return VuforiaJNI.WordList_loadFilterList(this.swigCPtr, this, path, storageType);
    }

    public int getFilterListWordCount() {
        return VuforiaJNI.WordList_getFilterListWordCount(this.swigCPtr, this);
    }

    public String getFilterListWordU(int i) {
        short[] codes = VuforiaJNI.WordList_getFilterListWordU(this.swigCPtr, this, i);
        if (codes == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(codes.length);
        for (short code : codes) {
            result.appendCodePoint(code);
        }
        return result.toString();
    }
}
