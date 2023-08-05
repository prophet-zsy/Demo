package com.example.a056_listenerbylambda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 */

//  todo androidStudio 对 java8 以及lambda表达式的支持 有待验证

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "您点击了Hello World", Toast.LENGTH_SHORT).show();
//            }
//        });

        textView.setOnClickListener(View -> Toast.makeText(this, "您点击了Hello World", Toast.LENGTH_SHORT).show());
    }
}
