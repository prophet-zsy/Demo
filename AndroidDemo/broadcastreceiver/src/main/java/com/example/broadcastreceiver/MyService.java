package com.example.broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * 独立进程的Service动态注册broadcast
 * 对应的onReceive方法运行在service所在进程中
 */

public class MyService extends Service {

    private static final String TAG = "MyService";
    private MyReceiver myReceiver;
    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("testBroadcastOtherProcess");
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
