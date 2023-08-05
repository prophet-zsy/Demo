//
// Created by SM2887 on 2022/10/27.
//

#include "xxx_ptr_test.h"
#include "memory.h"

// todo 研究下如何混编c和c++
int xxx_ptr_test() {
    auto_ptr< string > str(new string("我要成为大牛~ 变得很牛逼！"));
    return 0;
}


