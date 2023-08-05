package com.example.a005_neonlights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int[] colors = new int[] {
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
    };
    int[] viewIds = new int[] {
            R.id.text1,
            R.id.text2,
            R.id.text3,
            R.id.text4,
            R.id.text5,
            R.id.text6
    };
    View[] views = new View[viewIds.length];
    int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < viewIds.length; i++) {
            views[i] = findViewById(viewIds[i]);
        }
        final Handler handler = Handler.createAsync(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                for (int i = 0; i < views.length; i++) {
//                    对比下三种不同的设置方法
//                    views[i].setBackground();  + Drawable
//                    views[i].setBackgroundColor(MainActivity.this.getResources().getColor(colors[(offset + i) % views.length]));
                    views[i].setBackgroundResource(colors[(offset + i) % views.length]);
                }
                offset = (offset + 1) % views.length;
                return false;
            }
        });
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(new Message());
                }
            }
        }.start();
    }
}
