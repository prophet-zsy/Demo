package com.example.buttonselector;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;


/***
 *  ImageButton 通过selector设置按压 变背景 未验证成功
 *  现通过设置onTouchListener来变换背景，但不是最优解
 *  todo  验证 selector 如何改变 ImageButton 背景
 */

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton image = findViewById(R.id.image);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    image.setImageResource(R.drawable.ic_baseline_play_circle_filled_24_gray);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    image.setImageResource(R.drawable.ic_baseline_play_circle_filled_24_lightgray);
                return false;
            }
        });
    }
}