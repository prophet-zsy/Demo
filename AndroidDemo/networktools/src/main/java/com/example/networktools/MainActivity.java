package com.example.networktools;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private MyReceiver myReceiver;
    private ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        image_view = findViewById(R.id.image_view);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }



    public void testNetWorkState(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        通过ConnectivityManager来获取所有网络的状态
//        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
//        if (networkInfos == null) return;
//        StringBuffer stringBuffer = new StringBuffer();
//        for (NetworkInfo networkInfo : networkInfos) {
//            stringBuffer.append(networkInfo.toString());
//            stringBuffer.append("\n\n");
//        }
//        textView.setText(stringBuffer.toString());
//        通过ConnectivityManager来获取活跃网络的状态
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        textView.setText(activeNetworkInfo.getTypeName() + ", " + activeNetworkInfo.isConnected());
    }

    public void netWorkSetting(View view) {
        Tools.checkNetWorkState(this);
        if (! Tools.NETWORK_STATE) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String channel = "channel_1";
            NotificationChannel channelbody = new NotificationChannel(channel,"消息推送",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channelbody);
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, 0);
            Notification notification = new Notification.Builder(this, channel)
                                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                                            .setTicker("网络连接")
                                            .setContentIntent(pendingIntent)
                                            .build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notification);
        }
    }

    public void imageDownLoad(View view) throws InterruptedException, ExecutionException, TimeoutException {
        String path = "https://tse1-mm.cn.bing.net/th/id/OET.60fa16a6115a419db8181566855b1bbe?w=135&h=272&c=7&rs=1&o=5&dpr=1.25&pid=1.9";
        MyTask myTask = new MyTask();
        myTask.execute(path);
        Bitmap bitmap = myTask.get(10, TimeUnit.SECONDS);
        image_view.setImageBitmap(bitmap);
    }

    class MyTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String path = strings[0];
            try  {
                URL url = new URL(path);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setDoInput(true);
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    Log.i("TEST", "RESPONSE OK");
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
