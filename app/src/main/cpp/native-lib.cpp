#include <jni.h>
#include <string>

#include <FaceDetector.h>
#include <FaceLandmarker.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_seeta_sdk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_seeta_sdk_MainActivity_dispose(
        JNIEnv* env,
        jobject /* this */) {
    seeta::ModelSetting modelSetting = toSetting(env, setting);

    seeta::FaceDetector *facedector = new seeta::FaceDetector(modelSetting);

    facedector->FaceDetector(seeta::FaceDetector::Property(property));
    return (jlong)facedector;
    delete facedector;
}

/*
 * Class:     com_seeta_sdk_FaceDetector
 * Method:    construct
 * Signature: (Lcom/seeta/sdk/SeetaModelSetting;)V
 */
JNIEXPORT void JNICALL Java_com_seeta_sdk_FaceDetector_construct
        (JNIEnv *, jobject, jobject);


