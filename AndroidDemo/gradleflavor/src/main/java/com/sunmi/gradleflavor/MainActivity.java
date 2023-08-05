package com.sunmi.gradleflavor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


/**
 * 【Android Gradle flavor —— 打造不同风味的app】https://juejin.cn/post/7031399811173744648
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pay(View view) {
//        new PayHelper().pay(); // 通过switch分支控制 选择执行不同风味的代码
        new PayHelper2().pay();  // 通过将风味代码 写在 app/src/风味名/ 路径下来 区分 不同 风味 便于选择
    }
}