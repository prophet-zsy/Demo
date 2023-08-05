package com.example.a057_ontouchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


/**
 * 书中3.3 基于回调的事件处理， 事件源 和 事件监听器是 统一的
 *  当用户在GUI组件上激发某个事件时，组件自己特定的方法会负责处理该事件
 *
 *
 *  实验：
 *  在界面上分别点击按钮和activity对应的其他地方，
 *  点击按钮后如果 down事件向外传递，activity也会收到该事件，但该序列后续的 up事件不会再被Button消费
 *  点击按钮后如果 down事件不向外传递，activity并不会收到该事件
 *  点击activity上非按钮的位置，只有activity能收到对应的事件
 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String type = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            type = "ACTION_DOWN";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            type = "ACTION_UP";
        }
        Log.d(TAG, "onTouchEvent" + type);
        return super.onTouchEvent(event);
    }
}
