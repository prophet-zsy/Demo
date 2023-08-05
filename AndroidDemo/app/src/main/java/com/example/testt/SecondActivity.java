package com.example.testt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "recyclerSecondActivity";
    private Button returnMain;
    private Button dialog;
    private Button transActivity3;
    private TextView tv;
    private Button count;
    private int cnt;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        final int num1 = intent.getIntExtra("num1", -1);
        final int num2 = intent.getIntExtra("num2", -1);

        returnMain = findViewById(R.id.returnMain);
        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("result", num1+ num2);
                setResult(0x002, intent);
                finish();
            }
        });

        dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SecondActivity.this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("对话框")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .setNeutralButton("忽略", null)
                        .setCancelable(false)
                        .show();
            }
        });
        transActivity3 = findViewById(R.id.transActivity3);
        transActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(SecondActivity.this, ThirdActivity.class);
                startActivity(intent1);
            }
        });
        count = findViewById(R.id.count);
        tv = findViewById(R.id.tv2);
        flag = false;
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                if (count.getText() == "启动"){
                    count.setText("暂停");
                } else {
                    count.setText("启动");
                }
            }
        });
        cnt = 0;
        final Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(String.valueOf(cnt++));
                            }
                        });
                    }
                }
            }
        }.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        flag = false;
        count.setText("启动");
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
