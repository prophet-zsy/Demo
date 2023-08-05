package com.example.customcontentprovider2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
在这里访问customcontentprovider中存入的信息，其通过contentProvider公开数据
本app通过ContentResolver访问其它app公开的数据

数据库中的表对内容提供者SQLiteOpenHelper和ContentProvider都是可访问的
但表对于ContentResolver来说是不可访问的，它可见的只是ContentProvider提供的增删改查的接口
 */

public class MainActivity extends AppCompatActivity {
    private Uri uri;

    private ContentResolver contentResolver;

    private List<Map<String, String>> show_list;
    private ListView listView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        query();

    }

    private void init() {
        uri = Uri.parse("content://com.example.customcontentprovider.MyProvider");
        contentResolver = getContentResolver();

        show_list = new ArrayList<>();
        listView = findViewById(R.id.list_view);

        simpleAdapter = new SimpleAdapter(
                this,
                show_list,
                R.layout.student_item,
                new String[]{"name", "age", "score"},
                new int[]{R.id.tvName, R.id.tvAge, R.id.tvScore}
        );
        listView.setAdapter(simpleAdapter);
    }

    private void query() {
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        show_list.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int score = cursor.getInt(cursor.getColumnIndex("score"));

            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("age", String.valueOf(age));
            map.put("score", String.valueOf(score));
            show_list.add(map);
        }
        simpleAdapter.notifyDataSetChanged();
    }


}
