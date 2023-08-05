//
// Created by SM2887 on 2022/12/30.
//

#include <iostream>
#include <thread>  // c++ 11 标准的线程库
#include <unistd.h>

using namespace std;

#ifndef  DEMO_THREAD
#define DEMO_THREAD


//子线程的初始函数
void myprint()
{
    long start = clock();
    cout << " myprint(): MyThread start 我的线程开始执行" << endl;  // todo 子线程输出的中文内容是乱码，研究下
    sleep(1);               // unistd.h 库中的
    _sleep(1000);  // stdlib.h 库中的
    cout << " myprint(): Mythread stop 我的线程执行完毕" << endl;
    long end = clock();
    cout << "used time : " << (end - start) * 1000 / CLOCKS_PER_SEC << " ms" << endl;
}

//主线程在从main开始执行，一旦主线程从main()返回，则整个程序结束
int testThread()
{
//    c++ 11 创建子线程的调用方法
    thread mytobj(myprint);	//创建了线程，执行起点是myprint，同时让子线程开始执行
    mytobj.join();			//主线程阻塞在这里，等子线程执行完
    cout << "testThread() finished ! \n";





    return 0;
}
#endif

