package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 *    retrofit是对OkHttp的封装，遵循Restful设计风格
 *    以下是使用样例
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fanyi.youdao.com/")
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getCall("");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Reader reader = response.body().charStream();
                char[] buf = new char[1024];
                int len = -1;
                try {
                    while ((len = reader.read(buf)) > 0) {
                        Log.i("TEST onResponse", new String(buf, 0, len));
                    }
                    buf = null;
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("TEST onFailure", t.toString());
            }
        });

    }

    public interface API {
        @GET("/")
        Call<ResponseBody> getCall(@Query("name") String name);
    }
}
