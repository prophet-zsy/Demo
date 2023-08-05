package com.example.broadcastsend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * 发送广播给其他app(com.example.broadcastreceiver)中的MyReceiver
 * 如果广播接收器在其他进程中，显式广播可以拉起其他进程
 * 隐式广播则不行
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//   todo 给静态注册的接收器发送隐式广播
//   todo 高版本中，静态注册的接收器，已经接收不到隐式广播了（除了某些特殊的系统广播，开机广播等），推荐使用动态注册
    public void sendBroadCast1(View view) {//        未指明发给谁，是隐式广播
        Intent intent = new Intent("asdf");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
//   todo 给静态注册的接收器发送显式广播
//   不需要接收方运行，因为显式广播指明了发给谁，直接让对应接收器处理就行（对应接收器所在进程如果没起来，则会拉起它）
    public void sendBroadCast2(View view) {//        指明发给谁，是显式广播
        Intent intent = new Intent("asdf");  // 显示广播因为已经指明了目的地，所以不用动作名也行
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//      note： 这里的包名要跟接收方的AndroidManifest.xml文件下 指定的包名一致 package="com.example.broadcastreceiver"
        intent.setComponent(new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.MyReceiver"));
        sendBroadcast(intent);
    }
//   todo 给动态注册的接收器发送隐式广播
//   需要接收方在运行中，而且已经注册过对应的接收器了；否则，接收不到
    public void sendBroadCast3(View view) {//        未指明发给谁，是隐式广播
        Intent intent2 = new Intent("asdf2");
        intent2.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent2);
    }
//   todo 给动态注册的接收器发送显式广播
//   不需要接收方运行，因为显式广播指明了发给谁，直接让对应接收器处理就行（对应接收器所在进程如果没起来，则会拉起它）
    public void sendBroadCast4(View view) {//        指明发给谁，是显式广播
        Intent intent2 = new Intent("asdf2");  // 显示广播因为已经指明了目的地，所以不用动作名也行
        intent2.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent2.setComponent(new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.MyReceiver"));
        sendBroadcast(intent2);
    }
}
