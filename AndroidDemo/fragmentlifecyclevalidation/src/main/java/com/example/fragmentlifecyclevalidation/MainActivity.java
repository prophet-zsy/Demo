package com.example.fragmentlifecyclevalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

/**
 * 验证fragment的生命周期和activity生命周期的关系
 * 在logcat中搜索LifeCycle，即可将日志过滤出来
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycle: MainActivity";

//    【Android Activity的构造方法】https://blog.csdn.net/ccpat/article/details/54915200
    public MainActivity() {  // 默认无参构造方法不能为private，可自定义也可默认生成
        Log.d(TAG, "MainActivity: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "before onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "after onCreate: ");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "before onStart: ");
        super.onStart();
        Log.d(TAG, "after onStart: ");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "before onResume: ");
        super.onResume();
        Log.d(TAG, "after onResume: ");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "before onPause: ");
        super.onPause();
        Log.d(TAG, "after onPause: ");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "before onStop: ");
        super.onStop();
        Log.d(TAG, "after onStop: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "before onDestroy: ");
        super.onDestroy();
        Log.d(TAG, "after onDestroy: ");
    }
}