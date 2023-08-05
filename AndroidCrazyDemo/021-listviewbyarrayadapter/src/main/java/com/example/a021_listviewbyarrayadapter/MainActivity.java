package com.example.a021_listviewbyarrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView list1;
    private ListView list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = findViewById(R.id.list1);
        list2 = findViewById(R.id.list2);

        String[] arr1 = new String[]{"孙悟空", "猪八戒", "牛魔王",};
        String[] arr2 = new String[]{"Java", "Hibernate", "Spring", "Android"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr2);

        list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);
    }
}
