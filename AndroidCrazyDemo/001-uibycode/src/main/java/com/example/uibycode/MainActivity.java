package com.example.uibycode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final TextView textView = new TextView(this);
        textView.setText("点击按钮，显示时间");
        Button button = new Button(this);
        button.setText("单击我");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(new Date().toString());
            }
        });
        linearLayout.addView(textView);
        linearLayout.addView(button);
        setContentView(linearLayout);
    }
}
