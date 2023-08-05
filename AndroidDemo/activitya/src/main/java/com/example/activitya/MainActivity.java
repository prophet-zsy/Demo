package com.example.activitya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * 这里演示
 *    1、两个app之间的activity互相调用，activitya调用activityb
 *    2、不同启动模式下activity在任务栈中的情况
 *    3、本地app两个service之间的调用
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyService1.MyBinder myBinder1 = null;
    private MyService2.MyBinder myBinder2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToOtherAppActivity(View view) {
//        for (int i = 0; i < 10; i++) {
        startActivity(new Intent("com.example.activityb.MainActivity"));
//        }
        Log.i(TAG, "After startActivity");
    }

    public void bindService1(View view) {
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "bindService1    onServiceConnected: ");
                myBinder1 = (MyService1.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "bindService1    onServiceDisconnected: ");
            }
        };
        Intent intent = new Intent(this, MyService1.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void bindService2ByService1(View view) {
        myBinder1.bindService2();
    }

    public void getMyBinder2(View view) {
        myBinder2 = myBinder1.getMyBinder2();
    }

    public void greetByMyBinder2(View view) {
        String greet = myBinder2.greet();
        Log.d(TAG, "greetByMyBinder2: " + greet);
    }
}
