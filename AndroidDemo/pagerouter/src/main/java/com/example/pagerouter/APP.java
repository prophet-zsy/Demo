package com.example.pagerouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 开启log，要放到前面才能看到初始化过程的log
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        // 初始化
        ARouter.init(this);
    }
}
