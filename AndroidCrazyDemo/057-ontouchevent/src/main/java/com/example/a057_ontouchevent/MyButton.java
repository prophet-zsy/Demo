package com.example.a057_ontouchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = "MyButton";
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        String type = "";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            type = "ACTION_DOWN";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            type = "ACTION_UP";
        }
        Log.d(TAG, "onTouchEvent" + type);
        return false;
//        返回true 代表消费该事件，不会再向外传递
//        返回false 代表不消费该事件，继续向外传递
    }
}
