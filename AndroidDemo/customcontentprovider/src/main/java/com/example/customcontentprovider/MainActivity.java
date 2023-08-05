package com.example.customcontentprovider;

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

public class MainActivity extends AppCompatActivity {
    private Uri uri;
    private ContentResolver contentResolver;

    private EditText stuName;
    private EditText stuAge;
    private EditText stuScore;

    private List<Map<String, String>> show_list;
    private ListView listView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        test();

    }

    private void init() {
        uri = Uri.parse("content://com.example.customcontentprovider.MyProvider");
        contentResolver = getContentResolver();
        stuName = findViewById(R.id.stuName);
        stuAge = findViewById(R.id.stuAge);
        stuScore = findViewById(R.id.stuScore);

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

    private void test() {
        contentResolver.delete(uri,null, null);
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

    private void insert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", stuName.getText().toString());
        contentValues.put("age", stuAge.getText().toString());
        contentValues.put("score", stuScore.getText().toString());
        contentResolver.insert(uri, contentValues);
        query();
    }

    public void onButton(View view) {
        switch (view.getId()) {
            case R.id.addStu:
                insert();
                break;
        }
    }

}
