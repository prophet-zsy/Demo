package JNIDemo;


//（良心JNI教程）【【Android开发进阶】JNI入门到精通: 用Java和C/C++打造高性能应用】https://www.bilibili.com/video/BV188411V7Yq/

// 【IntelliJ IDEA平台下JNI编程全解】https://blog.csdn.net/u013709270/article/details/106589498
public class JNIDemo {

    static {
//        System.loadLibrary("jniDemo");  // 传入文件名（不含扩展名），从java.library.path 环境变量中 查找对应的.ddl动态库
        System.load("C:\\Users\\admin\\Desktop\\Demo\\javaDemo\\src\\main\\cpp\\JNIDemo_JNIDemo.dll");         // 传入绝对路径
    }

    public static void main(String[] args) {
        invokeNative();
    }

    public static native void invokeNative();
}
