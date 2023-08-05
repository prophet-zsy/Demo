package com.example.a023_autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 *  AutoCompleteTextView和MultiAutoCompleteTextView 两个组件自带前缀匹配
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AutoCompleteTextView auto;
    private MultiAutoCompleteTextView mauto;
    private List<String> books = new ArrayList<String>() {{
        add("疯狂Java讲义");
        add("疯狂前端开发讲义");
        add("疯狂XML讲义");
        add("疯狂Workflow讲义");
        add("Java讲义");
        add("前端开发讲义");
        add("XML讲义");
        add("Workflow讲义");
    }};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto = findViewById(R.id.auto);
        mauto = findViewById(R.id.mauto);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                books
        );
        auto.setAdapter(adapter);
        mauto.setAdapter(adapter);
        mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());  // 设置分隔符
    }

//    【AutoCompleteTextView支持数据更新】https://blog.csdn.net/iteye_6288/article/details/81820979
//    todo 传入AutoCompleteTextView组件的adapter绑定的数据并不支持数据更新，因此需要自己定义过滤规则和adapter
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.autoButton:
                books.add(auto.getText().toString());
                break;
            case R.id.mautoButton:
                books.add(mauto.getText().toString());
                break;
        }
        Log.i(TAG, books.toString());
        adapter.notifyDataSetChanged();
    }
}
