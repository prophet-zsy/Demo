package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView函数中只是将xml形式的布局文件解析出来，但并未执行测量、布局、绘制三个流程
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.line1);
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        LinearLayout linearLayout = findViewById(R.id.line1);
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));
    }


    /*
    view树是在调用完onResume函数之后才开始被绘制
    view树的测量、布局、绘制是在resume阶段完成的，onResume函数之后
     */
    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout linearLayout = findViewById(R.id.line1);
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));
        linearLayout.addView(new Button(this));
    }
}
