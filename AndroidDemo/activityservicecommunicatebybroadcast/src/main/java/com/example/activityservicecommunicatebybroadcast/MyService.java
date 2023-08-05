package com.example.activityservicecommunicatebybroadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String TAG = "MyService";
    class MyReceiver2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int a = intent.getIntExtra("ChineseScore", -1);
            int b = intent.getIntExtra("MathScore", -1);
            Intent intent1 = new Intent("result");
            intent1.putExtra("result", sum(a, b));
            sendBroadcast(intent1);
            Log.i(TAG, "发送动作名为result的广播");

        }
    }
    private MyReceiver2 myReceiver2;

    public MyService() {
    }

    private int sum (int a, int b) {
        return a + b;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myReceiver2 = new MyReceiver2();
        IntentFilter intentFilter = new IntentFilter("sum");
        registerReceiver(myReceiver2, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(myReceiver2);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
