package com.example.broadcastreceiver;

import android.app.Application;
import android.util.Log;


/**
 * 静态注册的Receiver收到显式广播时，
 * 如果Receiver所在的进程还未启动，
 * 则先拉起对应的进程，执行Application类的初始化，然后再初始化对应的组件
 */

public class APP extends Application {
    private static final String TAG = "APP";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
}
