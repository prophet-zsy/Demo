package com.example.computeservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {


    private int serviceCompute(int ...scores) {
        int len = scores.length;
        if (len == 0) return 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += scores[i];
        }
        return sum;
    }

    public class MyBinder extends Binder {
        public int compute (int ...scores) {
//            int len = scores.length;
//            if (len == 0) return 0;
//            int sum = 0;
//            for (int i = 0; i < len; i++) {
//                sum += scores[i];
//            }
//            return sum;
            return serviceCompute(scores);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int res = intent.getIntExtra("Chinese", -1) + intent.getIntExtra("Math", -1);
        Log.i("MyService", String.valueOf(res));
        return super.onStartCommand(intent, flags, startId);
    }
}
