package com.example.parentmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


/**
 * 在父module中跳转到子module的页面，子module中定义的资源命名需要添加前缀与父module中进行区分，否则，命名相同时默认载入父module中的资源
 * （【组件化路上之二 Android 资源重名问题解决】https://www.jianshu.com/p/8e53fd422886）
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, com.example.childmodule1.MainActivity.class));
    }
}