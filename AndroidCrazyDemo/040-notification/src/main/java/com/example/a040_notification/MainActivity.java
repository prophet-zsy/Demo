package com.example.a040_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

/**
 *  Notification 因为是在System_server进程中，使用RemoteViews，不方便自己设置
 *  所以内置了一些 不同的 布局风格
 */

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private final int NOTIFICATION_ID = 0x123;
    private final String CHANNEL_ID = "crazyit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  // 获取Notification服务
        String name = "测试channel";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("测试channel的描述信息");
        channel.enableLights(true);        //  可能是 设置通知出现时 有呼吸灯（还是闪光灯，不确定）
        channel.setLightColor(Color.RED);  //  可能是 设置呼吸灯的颜色
        channel.enableVibration(true);     // 设置震动
        channel.setVibrationPattern(new long[] {0, 50, 100, 150}); // 设置震动模式
//        channel.setSound(Uri.parse("android.resource://org.crazy.ui/" + R.raw.msg), null); // 设置自定义声音
        notificationManager.createNotificationChannel(channel);  // 创建通道
    }
    public void sendNotification(View view) {
        Intent intent = new Intent(MainActivity.this, OtherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Person person = new Person.Builder()
                .setName("孙悟空")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .build();
        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle(person); // 设置通知参与者
        messagingStyle.setConversationTitle("一条新通知");  // 设置消息标题
//        创建一条消息
        Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message("恭喜您，加薪了，工资增加20%", System.currentTimeMillis(), person);
//        message.setData("image/jpeg", Uri.parse("file:///mnt/sdcard/list.png"));  // 设置额外的数据
        messagingStyle.addMessage(message);   // 添加一条消息
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(messagingStyle)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
    public void cancelNotification(View view) {
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
