package com.seeta.obj;

import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaModelSetting;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;

public class FaceLandmarker {
    static{
        System.loadLibrary("SeetaFaceLandmarker600_java");
    }

    public long impl = 0;
    private native void construct(SeetaModelSetting seeting);
    public FaceLandmarker(SeetaModelSetting setting){
        this.construct(setting);
    }

    public native void dispose();
    protected void finalize()throws Throwable{
        super.finalize();
        this.dispose();
    }

    public native int number();

    public native void mark(SeetaImageData imageData, SeetaRect seetaRect, SeetaPointF[] pointFS);

    public native void mark(SeetaImageData imageData, SeetaRect seetaRect, SeetaPointF[] pointFS, int[] masks);
}
