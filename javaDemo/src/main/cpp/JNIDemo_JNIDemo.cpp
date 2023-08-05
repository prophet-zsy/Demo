
#include "jni.h"
#include "iostream"
#include "stdio.h"
#include "JNIDemo_JNIDemo.h"
using namespace std;

// IDEA 没有很适合写jni

// 【g++以及gcc的区别】 https://zhuanlan.zhihu.com/p/100050970
// 【IntelliJ IDEA平台下JNI编程全解】https://blog.csdn.net/u013709270/article/details/106589498
// 编译命令 （win平台）
// g++ -I"C:\software\jdk-11.0.16.1\include" -I"C:\software\jdk-11.0.16.1\include\win32" -shared -o JNIDemo_JNIDemo.dll .\JNIDemo_JNIDemo.cpp

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_JNIDemo_JNIDemo_invokeNative(JNIEnv *, jclass) {
    cout << "Hello World in cpp !" << endl;
    printf("Hello World in cpp !\n");
}
#ifdef __cplusplus
}
#endif