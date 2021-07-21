//
// Created by Admin on 2021/7/20.
//

#include <jni.h>
#include <FaceDetector.h>
#include <util/Po.h>

extern "C" {

/*
* Class:     com_seeta_sdk_FaceDetector
* Method:    construct
* Signature: (Lcom/seeta/sdk/SeetaModelSetting;)V
*/
JNIEXPORT long JNICALL Java_com_seeta_sdk_FaceDetectorNative_construct
        (JNIEnv *env, jobject, jobject setting) {
    seeta::ModelSetting modelSetting = toSetting(env, setting);
    auto *facedector = new seeta::FaceDetector(modelSetting);
    return (jlong) facedector;
}

/*
 * Class:     com_seeta_sdk_FaceDetector
 * Method:    dispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_seeta_sdk_FaceDetectorNative_dispose
        (JNIEnv* env, jobject, jlong nativeId) {
    seeta::FaceDetector* facedector = (seeta::FaceDetector*)nativeId;
    delete facedector;
}

/*
 * Class:     com_seeta_sdk_FaceDetector
 * Method:    detect
 * Signature: (Lcom/seeta/sdk/SeetaImageData;)[Lcom/seeta/sdk/SeetaRect;
 */
JNIEXPORT jobjectArray JNICALL Java_com_seeta_sdk_FaceDetectorNative_detect
        (JNIEnv* env, jobject, jlong nativeId, jobject image) {
    seeta::FaceDetector *facedector = (seeta::FaceDetector *) nativeId;
    SeetaImageData imageData = toSeetaImageData(env, image);

    jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
    jbyte *array = env->GetByteArrayElements(dataArray, 0);
    imageData.data = (unsigned char *) array;

    SeetaFaceInfoArray infos = facedector->detect(imageData);
    jobject result = toSeetaFaceInfoArray(env, infos);

    env->ReleaseByteArrayElements(dataArray, array, 0);
    env->DeleteLocalRef(image);

    return result;
}

/*
 * Class:     com_seeta_sdk_FaceDetector
 * Method:    set
 * Signature: (Lcom/seeta/sdk/Property;D)V
 */
JNIEXPORT void JNICALL Java_com_seeta_sdk_FaceDetectorNative_set
        (JNIEnv* env, jobject, jlong nativeId, jobject, jdouble) {

}

/*
 * Class:     com_seeta_sdk_FaceDetector
 * Method:    get
 * Signature: (Lcom/seeta/sdk/Property;)D
 */
JNIEXPORT jdouble JNICALL Java_com_seeta_sdk_FaceDetectorNative_get
        (JNIEnv* env, jobject, jlong nativeId, jobject) {

}

}
