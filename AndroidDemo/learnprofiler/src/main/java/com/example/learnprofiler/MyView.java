package com.example.learnprofiler;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View implements Runnable {

    private int x;
    private int y;
    private int xx;
    private int yy;
    private Paint paint;

    public MyView(Context context) {
        super(context);
        xx = 50;
        yy = 50;
        x = 0;
        y = 0;
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        xx = (int) typedArray.getDimension(R.styleable.MyView_xx, -1);
        yy = (int) typedArray.getDimension(R.styleable.MyView_yy, -1);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 100; i++) {
            paint = new Paint();
        }
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        x += 50;
        y += 50;

//        canvas.drawCircle(x, y, 50, paint);

        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 50, y + 50);
        canvas.drawPath(path, paint);

        if (x >= xx) x = 0;
        if (y >= yy) y = 0;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate();
        }
    }
}
