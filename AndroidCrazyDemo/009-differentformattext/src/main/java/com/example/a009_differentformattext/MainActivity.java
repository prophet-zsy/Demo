package com.example.a009_differentformattext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

public class MainActivity extends AppCompatActivity {

    private CheckedTextView checkedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkedTextView = findViewById(R.id.CheckedTextView);
    }

    public void changeCheckState(View view) {
        checkedTextView.setChecked(!checkedTextView.isChecked());
    }
}
