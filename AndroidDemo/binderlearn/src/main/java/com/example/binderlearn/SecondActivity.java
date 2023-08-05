package com.example.binderlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private MyBinder myBinder = new MyBinder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void read(View view) {
        String read = myBinder.read();
        Toast.makeText(this, read, Toast.LENGTH_SHORT).show();
    }
}
