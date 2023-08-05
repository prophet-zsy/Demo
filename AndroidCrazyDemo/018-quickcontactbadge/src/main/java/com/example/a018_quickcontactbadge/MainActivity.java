package com.example.a018_quickcontactbadge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.QuickContactBadge;

public class MainActivity extends AppCompatActivity {

    private QuickContactBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        badge = findViewById(R.id.badge);
        badge.assignContactFromPhone("020-888888888", false);
//        该行代码会 将QuickContactBadge组件与电话号码为020-888888888的联系人建立关联，当被点击时，系统会打开该联系人对应的联系方式界面
    }
}
