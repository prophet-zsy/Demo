package com.example.a062_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 *  AsyncTask<Params, Progress, Result> 对使用线程执行异步任务做了封装，并添加了方便易用的附加功能，如onPreExecute、onPostExecute等
 *
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView text;
    Button download;
    ProgressBar progressBar;

    DownloadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        download = findViewById(R.id.download);
        progressBar = findViewById(R.id.progressBar);
        task = new DownloadTask();

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (! task.isrunning)
                        task.execute(new URL("http://www.baidu.com"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class DownloadTask extends AsyncTask<URL, Integer, String> {
        boolean isrunning = false;
        int progress = 0;

        @Override
        protected String doInBackground(URL... urls) {  // 子线程中要做的事情
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URLConnection connection = urls[0].openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    Thread.sleep(1000);
                    stringBuilder.append(line);
                    Log.d(TAG, line);
                    progress ++;
                    publishProgress(progress);  //  发布进度， 会触发 onProgressUpdate 在主线程中执行
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            text.setText("已经读取了【" + values[0] + "】行");
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPreExecute() {  // 执行前  （in 主线程）
            super.onPreExecute();
            progressBar.setMax(2);
            progressBar.setVisibility(View.VISIBLE);
            text.setText("下载开始");
            isrunning = true;
        }

        @Override
        protected void onPostExecute(String string) {  // 执行后  （in 主线程）
            super.onPostExecute(string);
            progressBar.setVisibility(View.GONE);
            text.setText("下载完成");
        }
    }
}
