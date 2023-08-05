package com.example.activitya;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


/**
 * 在MyService1中启动MyService2，
 * 然后转发MyService2返回的Binder
 */

public class MyService1 extends Service {

    private static final String TAG = "MyService1";

    private MyService2.MyBinder myBinder2 = null;

    public MyService1() {
    }

    private void bindService2() {
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "bindService2    onServiceConnected: ");
                myBinder2 = (MyService2.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "bindService2    onServiceDisconnected: ");
            }
        };
        Intent intent = new Intent(this, MyService2.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder {
        private static final String TAG = "MyBinder";
        public void bindService2() {
            MyService1.this.bindService2();
        }

        public MyService2.MyBinder getMyBinder2() {
            Log.d(MyService1.TAG + TAG, "getMyBinder2: ");
            return myBinder2;
        }
    }
}
