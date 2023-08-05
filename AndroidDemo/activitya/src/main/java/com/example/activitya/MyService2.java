package com.example.activitya;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService2 extends Service {
    public MyService2() {
    }

    private String greet() {
        return "来自 MyService2 的问候，MyService2已经连接成功";
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder {
        public String greet() {
            return MyService2.this.greet();
        }
    }
}
