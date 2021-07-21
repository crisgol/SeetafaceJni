package com.seeta.obj;


//import com.seeta.SeetaModelSetting;


import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaModelSetting;
import com.seeta.sdk.SeetaPointF;

public class FaceRecognizer {

    public long impl = 0;

    private native void construct(SeetaModelSetting setting);
    
    public FaceRecognizer(SeetaModelSetting setting) {
        this.construct(setting);
    }

    public native void dispose();

    @Override
    protected void finalize()throws Throwable{
        super.finalize();
        this.dispose();
    }

    public native int GetCropFaceWidth();
    public native int GetCropFaceHeight();
    public native int GetCropFaceChannels();

    public native int GetExtractFeatureSize();

    public native boolean CropFace(SeetaImageData image, SeetaPointF[] points, SeetaImageData face);

    public native boolean ExtractCroppedFace(SeetaImageData face, float[] features);

    public native boolean Extract(SeetaImageData image, SeetaPointF[] points, float[] features);

    public native float CalculateSimilarity(float[] features1, float[] features2);
}
