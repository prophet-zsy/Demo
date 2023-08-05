package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 *   OkHttp是一个http客户端，常用的网络请求框架
 *   遵循http协议
 *   以下是使用示例
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://fanyi.youdao.com/")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TEST onFailure", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("TEST onResponse", response.body().string());  // 这里使用response.body().string()获取返回结果，并不完全
                Reader reader = response.body().charStream();
                char[] buf = new char[1024];
                int len = -1;
                while ((len = reader.read(buf)) > 0) {
                    Log.i("TEST onResponse", new String(buf, 0, len));
                }
                buf = null;
                reader.close();
            }
        });
    }
}
