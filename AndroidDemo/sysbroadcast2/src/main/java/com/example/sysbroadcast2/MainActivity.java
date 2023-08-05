package com.example.sysbroadcast2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "发送短信成功", Toast.LENGTH_SHORT).show();
        }
    }

    private MyReceiver myReceiver;
    private EditText PhoneNum;
    private EditText MSContent;

    public void sendMS (View view) {
        Intent intent = new Intent("test.receive");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(PhoneNum.getText().toString(), "", MSContent.getText().toString(), pendingIntent, null);
        Log.i("sendMS", "发送消息成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter("test.receive");
        registerReceiver(myReceiver, intentFilter);
        PhoneNum = findViewById(R.id.PhoneNum);
        MSContent = findViewById(R.id.MSContent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
