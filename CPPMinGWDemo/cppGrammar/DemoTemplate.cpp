//
// Created by SM2887 on 2022/12/30.
//
#include "iostream"


using namespace std;

#include "DemoTemplate.h"

CDemoTemplate::CDemoTemplate() {
    cout << "CDemoTemplate::CDemoTemplate  " << this->var << endl;
}

CDemoTemplate::CDemoTemplate(int tmpVar) {
    this->var = tmpVar;
    cout << "CDemoTemplate::CDemoTemplate  " << this->var << endl;
}

CDemoTemplate::~CDemoTemplate() {
    cout << "CDemoTemplate::~CDemoTemplate  " << this->var << endl;
}

void CDemoTemplate::hello() {

    cout << "hello" << endl;

}

// 传入不同的参数类型，实例化得到的对象本质上算是不同的类，模板类中定义的静态变量并不共享
template<> int MyClass<int>::startVar = 3;
template<> int MyClass<double>::startVar = 3;

template<class T> int MyClass<T>::startVar = 3;


template<> MyClass<int>::MyClass() {
    MyClass<int>::startVar = 3;
}
template<> MyClass<double>::MyClass() {
    MyClass<double>::startVar = 3;
}
template<class T> MyClass<T>::MyClass() {
    MyClass<T>::startVar = 3;
}

template<> MyClass<int>::MyClass(int tmpVar) {
    MyClass<int>::startVar = tmpVar;
}
//template<> MyClass<double>::MyClass(int tmpVar) {  // todo 看下这里为什么报错
//    MyClass<double>::startVar = tmpVar;
//}
template<class T>MyClass<T>::MyClass(T tmpVar) {
    MyClass<T>::startVar = tmpVar;
}


// todo 继续写例子研究下c++模板和java泛型的区别 （C++模板 因为原理是代码生成，所以是真泛型； 而java 因为原理是运行时擦除 仅编译期检查，所以是伪泛型，存在使用上的限制：泛型类型在运行时不能正常使用）
template<class K> class Ttest : public K {  // C++中可以继承泛型类型，但java不行，因为java的泛型类型在运行时被擦除了

};


