package com.example.a028_progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int[] data = new int[101];
    private int dataId = 0;

    private ProgressBar bar;
    private ProgressBar bar2;

//    展示了Handler如何避免内存泄漏，定义静态内部类（便不会持有外部类的引用），
//    如需访问外部类的成员，可以在内部类初始化的时候传入外部类对象的引用，并以弱引用的方式存储
    static class MyHandler extends Handler {
        private WeakReference<MainActivity> activity;

        public MyHandler(WeakReference<MainActivity> activity) {
            this.activity = activity;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "handleMessage:  settingProgress" + activity.get().dataId);
            activity.get().bar.setProgress(activity.get().dataId);
            activity.get().bar2.setProgress(activity.get().dataId);
        }
    }
    MyHandler handler = new MyHandler(new WeakReference<>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bar);
        bar2 = findViewById(R.id.bar2);

        new Thread() {
            @Override
            public void run() {
                while (dataId < data.length) {
                    data[dataId++] = dataId;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();
    }
}
