//
// Created by SM2887 on 2022/12/30.
//

//  头文件 一般包含定义和声明，头文件就是不断的其他文件中的代码导入使用，所以需要自带去重的逻辑
#ifndef CPPMINGWDEMO_DEMO_THREAD_H   //   防止多个文件同时导入该头文件，造成的重复定义的错误；防止 CMakeLists.txt中 将该.h文件添加进 add_executable()中，也会造成重复定义的错误
#define CPPMINGWDEMO_DEMO_THREAD_H

void myprint();
int testThread();




#endif //CPPMINGWDEMO_DEMO_THREAD_H
