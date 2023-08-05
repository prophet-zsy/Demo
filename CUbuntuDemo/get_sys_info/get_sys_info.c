//
// Created by SM2887 on 2022/11/12.
//

#include "get_sys_info.h"

#include <stdio.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
void sampleLoadAvg(){
    int f=0;
    char buffer[80]="";                         /* 定义字符串并初始化为'\0' */
    char buf[5][10];
    char *file="/proc/loadavg";
    f = open(file, O_RDONLY);
    if (f == 0)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    read(f, (void *)buffer, 80);
    sscanf(buffer, "%s %s %s %s %s",            /* sscanf()拆分成多个字符串 */
           &buf[0],&buf[1],&buf[2],&buf[3],&buf[4]);
    printf("一分钟平均负载：%s\n", buf[0]);
    printf("五分钟平均负载：%s\n", buf[1]);
    printf("一刻钟平均负载：%s\n", buf[2]);
    printf("采样时刻的间隔：%s\n", buf[3]);
    printf("最大线程的数目：%s\n", buf[4]);
    close(f);
}
void sampleTime(){
    int f=0;
    char buffer[80]="";
    char buf[2][10];
    float seconds=0;
    float secondr=0;
    char *file="/proc/uptime";
    f = open(file, O_RDONLY);
    if (f == 0)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    read(f, (void *)buffer, 80);
    sscanf(buffer, "%s %s", &buf[0], &buf[1]);
    close(f);
    printf("系统运行时间：\t%s秒\n", buf[0]);
    printf("系统空闲时间：\t%s秒\n", buf[1]);
    close(f);
    seconds=strtof(buf[0],NULL);
    secondr=strtof(buf[1],NULL);
    printf("系统运行时间：\t%03d天%02d时%02d分%02.6f秒\n",
           (int)seconds/(24*3600),             /* 天 */
           ((int)seconds/(3600))%(24),         /* 时 */
           ((int)seconds/60)%60,               /* 分 */
           ((int)seconds%60)+(seconds-(int)seconds)); /* 秒(精确至毫秒) */
    printf("系统空闲时间：\t%03d天%02d时%02d分%02.6f秒\n",
           (int)secondr/(24*3600),             /* 天 */
           ((int)secondr/(3600))%(24),         /* 时 */
           ((int)secondr/60)%60,               /* 分 */
           ((int)secondr%60)+(secondr-(int)secondr)); /* 秒(精确至毫秒) */
}

void sampleKernelVersion(){
    int f=0;
    char buffer[80]="";
    char *file="/proc/sys/kernel/version";
    f = open(file, O_RDONLY);
    if (f == 0)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    read(f, (void *)buffer, 80);
    buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
    printf("当前内核版本：\t%s\n", buffer);
    close(f);
}

void sampleOsRelease(){
    int f=0;
    char buffer[80]="";
    char *file="/proc/sys/kernel/osrelease";
    f = open(file, O_RDONLY);
    if (f == 0)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    read(f, (void *)buffer, 80);
    buffer[strlen(buffer)-1]=0;
    printf("当前发行版本：\t%s\n", buffer);
    close(f);
}

void sampleOsType(){
    int f=0;
    char buffer[80]="";
    char *file="/proc/sys/kernel/ostype";
    f = open(file, O_RDONLY);
    if (f == 0)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    read(f, (void *)buffer, 80);
    buffer[strlen(buffer)-1]=0;
    printf("当前操作系统：\t%s\n", buffer);
    close(f);
}

void sampleDiskStat(){
    int i;
    FILE *fp;
    int nread=0;
    ssize_t len = 0;
    char *buffer=NULL;
    char buf[20][32];
    char *file="/proc/diskstats";
    char *p;
    fp = fopen(file, "rb");
    if (fp == NULL)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
    printf("  磁盘  读次数  写次数\n");
//    while((nread = getline(&buffer, &len, fp)) != -1) { /* 简单实现读行的功能 */
//        sscanf(buffer, "%04s%08s%s %s %s %s %s %s %s %s %s %s %s %s",
//               &buf[0],&buf[1],&buf[2],&buf[3],&buf[4],&buf[5],&buf[6],
//               &buf[7],&buf[8],&buf[9],&buf[10],&buf[11],&buf[12],&buf[13]);
//        if ((p=strstr(buf[2], "loop"))!=NULL)
//        {
//            ;                                   /* loop本地回路不作操作 */
//        }
//        else {
//            printf("%06s%08s%08s\n",
//                   &buf[2],&buf[3], &buf[7]);
//        }
//    }
}

void sampleProcesses(void)
{
    FILE *fp;
    int nread=0;
    ssize_t len = 0;
    char *buf=NULL;
    char *buffer=NULL;
    char *file="/proc/stat";
    fp = fopen(file, "rb");
    if (fp == NULL)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
//    while((nread = getline(&buffer, &len, fp)) != -1) {
//        if((buf=strstr(buffer, "processes"))!=NULL)  /* 简单实现grep的功能 */
//            break;
//    }
    buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
    char count[16]="";
    sscanf(buffer, "%s%s", count, count);
    printf("执行线程数目:\t%s\n", count);
}

void sampleContext(void)
{
    FILE *fp;
    int nread=0;
    ssize_t len = 0;
    char *buf=NULL;
    char *buffer=NULL;
    char *file="/proc/stat";
    fp = fopen(file, "rb");
    if (fp == NULL)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
//    while((nread = getline(&buffer, &len, fp)) != -1) {
//        if((buf=strstr(buffer, "ctxt"))!=NULL)  /* 简单实现grep的功能 */
//            break;
//    }
    buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
    char count[16]="";
    sscanf(buffer, "%s%s", count, count);
    printf("上下文切换次数:\t%s\n", count);
}

void sampleMeminfo()
{
    FILE *fp;
    int nread=0;
    ssize_t len = 0;
    char *buf=NULL;
    char *buffer=NULL;
    char *file="/proc/meminfo";
    char content[16]="";
    fp = fopen(file, "rb");
    if (fp == NULL)
    {
        printf("error to open: %s\n", file);
        exit(EXIT_FAILURE);
    }
//    while((nread = getline(&buffer, &len, fp)) != -1) {
//        if((buf=strstr(buffer, "MemTotal"))!=NULL)  /* 简单实现grep的功能 */
//        {
//            buffer[strlen(buffer)-1]=0;             /* 简单实现tr()函数的功能 */
//            sscanf(buffer, "%s%s", content, content);
//            int memtotal_kb=(int)(strtof(content, NULL));
//            printf("内存总容量:\t%dG%4dM %4dK\n",
//                   memtotal_kb/(1024*1024),           /* Gb */
//                   (memtotal_kb/(1024))%1024,         /* Mb */
//                   (memtotal_kb%(1024*1024))%1024);   /* Kb */
//        }
//        if((buf=strstr(buffer, "MemFree"))!=NULL)  /* 简单实现grep的功能 */
//        {
//            buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
//            sscanf(buffer, "%s%s", content, content);
//            int memfree_kb=(int)(strtof(content, NULL));
//            printf("内存可用容量:\t%dG%4dM %4dK\n",
//                   memfree_kb/(1024*1024),           /* Gb */
//                   (memfree_kb/(1024))%1024,         /* Mb */
//                   (memfree_kb%(1024*1024))%1024);   /* Kb */
//        }
//        if((buf=strstr(buffer, "SwapTotal"))!=NULL)  /* 简单实现grep的功能 */
//        {
//            buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
//            sscanf(buffer, "%s%s", content, content);
//            int swaptotal_kb=(int)(strtof(content, NULL));
//            printf("SWAP总容量:\t%dG%4dM %4dK\n",
//                   swaptotal_kb/(1024*1024),           /* Gb */
//                   (swaptotal_kb/(1024))%1024,         /* Mb */
//                   (swaptotal_kb%(1024*1024))%1024);   /* Kb */
//        }
//        if((buf=strstr(buffer, "SwapFree"))!=NULL)  /* 简单实现grep的功能 */
//        {
//            buffer[strlen(buffer)-1]=0;                 /* 简单实现tr()函数的功能 */
//            sscanf(buffer, "%s%s", content, content);
//            int swapfree_kb=(int)(strtof(content, NULL));
//            printf("SWAP可用容量:\t%dG%4dM %4dK\n",
//                   swapfree_kb/(1024*1024),           /* Gb */
//                   (swapfree_kb/(1024))%1024,         /* Mb */
//                   (swapfree_kb%(1024*1024))%1024);   /* Kb */
//            break;                              /* 所需的信息已满足，退出循环 */
//        }
//    }
}
/*
 * ===  FUNCTION  ======================================================================
 *         Name:  main
 *  Description:
 * =====================================================================================
 */
int main ( int argc, char *argv[] )
{
    printf(">>>系统负载<<<\n");
    sampleLoadAvg();
    printf("----------------------------------------\n");
    printf("\n");
    printf(">>>运行时间<<<\n");
    sampleTime();
    printf("\n");
    printf(">>>版本信息<<<\n");
    sampleOsType();
    sampleOsRelease();
    sampleKernelVersion();
    printf("----------------------------------------\n");
    printf("\n");
    printf(">>>磁盘信息<<<\n");
    sampleDiskStat();
    printf("----------------------------------------\n");
    printf("\n");
    printf(">>>上下文切换<<<\n");
    sampleContext();
    printf("----------------------------------------\n");
    printf("\n");
    printf(">>>线程数目<<<\n");
    sampleProcesses();
    printf("----------------------------------------\n");
    printf("\n");
    printf(">>>内存信息<<<\n");
    sampleMeminfo();
    printf("----------------------------------------\n");
    return EXIT_SUCCESS;
}

