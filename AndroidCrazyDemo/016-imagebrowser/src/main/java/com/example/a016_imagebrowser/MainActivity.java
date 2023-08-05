package com.example.a016_imagebrowser;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int showId = 0;
    private int[] imgs = new int[] {
            R.drawable.img111,
            R.drawable.img222,
            R.drawable.img333,
    };
    private float alpha = 1.0f;
    private float step = 0.2f;
    private Button increaseAlpha;
    private Button decreaseAlpha;
    private Button nextImg;
    private ImageView imgShow;
    private ImageView imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    public void initView() {
        increaseAlpha = findViewById(R.id.increaseAlpha);
        decreaseAlpha = findViewById(R.id.decreaseAlpha);
        nextImg = findViewById(R.id.nextImg);
        imgShow = findViewById(R.id.imgShow);
        imgDetail = findViewById(R.id.imgDetail);
    }

    public void registerListener() {
        increaseAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alpha = Math.min(1.0f, alpha + step);
                imgShow.setAlpha(alpha);
            }
        });
        decreaseAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alpha = Math.max(0.0f, alpha - step);
                imgShow.setAlpha(alpha);
            }
        });
        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgShow.setImageResource(imgs[(showId++)%imgs.length]);
            }
        });
        imgShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();  // 获取相对于当前View左上角的坐标
                int y = (int) motionEvent.getY();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgShow.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                double scale = 1.0 * bitmap.getHeight() / imgShow.getHeight();  // 原图片大小 / ImageView的大小
                int xOnBitmap = (int) (x * scale);
                int yOnBitmap = (int) (y * scale);
                int edge = 120;  // imgDetail 的边长为120dp
                xOnBitmap = Math.min(bitmap.getWidth() - edge, xOnBitmap);
                yOnBitmap = Math.min(bitmap.getHeight() - edge, yOnBitmap);
                imgDetail.setImageBitmap(Bitmap.createBitmap(bitmap, xOnBitmap, yOnBitmap, 120, 120));
                return true;  // 消费掉该Touch事件，可以实现随着ACTION_MOVE动作实时显示细节
            }
        });
    }
}
