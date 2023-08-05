#include <stdio.h>
#include "header_source_test/include/header_test.h"
#include "smart_ptr/xxx_ptr_test.h"


typedef enum {
    Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun
} Week;


int main() {
    Week week;
    week = Wed;
    printf("一周中的：%d\n", (int )week);
    int res = SubTest(45);
    printf("调用结果：%d\n", res);

//    xxx_ptr_test();


//    c语言中 字符数组作为 输入的接收者
    char c [30];
//    gets(c);  // 直接通过输出字符串的方式赋值给 字符数组 并不安全
//    fgets(c, 30, stdin); //     fgets 函数限制了输入字符串的长度，是安全的

    int aa; int bb;
//    scanf("%d asdfsad %d", &aa, &bb);  // 输入时 需要按照对应的format输入，才能正常获取输入内容
    scanf("%d%d", &aa, &bb);  // 输入时 format中两个%d相邻时，中间还是要加入 空格或回车 来区分不同的变量
    printf("%d %d", aa, bb);



    return 0;
}
