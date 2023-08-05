package com.example.datapath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 【Android 各种路径详细说明】
 * https://blog.csdn.net/gangjindianzi/article/details/79810222
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        todo  内部存储空间不需要申请权限
        Log.i("TEST", getFilesDir().getPath());                  //  app内部文件存储
        Log.i("TEST", getCacheDir().getPath());                  //  app内部缓存文件存储
        Log.i("TEST", getExternalCacheDir().getPath());          //  内置sd卡中  app内部文件存储
        Log.i("TEST", getExternalFilesDir("").getPath());  //  内置sd卡中  app内部缓存文件存储

        touchFile(getFilesDir(), "filesTest");                          // 对应路径    /data/data/com.example.datapath/cache/cacheTest
        touchFile(getCacheDir(), "cacheTest");                          // 对应路径    /data/data/com.example.datapath/files/filesTest
        touchFile(getExternalCacheDir(), "externalCacheTest");          // 对应路径    /storage/emulated/0/Android/data/com.example.datapath/cache/externalCacheTest
        touchFile(getExternalFilesDir(""), "externalFilesTest");  // 对应路径    /storage/emulated/0/Android/data/com.example.datapath/files/externalFilesTest

        Log.i("TEST", Environment.getDataDirectory().getPath());
        Log.i("TEST", Environment.getRootDirectory().getPath());
        Log.i("TEST", Environment.getDownloadCacheDirectory().getPath());
        Log.i("TEST", Environment.getStorageDirectory().getPath());  // 需要api30

//        todo API29上面，下面这些路径，好像申请了权限也不可以写
//        touchFile(Environment.getDataDirectory(), "EnvironmentGetDataTest");
//        touchFile(Environment.getRootDirectory(), "EnvironmentGetRootTest");
//        touchFile(Environment.getDownloadCacheDirectory(), "EnvironmentGetDownloadCacheTest");
//        touchFile(Environment.getStorageDirectory(), "EnvironmentGetStorageTest");
    }

    //        创建文件
    private void touchFile(File parent, String FileName) {
        File file = new File(parent, FileName);
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write("asdfasdfasdf".getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
