package com.example.analogclockandtextclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;


public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometerId);
//        可以设置监听
//        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//
//            }
//        });
    }

    public void chronometerStart(View view) {
//        设置开始计时时间
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
}