package com.example.a047_contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 上下文菜单 与 选项菜单 基本相似
 * 上下文菜单 主要锚定在某一个具体的View上，长按时 会跳出上下文菜单
 */

public class MainActivity extends AppCompatActivity {

    private static final int RED = 0x001;
    private static final int GREEN = 0x002;
    private static final int BLUE = 0x003;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        // todo 将上下文菜单注册到textView上， 意味着用户长按该组件时，会出现上下文菜单
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("选择背景颜色");
        menu.add(0, RED, 0, "红色");
        menu.add(0, GREEN, 0, "绿色");
        menu.add(0, BLUE, 0, "蓝色");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case RED:
                textView.setBackgroundColor(Color.RED);
                break;
            case GREEN:
                textView.setBackgroundColor(Color.GREEN);
                break;
            case BLUE:
                textView.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
