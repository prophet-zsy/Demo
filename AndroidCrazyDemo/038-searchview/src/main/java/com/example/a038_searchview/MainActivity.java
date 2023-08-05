package com.example.a038_searchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;

    private String[] content = new String[] {
            "aaaaaaa",
            "bbbbbbbb",
            "ccccccc",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content));
        listView.setTextFilterEnabled(true);  // 启动listView的文本过滤功能 // todo <listView 默认有文本过滤功能哎~>

        searchView.setIconifiedByDefault(false);  // 设置点击时 searchView图标是否默认自动缩小
        searchView.setSubmitButtonEnabled(true);  // 设置searchView 显示提交按钮
        searchView.setQueryHint("查找");           // 设置searchView内 默认显示的提示文本
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {  // 提交时，触发
                Toast.makeText(MainActivity.this, "您要查询的是" + s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {  // 文本改变时，触发
                if (s.isEmpty()) {
                    listView.clearTextFilter();
                } else {
                    listView.setFilterText(s);
                }
                return true;
            }
        });
    }
}
