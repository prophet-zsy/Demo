package com.example.activityservicecommunicatebybroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    class MyReceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int result = intent.getIntExtra("result", -1);
            res.setText(String.valueOf(result));
        }
    }
    private EditText ChineseScore;
    private EditText MathScore;
    private TextView res;
    private MyReceiver1 myReceiver1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChineseScore = findViewById(R.id.ChineseScore);
        MathScore = findViewById(R.id.MathScore);
        res = findViewById(R.id.res);
        myReceiver1 = new MyReceiver1();
        IntentFilter intentFilter = new IntentFilter("result");
        registerReceiver(myReceiver1, intentFilter);
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver1);
        stopService(new Intent(this, MyService.class));
    }

    public void compute (View view) {
        Intent intent = new Intent("sum");
        intent.putExtra("ChineseScore", Integer.valueOf(ChineseScore.getText().toString()));
        intent.putExtra("MathScore", Integer.valueOf(MathScore.getText().toString()));
        sendBroadcast(intent);
        Log.i(TAG, "发送动作名为sum的广播");
    }
}
