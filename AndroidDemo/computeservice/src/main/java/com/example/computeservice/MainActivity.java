package com.example.computeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 展示activity与service的交互，直接裸用Binder只能在同一进程之间通信
 *
 * 在不同进程之间通信
 * Binder驱动支持不同进程之间的通信，但需要写参数和返回值的序列化代码，或者使用aidl自动生成这部分代码
 *
 * 附： logcat可以查看不同进程中的日志
 */

public class MainActivity extends AppCompatActivity {

    private EditText ChineseScore;
    private EditText MathScore;
    private TextView res;

    private MyService.MyBinder iBinder = null;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChineseScore = findViewById(R.id.ChineseScore);
        MathScore = findViewById(R.id.MathScore);
        res = findViewById(R.id.res);

//        bindService(new Intent(this, MyService.class), conn, BIND_AUTO_CREATE);
    }

    public void compute(View view) {
//        直接在Activity中计算结果
//        res.setText(String.valueOf(Integer.valueOf(ChineseScore.getText().toString())  + Integer.valueOf(MathScore.getText().toString())));
        int ChineseSo = Integer.valueOf(ChineseScore.getText().toString());
        int MathSo = Integer.valueOf(MathScore.getText().toString());
        if (iBinder != null) {
            int resInt = iBinder.compute(ChineseSo, MathSo);
            res.setText(String.valueOf(resInt));
        }
    }

    public void computebyStartService(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("Chinese", Integer.valueOf(ChineseScore.getText().toString()));
        intent.putExtra("Math", Integer.valueOf(MathScore.getText().toString()));
        startService(intent);
    }
}
