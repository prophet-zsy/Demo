package com.example.a046_menuandactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 可以直接给菜单项MenuItem直接setIntent，这样点击对应菜单项之后会进行跳转
 * 也可以重写onOptionsItemSelected方法，匹配对应的菜单项，设置对应的跳转逻辑
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("启动项");
        subMenu.setHeaderTitle("请选择您要启动的项目");
        MenuItem item = subMenu.add("查看Swift");
        item.setIntent(new Intent(this, OtherActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
