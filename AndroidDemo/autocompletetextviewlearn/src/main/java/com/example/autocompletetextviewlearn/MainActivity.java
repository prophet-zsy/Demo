package com.example.autocompletetextviewlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button submit;
    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextViewAdapter adapter;
    private Set<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.submit);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        data = new HashSet<String>() {{
            add("thanks");
            add("give");
        }};
        adapter = new AutoCompleteTextViewAdapter(this, data);
        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setDropDownVerticalOffset(500); // 设置提示框的偏移量，使用工具将dp转换为像素
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add(autoCompleteTextView.getText().toString());
                adapter.notifyAllData(data);
                Log.d(TAG, "onClick: " + data);
            }
        });
    }
}


