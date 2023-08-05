package com.example.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View implements Runnable{

    private Paint paint;
    private Bitmap bitMap;
    private  int x;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    void  init() {
        bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.bit_map);
        bitMap = bitMap.createScaledBitmap(bitMap, 2000, 2000, true);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(50, 50, 50, paint);
//        canvas.drawRect(20, 30, 110, 170, paint);
//        canvas.drawBitmap(bitMap, 0, 0, null);
        moveBackground(canvas);
        super.onDraw(canvas);
    }

    private void moveBackground(Canvas canvas) {
        x -= 10;
        int x2 = getWidth() + x;
        canvas.drawBitmap(bitMap, x, 0, null);
        canvas.drawBitmap(bitMap, x2, 0, null);
        if (x2 <= 0) x = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate();
        }
    }
}
