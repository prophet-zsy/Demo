package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 广播相当于全局消息，只要注册接收器就能接收，发送广播可以通过intent携带消息
 * 广播功能只要获得上下文Context就能使用
 *
 * 接收广播的一方BroadcastReceiver需要注册在AndroidManifest.xml清单文件中
 * 而发送广播的一方不需要注册在AndroidManifest.xml清单文件中
 *
 * 广播可以用作不同组件、不同app、不同进程之间的通信，但携带消息的体量上限受intent限制
 * 广播广播广而播之，适合公开传播的消息
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button button;
    private Button button2;
    private Button button3;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "sendBroadcast");
                Intent intent = new Intent("asdf");
//                静态注册的广播需要这句话，因为静态注册的广播在app启动之前也是可以收到的，所以应该在app未启动的情况下告知应该做出什么反应的代码
                intent.setComponent(new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.MyReceiver"));
                sendBroadcast(intent);  //  显式广播，指定了发给哪个app
            }
        });

        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter("asdf2"));

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("asdf2");
//                动态注册的广播可以不需要这句话，静态广播一定需要这句话
//                有下面这句话（指定收到广播后应该执行的代码），则为显式广播（意图明显），无下面这句话，则为隐式广播
//                intent.setComponent(new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.MyReceiver"));
                sendBroadcast(intent);
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        测试其他进程中的Receiver对应的onReceive方法运行在哪里
                startService(new Intent(MainActivity.this, MyService.class));
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sendBroadcast(new Intent("testBroadcastOtherProcess"));
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
