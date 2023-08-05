package com.example.loadimageoom;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(3);
        int imageCnt = 10;
        for (int i = 0; i < imageCnt; i++) {
            ImageView imageView = new ImageView(this);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);   // drawable 中 太大的图片加载不动
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
//            imageView.setImageBitmap(bitmap);
            imageView.setImageResource(R.drawable.img);  // 直接调用setImageResource方法进行加载同一张图片 是ok的，相比于自己解析bitmap 可能有优化
            gridLayout.addView(imageView);
        }
    }
}
