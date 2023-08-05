//
// Created by SM2887 on 2023/1/20.
//
#include "string"
#include "iostream"
#include "StringDemo.h"

using namespace std;

StringDemo::StringDemo() {
    int kk = 1;

    string str = string();
    str.append(to_string(kk++));
    str.append(to_string(kk++));
    str.append(to_string(kk++));
    str.append(to_string(kk++));
    cout << str << endl;


}
