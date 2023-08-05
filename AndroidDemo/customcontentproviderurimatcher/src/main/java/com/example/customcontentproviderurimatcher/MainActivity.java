package com.example.customcontentproviderurimatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

public class MainActivity extends AppCompatActivity {
    private ContentResolver contentResolver;

    private Spinner spinner;
    private TextView hint;
    private EditText stuName;

    private List<Map<String, String>> show_list;
    private ListView listView;
    private SimpleAdapter simpleAdapter;

    private Uri stuUri = Uri.parse("content://MyProvider2/student");
    private Uri teaUri  = Uri.parse("content://MyProvider2/teacher");
    private Uri uriChose = stuUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        contentResolver = getContentResolver();
        spinner = findViewById(R.id.spinner);
        hint = findViewById(R.id.hint);

        stuName = findViewById(R.id.stuName);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = spinner.getSelectedItem().toString();
                if (selectedName.equals("Student")) {
                    hint.setText("请输入学生姓名：");
                    uriChose = stuUri;
                    query();
                } else {
                    hint.setText("请输入老师姓名：");
                    uriChose = teaUri;
                    query();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        show_list = new ArrayList<>();
        listView = findViewById(R.id.list_view);

        simpleAdapter = new SimpleAdapter(
                this,
                show_list,
                R.layout.student_item,
                new String[]{"name"},
                new int[]{R.id.tvName}
        );
        listView.setAdapter(simpleAdapter);
    }


    private void query() {
        Cursor cursor = contentResolver.query(uriChose, null, null, null, null);
        show_list.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));

            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            show_list.add(map);
        }
        simpleAdapter.notifyDataSetChanged();
    }

    private void insert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", stuName.getText().toString());
        contentResolver.insert(uriChose, contentValues);
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
