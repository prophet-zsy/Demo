package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NotificationManager mNotificationManager;
    private int NOTIFICATION_FLAG = 1;
    private int NOTIFICATION_FLAG2 = 2;
    private int NOTIFICATION_FLAG3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void notify (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        switch (view.getId()) {
            case R.id.notify1:
//                这个是早期版本，已经废弃
                Notification notification = new Notification();
                notification.icon = R.drawable.ic_launcher_foreground;
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notification.tickerText = "有新消息";
                mNotificationManager.notify(NOTIFICATION_FLAG, notification);
                break;
            case R.id.notify2:
                String channel = "channel_1";
                NotificationChannel channelbody = new NotificationChannel(channel,"消息推送",NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channelbody);
                notification = new Notification.Builder(this, channel)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("这是新消息标题")
                        .setContentText("这是新消息内容")
                        .setTicker("新消息Ticker")
                        .setContentIntent(pendingIntent)
                        .build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(NOTIFICATION_FLAG2, notification);
//                mNotificationManager.cancel(NOTIFICATION_FLAG2);  //  取消NOTIFICATION_FLAG2对应的通知
                break;
            case R.id.notify3:
                channel = "channel_2";
                channelbody = new NotificationChannel(channel,"消息推送",NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channelbody);
//                remoteViews
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.player_tab);
                remoteViews.setViewVisibility(R.id.downloadProgress, View.VISIBLE);
                notification = new Notification.Builder(this, channel)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setCustomContentView(remoteViews)
                        .build();
                for (int i = 0; i < 5; i++) {
                    mNotificationManager.notify(NOTIFICATION_FLAG3 + i, notification);
                }
//                mNotificationManager.cancel(NOTIFICATION_FLAG3);
                break;
        }
    }
}
