package com.seeta.obj;

import com.seeta.sdk.FaceDetectorNative;
import com.seeta.sdk.Property;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaModelSetting;
import com.seeta.sdk.SeetaRect;

/**
 * @describe 人脸检测器
 */
public class FaceDetector extends FaceDetectorNative {

    //如果报错：no "J" field "impl" in class
    //这个地方必须得有 long型的impl，native中有使用
    //该参数在jni中的作用可能是获取的持久化序列号
    //对象指针:为了不暴露底层细节，保证接口的ABI兼容性，采用了impl指针隔离实现的方式。
    //我将指针对象传递出来，在Java层处理

    private long nativeId = 0;

    public FaceDetector(SeetaModelSetting setting) {
        nativeId = construct(setting);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.dispose(nativeId);
    }



    /**
     * 输入彩色图像，检测其中的人脸。
     *
     * @param image 输入的图像数据
     * @return 人脸信息数组
     */
    public SeetaRect[] detect(SeetaImageData image){
        return this.detect(nativeId,image);
    }

    /**
     * 设置人脸检测器相关属性值
     * @param property 人脸检测器属性类别
     * @param value 设置的属性值
     */
    public void set(Property property, double value) {
        set(nativeId,property,value);
    }

    /**
     * 获取人脸检测器相关属性值。
     * @param property 人脸检测器属性类别
     * @return 对应的人脸属性值
     */
    public double get(Property property){
        return get(nativeId,property);
    }
}
