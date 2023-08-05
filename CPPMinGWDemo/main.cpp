//#include "execinfo.h"  // 用获取运行时信息，比如打印调用栈之类的，  MinGW上没有这个头文件
//#include "demo_thread.h"
//#include "demo_thread.cpp"


#include <cassert>
#include <cstdio>
#include <cstring>
#include "B/b.h"


extern int testThread();  // 函数是否使用extern修饰 无所谓，因为没有extern 也可以起到


int main() {

//    todo  cpp导入外部文件 方式的讨论 (多文件编译demo)
    testThread();
//     cpp同一个文件中，使用一个变量、类或函数，要么将其定义（实现）出来，要么将其声明出来
//    （定义的方式，  编译时 只编一个要使用的文件）
//    定义的方式包括：1、直接在本文件中最开始的地方（使用之前）将要使用的变量、类或函数 定义（实现）出来；
//                 2、或者在另一个cpp文件中将要使用的变量、类或函数 定义（实现）出来，然后通过include的方式将其导入到使用的文件中；
//    （声明的方式，  编译时 需要编译要使用的文件和其他包含实现的文件）
//    声明的方式包括：1、直接在本文件中最开始的地方（使用之前）将要使用的变量、类或函数 声明出来，对应的实现放在其他cpp文件中；
//                 2、在其他头文件中将要使用的变量、类或函数 声明出来，然后在本文件中最开始的地方（使用之前）将其include进来，对应的实现放在其他cpp文件中；
//                 3、针对变量可以使用extern修饰，来说明当前不是定义，而是声明使用外部文件已经定义好的全局变量

//    以上描述为以下几种要点的组合：
//    extern 声明
//    include 导入声明
//    include 直接导入定义
//    CMakeLists.txt编译可执行目标文件时，add_executable()添加要编译的文件


//    assert(false && "hello ");


//    test();

    return 0;
}
