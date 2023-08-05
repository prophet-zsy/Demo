package com.example.a054_controlplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * 控制飞机移动
 */

public class MainActivity extends AppCompatActivity {

    PlaneView planeView;
    int speed = 10; // 飞机的移动速度
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        planeView = new PlaneView(this);
        setContentView(planeView);
//        planeView = findViewById(R.id.plane_view);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        planeView.curX = (metrics.widthPixels / 2.0f);
        planeView.curY = (metrics.heightPixels - 1000);
        planeView.setOnTouchListener(new View.OnTouchListener() {
//                 * 点击屏幕上边缘 向上移动
//                 * 点击屏幕下边缘 向下移动
//                 * 点击屏幕左边缘 向左移动
//                 * 点击屏幕右边缘 向右移动
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getX() < metrics.widthPixels / 8) {
                    planeView.curX -= speed;
                }
                if (motionEvent.getX() > metrics.widthPixels * 7 / 8) {
                    planeView.curX += speed;
                }
                if (motionEvent.getY() < metrics.heightPixels / 8) {
                    planeView.curY -= speed;
                }
                if (motionEvent.getY() > metrics.heightPixels * 7 / 8) {
                    planeView.curY += speed;
                }
                planeView.invalidate();
                return true;
            }
        });
    }
}
