package com.example.a063_launcheractivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 *  LauncherActivity继承自ListActivity，list中每个item都对应一个intent
 *  用户单击不同的item时，应用程序会自动启动对应的Activity
 */

public class MainActivity extends LauncherActivity {

    String[] name = new String[] {
            "设置程序参数", "查看星际兵种"
    };
    Class[] classes = new Class[] {
            SecondActivity.class, ThirdActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        setListAdapter(adapter);
    }

//    重写 intentForPosition方法 指定对应的跳转对象
    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, classes[position]);
    }
}
