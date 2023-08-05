package com.example.componentlearnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 组件之间的页面跳转
 * 【Android进阶学习之组件化/MVC/MVP/MVVM】
 * https://www.bilibili.com/video/BV1LK411c75R?p=2
 */

public class ComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
    }
}
