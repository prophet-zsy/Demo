#include <stdio.h>
#include "header_source_test/include/header_test.h"
#include "smart_ptr/xxx_ptr_test.h"


typedef enum {
    Mon = 1, Tues, Wed, Thurs, Fri, Sat, Sun
} Week;


int main() {
    Week week;
    week = Wed;
    printf("һ���еģ�%d\n", (int )week);
    int res = SubTest(45);
    printf("���ý����%d\n", res);

//    xxx_ptr_test();


//    c������ �ַ�������Ϊ ����Ľ�����
    char c [30];
//    gets(c);  // ֱ��ͨ������ַ����ķ�ʽ��ֵ�� �ַ����� ������ȫ
//    fgets(c, 30, stdin); //     fgets ���������������ַ����ĳ��ȣ��ǰ�ȫ��

    int aa; int bb;
//    scanf("%d asdfsad %d", &aa, &bb);  // ����ʱ ��Ҫ���ն�Ӧ��format���룬����������ȡ��������
    scanf("%d%d", &aa, &bb);  // ����ʱ format������%d����ʱ���м仹��Ҫ���� �ո��س� �����ֲ�ͬ�ı���
    printf("%d %d", aa, bb);



    return 0;
}
