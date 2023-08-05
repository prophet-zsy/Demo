package com.example.updownload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitDownloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_download);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://prophet-zsy.cc:8060/MusicInfoManageSystem/")
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        DownloadMusicNetProxy.downloadMusic(api, getFilesDir().getPath());
    }

    public interface API {
        @GET("download-servlet")
        public Observable<ResponseBody> getMusicData(@Query("musicName") String musicName);
    }
}

class DownloadMusicNetProxy {
    public static void downloadMusic(RetrofitDownloadActivity.API api, final String storePath) {
        final Observable<ResponseBody> observable = api.getMusicData("chengdu.mp3");
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(final ObservableEmitter<Object> e) throws Exception {
                observable  // 因为要写文件，所以不切换主线程
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            InputStream inputStream = responseBody.byteStream();
                            OutputStream outputStream = new FileOutputStream(new File(storePath, "chengdu.mp3"));
                            byte[] buf = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(buf)) > 0) {
                                outputStream.write(buf, 0, len);
//                                    Log.i("TEST", "downloading..." + len);
                            }
                            inputStream.close();
                            outputStream.close();
                            e.onComplete();
                        }
                    });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TEST", "download complete...");
                    }
                });
    }
}
