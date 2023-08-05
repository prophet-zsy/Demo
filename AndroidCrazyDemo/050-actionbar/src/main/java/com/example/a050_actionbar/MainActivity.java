package com.example.a050_actionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();  // 只有主题中设置使用了ActionBar，这里才能成功获取ActionBar，否则返回null
    }

    public void showActionBar(View view) {
        actionBar.show();
    }
    public void hideActionBar(View view) {
        actionBar.hide();
    }
}
