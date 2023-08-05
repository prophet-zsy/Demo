package com.example.a042_activitylikedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * 通过在AndroidManifest.xml文件中对activity配置主题 android:theme="@style/Theme.AppCompat.Dialog" 后
 *     它本质上是个完整窗口界面，但表现为对话框风格
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void close(View view) {
        finish();
    }
}
