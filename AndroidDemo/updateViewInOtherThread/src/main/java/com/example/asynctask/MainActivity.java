package com.example.asynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * 子线程中更新UI的三种方案
 */

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        handler = Handler.createAsync(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                tv.setText(String.valueOf(msg.what));
                return false;
            }
        });
        startThread();
//        方案1 使用 AsyncTask 来起子线程
//        new MyTask().execute();
    }

    public void startThread() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    方案2 给UI线程对应的handler发消息
//                    Message message = new Message();
//                    message.what = i;
//                    handler.sendMessage(message);
//                    方案3 直接使用对应view的postInvalidate方法刷新view
                    tv.postInvalidate();  // 只能刷新，不能在子线程中设置
                }
            }
        }.start();
    }

    class MyTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 20; i++) {
                publishProgress(i);  // 自动调用 onProgressUpdate
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tv.setText(String.valueOf(values[0]));
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i("TEST", "子线程运行结束");
            super.onPostExecute(aVoid);
        }
    }
}


