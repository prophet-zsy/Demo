package com.example.componentlearnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 不同组件之间可以单独编译，加快编译速度
 * 不同组件之间之间的界面跳转
 * 可以使用隐式intent的方式，也可以单独开发一个组件，实现页面路由的功能
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        activity中启动的线程，在activity退出后并不会回收，除非其所在进程被终止，因此如果线程在需求上跟activity是绑定的话，应在onDestroy方法中进行销毁
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int cnt = 0;
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.i("test", String.valueOf(cnt ++));
//                }
//            }
//        }).start();
    }

    public void startActivityInThisComponent(View view) {
        startActivity(new Intent("com.example.componentlearnapp.Main2Activity"));
    }

    public void startActivityInOtherComponent(View view) {
        startActivity(new Intent("com.example.componentlearn.ComponentActivity"));
    }
}
