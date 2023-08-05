#include <jni.h>
#include <string>

#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <android/log.h>


/**
 *  主要在native层 通过mmap建立内存映射的方式 实现进程间通信
 *  这里按照读写文件来实现，使用了两次拷贝；
 *
 *  Binder的实现中应该是一直维护建立过内存映射的缓冲区，
 *  通信双方共同操作这个缓冲区，生产者将内容拷贝至缓冲区，消费者可以直接读取缓冲区的内容，无需拷贝，因此节省了读取的那次拷贝，只用了一次拷贝
 */

//把java的string转化成c的字符串（声明）
char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr);


extern "C"
JNIEXPORT void JNICALL   // 提供java调用的函数
Java_com_example_binderlearn_MyBinder_write(JNIEnv *env, jobject thiz, jstring str) {

    //  内存映射使用的文件句柄
    std::string file = "/sdcard/binder";
    int m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);

    ftruncate(m_fd, 4096);  // 截断文件，设置为4K
    int8_t *m_ptr = static_cast<int8_t *>(mmap(0, 4096, PROT_READ | PROT_WRITE, MAP_SHARED, m_fd, 0));  //  建立文件与内存的映射

    char * p = Jstring2CStr(env, str);  // java String 转 c char *
    std::string data(p);

    memcpy(m_ptr, data.data(), data.size());  // copy
}

extern "C"
JNIEXPORT jstring JNICALL   // 提供java调用的函数
Java_com_example_binderlearn_MyBinder_read(JNIEnv *env, jobject thiz) {

    //  内存映射使用的文件句柄
    std::string file = "/sdcard/binder";
    int m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);

    ftruncate(m_fd, 4096);
    int8_t *m_ptr = static_cast<int8_t *>(mmap(0, 4096, PROT_READ | PROT_WRITE, MAP_SHARED, m_fd, 0));

    char *buf = static_cast<char *>(malloc(100));  // 建立接收缓存
    memcpy(buf, m_ptr, 100);  // copy
    std::string result(buf);  // 转string
    __android_log_print(ANDROID_LOG_ERROR, "Java_com_example_binderlearn_MyBinder_read", "读取数据:%s", result.c_str());  // 记录日志

    munmap(m_ptr, 4096);  // 解除内存映射
    close(m_fd);  // 关闭文件

    return env->NewStringUTF(result.c_str());
}





//把java的string转化成c的字符串（定义）
char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
    char*   rtn   =   NULL;
    jclass   clsstring   =   env->FindClass("java/lang/String");  //String
    jstring   strencode   =   env->NewStringUTF("UTF-8"); //"UTF-8"
    jmethodID   mid   =   env->GetMethodID(clsstring,   "getBytes",   "(Ljava/lang/String;)[B"); //getBytes(Str);
    jbyteArray   barr=   (jbyteArray)env->CallObjectMethod(jstr,mid,strencode); // String .getByte("GB2312");
    jsize   alen   =   env->GetArrayLength(barr);
    jbyte*   ba   =   env->GetByteArrayElements(barr,JNI_FALSE);
    if(alen   >   0)
    {
        rtn   =   (char*)malloc(alen+1);         //"\0"
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    env->ReleaseByteArrayElements(barr,ba,0);  //释放内存空间
    return rtn;
}