package com.example.leakcanarylearn;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 *  [全解系列：内存泄漏定位工具LeakCanary！]https://cloud.tencent.com/developer/article/1699620
 */

public class LeakApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 判断当前进程是否是 LeakCanary 专门用于分析heap内存的而创建的那个进程，即HeapAnalyzerService所在的进程，如果是的话，则不进行Application中的初始化功能
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            return;
        }
        LeakCanary.install(this);
    }
}
