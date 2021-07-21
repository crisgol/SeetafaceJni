package com.seeta.sdk;

/**
 * @describe 人脸检测器
 */
public class FaceDetectorNative {
    static {
        System.loadLibrary("SeetaFaceDetector600_java");
    }

    protected native long construct(SeetaModelSetting setting);
    public native void dispose(long nativeId);

    /**
     * 输入彩色图像，检测其中的人脸。
     *
     * @param nativeId  持久化序列号,对象初始化时的指针
     * @param image 输入的图像数据
     * @return 人脸信息数组
     */
    public native SeetaRect[] detect(long nativeId, SeetaImageData image);

    /**
     * 设置人脸检测器相关属性值
     *
     * @param nativeId     持久化序列号,对象初始化时的指针
     * @param property 人脸检测器属性类别
     * @param value    设置的属性值
     */
    public native void set(long nativeId,Property property, double value);

    /**
     * 获取人脸检测器相关属性值。
     *
     * @param nativeId 持久化序列号,对象初始化时的指针
     * @param property 人脸检测器属性类别
     * @return 对应的人脸属性值
     */
    public native double get(long nativeId,Property property);
}
