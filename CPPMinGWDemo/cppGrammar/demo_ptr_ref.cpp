//
// Created by SM2887 on 2023/1/21.
//

#include <memory>
#include "iostream"

using namespace std;


int main() {

    int a = 0;
    int * ra = &a;                  //   分配一个变量，把a的地址赋值给他
    int ** rra = &ra;               // 再分配一个变量，把ra的地址的赋值给他
    int *** rrra = &rra;            // 再分配一个变量，把rra的地址的赋值给他
    int **** rrrra = &rrra;         // 再分配一个变量，把rrra的地址的赋值给他
    int ***** rrrrra = &rrrra;      // 再分配一个变量，把rrrra的地址的赋值给他
    int ****** rrrrrra = &rrrrra;   // 再分配一个变量，把rrrrra的地址的赋值给他
    int ******* rrrrrrra = &rrrrrra;// 再分配一个变量，把rrrrrra的地址的赋值给他
    cout << a << endl;
    cout << ra << endl;
    cout << rra << endl;
    cout << rrra << endl;
    cout << rrrra << endl;
    cout << rrrrra << endl;
    cout << rrrrrra << endl;
    cout << rrrrrrra << endl;





//  智能指针
    shared_ptr<string> ptr1 = make_shared<string>(10, '9');
    shared_ptr<string> ptr2 = make_shared<string>("hello");
    shared_ptr<string> ptr3 = make_shared<string>();

    cout << ptr1->c_str() << endl;
    cout << ptr2->c_str() << endl;
    cout << ptr3->c_str() << endl;


    return 0;
}
