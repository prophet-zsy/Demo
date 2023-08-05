//
// Created by SM2887 on 2023/1/20.
//

#include "iostream"

using namespace std;
//  运算符重载

class Complex {
//private:  // cpp中默认属性为private
    double real;
    double image;
public:
    Complex(double real, double image) : real(real), image(image) {}  // 如果不自己定义构造函数，会有一个默认的构造函数
    Complex & operator+(Complex x) {  // 成员函数默认包含一个this参数，所有只需要写一个参数就好
        real += x.real;
        image += x.image;
        return const_cast<Complex &>(*this);
    }
    friend ostream& operator<<(ostream & out, Complex &x);  // 友元函数
// 【C++——友元 & 内部类】https://blog.csdn.net/m0_46657980/article/details/109385050
};
ostream& operator<<(ostream & out, Complex &x) {
    out << "[" << x.real << ", " << x.image << "] ";
    return out;
}

struct CComplex {
    double real;
    double image;
};

CComplex operator+(CComplex a, CComplex b) {
    CComplex c{};
    c.real = a.real + b.real;
    c.image = a.image + b.image;
    return c;
}



int main () {
//    类的成员函数 重载运算符 + ， 类的友元函数 重载运算符 <<
    Complex x(2.5, 5.6);
    Complex y(3.5, 7.6);
    cout << (x + y) << endl;   // 类似于链式调用  //

//    关于结构体，重载运算符 + 作为全局函数
    CComplex xx, yy, zz;
    xx.real = 2.5; xx.image = 5.6;
    yy.real = 3.5; yy.image = 7.6;
    zz = xx + yy;
    cout << "[" << zz.real << ", " << zz.image << "] " << endl;

    return 0;
}
