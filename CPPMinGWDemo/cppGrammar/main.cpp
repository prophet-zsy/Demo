//
// Created by SM2887 on 2023/1/20.
//

#include <memory>
#include "iostream"
#include "iomanip"  // cpp中标准输入输出的格式控制库
#include "DemoTemplate.h"
#include "StringDemo.h"

using namespace std;


class A {
public:
    void show() {
        cout << "A::show()" << endl;
    }
};

int global_i;  // 全局变量隐式初始化为0

int main() {
    //   初始化demo   【C++中五花八门的初始化】https://zhuanlan.zhihu.com/p/365769082
    int i = 0;
    int j;  // c++中隐式初始化（栈上分配的内存，应该是随机值） （java中应该是直接报未初始化的错误）
    A aa;
    cout << i << endl;
    cout << j << endl;
    cout << global_i << endl;
    aa.show();


    cout << "Hello, World!" << endl;

//    cpp 和 c语言混编
//   c语言中无函数重载，cpp中有函数重载


//    【C++中指针常量和常量指针的区别】https://www.cnblogs.com/lizhenghn/p/3630405.html
    int a = 5;
//    const int b;        //  不对
    const int c = 100;  //  cpp 中 const类型的变量定义的时候就要初始化，类似java中的final

    int const* p1 = &c;   // 常量指针，指针指向的是常量，指针本身不是常量  （不能通过该指针修改指向的值）     <被指的不变>
    const int* p2 = &c;   // 常量指针，指针指向的是常量，指针本身不是常量  （不能通过该指针修改指向的值）     <被指的不变>
    int* const p3 = &a;   // 指针常量，指针本身是常量，也叫常指针，其指向的地址不可以变化，指向的内容可以变化  <指针不变>
    const int* const p4 = &c;  //  指向常量的常指针                                               <都不能变>


    StringDemo();  // c++中字符串使用demo



    //    C++中 类 不同的初始化方式     【C++创建类的对象（类的初始化）的方法区别new和不用new】https://blog.csdn.net/ytusdc/article/details/88621223
    CDemoTemplate demoTemplate = CDemoTemplate(1);      // 栈上分配
    auto * demoTemplate2 = new CDemoTemplate(2);  // 堆上分配
    delete demoTemplate2; // 回收堆上分配的对象
//    C++中 传入不同类型参数的模版类 初始化后 算是不同的类， 静态变量 不共享！！！
    auto * myClass = new MyClass<int>();
    auto * myClass2 = new MyClass<double>();
    cout << myClass->startVar << endl;
    cout << myClass2->startVar << endl;
    myClass->startVar = 30;            // 传入不同的参数类型，实例化得到的对象本质上算是不同的类，模板类中定义的静态变量并不共享
    cout << myClass->startVar << endl;
    cout << myClass2->startVar << endl;
    delete myClass;
    delete myClass2;


//   【华为大佬72小时讲完的C++教程，学完即可就业！拿走不谢，学不会我退出IT界】 03 类型输入输出格式控制 https://www.bilibili.com/video/BV1Q8411b7KT?p=4
//    int aa, cc;                //   对cpp中标准输入输出流的理解（本质：运算符重载）
//    cin >> aa >> cc;           //  （cin  可以表示键盘，输入设备） 键盘内容依次给aa 和 cc
//    cout << aa << cc << endl;  //  （cout 可以表示屏幕，输出设备） aa 和 cc 的内容依次给到屏幕
//   todo cpp 标准输出的格式控制
//   todo c语言 的printf 格式控制更加方便，cpp兼容c ，可以直接使用c中的printf
    cout << " \b十六进制：" << hex << 100 << endl;
    cout << " \b十六进制：" << setbase(16) << 100 << endl;
//    【请问clion的控制台中文乱码问题怎么正确解决呢？】https://www.zhihu.com/question/386494355









    return 0;
}




