cmake_minimum_required(VERSION 3.10.2)


project("Seetaface_java")

######################################################
# 自定义CMake
## 创建一个共享库
#set(CMAKE_CXX_COMPILER ${tools}/bin/arm-linux-gnueabi-g++)
set(seeta_java_dir ${CMAKE_CURRENT_SOURCE_DIR})
set(seeta_lib_dir ${seeta_java_dir}/../../../)
## 调用FaceDetector的头文件
set(seeta_java_include_dir ${seeta_java_dir}/seeta)

message("seeta_java_dir = ${seeta_java_dir}")
message("seeta_lib_dir = ${seeta_lib_dir}")
message("seeta_java_include_dir = ${seeta_java_include_dir}")

add_library(
        SeetaFaceDetector600_java
        SHARED
        FaceDetectorJni.cpp)

## 设置文件输出位置
set_target_properties(SeetaFaceDetector600_java
        PROPERTIES
        CMAKE_LIBRARY_OUTPUT_DIRECTORY
        "${seeta_java_dir}/jniLibs/${CMAKE_ANDROID_ARCH_ABI}")

## 加入头文件
target_include_directories(SeetaFaceDetector600_java PRIVATE ${seeta_java_include_dir})

## 创建一个静态库，libSeetaFaceDetector600，直接引用libSeetaFaceDetector600.so
add_library(SeetaFaceDetector600 SHARED IMPORTED)
set_target_properties(SeetaFaceDetector600
        PROPERTIES IMPORTED_LOCATION
        ${seeta_lib_dir}/nativeLibs/${ANDROID_ABI}/libSeetaFaceDetector600.so)

## 找到log-lib
find_library(log-lib log)
## 把所有库文件都引入工程
target_link_libraries(SeetaFaceDetector600_java SeetaFaceDetector600 ${log-lib})