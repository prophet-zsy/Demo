package com.example.a051_menuonactionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);  // 显示应用程序图标  // todo 未验证成功
        actionBar.setDisplayHomeAsUpEnabled(true);  // 显示ActionBar左侧的返回箭头
    }

//    要重写onCreateOptionsMenu方法，解析定义好的xml文件 和 menu关联起来
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //  android.R.id.home 默认为ActionBar左侧箭头的id
                Intent intent = new Intent(this, FirstActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   //  清空目标Activity上面的Activity
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
