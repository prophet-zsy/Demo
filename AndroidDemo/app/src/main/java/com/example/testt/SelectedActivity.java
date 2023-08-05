package com.example.testt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.nio.channels.InterruptedByTimeoutException;

public class SelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        BFragment Bfrag = (BFragment) getSupportFragmentManager().findFragmentById(R.id.BFragment);
        Bfrag.setFragTv(city);
    }
}
