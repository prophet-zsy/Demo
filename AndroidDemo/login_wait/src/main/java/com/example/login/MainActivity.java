package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

/**
 * 这里结合Tomcat和servlet后台一起
 * 后台提供登录服务，前台提供登录界面
 */

public class MainActivity extends AppCompatActivity {
    private static final String PATH = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
