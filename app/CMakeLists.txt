cmake_minimum_required(VERSION 3.10.2)


project("Seetaface_java")

#set(lib_src_dir ${CMAKE_SOURCE_DIR}/src/main/cpp/include/seeta)

#将native-lib关联到第三方的头文件
#target_include_directories(SeetaFaceDetector600_java PRIVATE ${lib_src_dir})

#导入第三方库，不同的第三方库需要分开导入
#add_library(libSeetaFaceDetector600 SHARED IMPORTED)
#find_package(libSeetaFaceDetector600 REQUIRED)

#include_directories(${TenniS_INCLUDE_DIRS})
#set_target_properties(libSeetaFaceDetector600
#        PROPERTIES IMPORTED_LOCATION
#        ${CMAKE_SOURCE_DIR}/src/main/nativeLibs/${CMAKE_ANDROID_ARCH_ABI}/libSeetaFaceDetector600.so)

#message("CMAKE_SOURCE_DIR = ${CMAKE_SOURCE_DIR}")
#message(STATUS "PROJECT_SOURCE_DIR = ${PROJECT_SOURCE_DIR}")
#message(WARNING "CMAKE_BINARY_DIR = ${CMAKE_BINARY_DIR}")
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

#find_library( # Sets the name of the path variable.
#        log-lib
#
#        # Specifies the name of the NDK library that
#        # you want CMake to locate.
#        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

#add_executable(libSeetaFaceDetector600_java ${CMAKE_SOURCE_DIR}/src/main/cpp/include/seeta) # 生成可执行文件
#将所有库关联到同NDK原生log库中
#target_link_libraries( # Specifies the target library.
#        libSeetaFaceDetector600
#        # Links the target library to the log library
#        # included in the NDK.
#        ${log-lib})

######################################################
# 自定义CMake
## 创建一个共享库
#set(CMAKE_CXX_COMPILER ${tools}/bin/arm-linux-gnueabi-g++)
set(seeta_java_dir ${CMAKE_CURRENT_SOURCE_DIR}/src/main)
## 调用FaceDetector的头文件
set(seeta_java_include_dir ${seeta_java_dir}/cpp/include/FaceDetector/seeta)

add_library(
        SeetaFaceDetector600_java
        SHARED
        src/main/cpp/include/FaceDetector/FaceDetectorJni.cpp)

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
        ${seeta_java_dir}/nativeLibs/${CMAKE_ANDROID_ARCH_ABI}/libSeetaFaceDetector600.so)

## 找到log-lib
find_library(log-lib log)
## 把所有库文件都引入工程
target_link_libraries(SeetaFaceDetector600_java SeetaFaceDetector600 ${log-lib})