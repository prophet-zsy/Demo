package com.example.a048_menudefinebyxml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  界面和 045、047 相同，只是把所有的menu在xml中定义了一遍
 *  menu的定义由xml完成，这样对menu有修改的需求时，直接修改xml文件即可，不需要动代码
 */

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        registerForContextMenu(textView);  // 注册上下文菜单
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("请选择背景色");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.font_10: textView.setTextSize(10 * 2); break;
            case R.id.font_12: textView.setTextSize(12 * 2); break;
            case R.id.font_14: textView.setTextSize(14 * 2); break;
            case R.id.font_16: textView.setTextSize(16 * 2); break;
            case R.id.font_18: textView.setTextSize(18 * 2); break;
            case R.id.font_red: textView.setTextColor(Color.RED); break;
            case R.id.font_green: textView.setTextColor(Color.GREEN); break;
            case R.id.font_blue: textView.setTextColor(Color.BLUE); break;
            case R.id.plain_item:
                Toast.makeText(this, "您点击了普通菜单项", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red: textView.setBackgroundColor(Color.RED); break;
            case R.id.green: textView.setBackgroundColor(Color.GREEN); break;
            case R.id.blue: textView.setBackgroundColor(Color.BLUE); break;
        }
        return super.onContextItemSelected(item);
    }
}
