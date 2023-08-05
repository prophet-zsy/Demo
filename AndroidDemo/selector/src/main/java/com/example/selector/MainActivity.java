package com.example.selector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/***
 *  验证selector的使用
 *  通过xml定义selector，可以在不同情况下展示组件的不同状态
 * 【selector中的排放顺序是严格程度从高到低】https://blog.csdn.net/qinqinde123/article/details/121482701
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}