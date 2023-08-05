//
// Created by SM2887 on 2023/1/19.
//

#include "stdio.h"


int main() {

    printf("Hello World !\n");


    int a;
    char c;
    scanf("%d", &a);
    getchar();  // 获取回车，以区分两个内容的边界 （但只能获取一个分隔符，有多个分隔符需要特殊处理）
    scanf("%c", &c);
    printf("%d %c", a, c);

    return 0;
}



