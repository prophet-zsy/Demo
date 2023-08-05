package com.example.binderlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 极简的实现了下Binder
 *
 * MainActivity与SecondActivity分别运行在不同的进程，启动app，只有MainActivity的进程，点击跳转拉起SecondActivity对应的进程
 * 这样两个activity之间的通信则属于进程间通信，
 * binder的读写功能在native层实现，两个进程各维护一个binder，去读写相同文件（实际binder应该是，去读写建立内存映射的缓冲区，读的时候不需要拷贝）
 */



public class MainActivity extends AppCompatActivity {

    private MyBinder myBinder = new MyBinder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSelfPermission();
    }
    public void checkSelfPermission() {
        String[] permissions = new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> needToRequest = new ArrayList<>();
        for (String permission: permissions) {
            int res = this.getPackageManager().checkPermission(permission, this.getPackageName());
            if (res == PackageManager.PERMISSION_DENIED) needToRequest.add(permission);
        }
        if (!needToRequest.isEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(needToRequest.toArray(new String[needToRequest.size()]), 1);
            else Toast.makeText(this, "权限未能成功申请" + needToRequest.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void write(View view) {
        String content = "来自Main2Activity进程的消息，收到请回复！";
        Log.i("tag", String.valueOf(content.getBytes().length));
        myBinder.write(content);
        Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
    }
    public void jump(View view) {
        new Thread() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}
