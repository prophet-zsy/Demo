package com.example.customtitle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import java.util.concurrent.CyclicBarrier;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {

    private Paint mPaint;

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth()/2;
        int cy = getHeight()/2;
        int rad = cx < cy ? cx-10 : cy - 10;
        canvas.drawCircle(cx, cy, rad, mPaint);
        this.setText("");
        this.setBackgroundColor(Color.TRANSPARENT);
    }

    void setPaintColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPaintColor(Color.RED);
                return true;
            case MotionEvent.ACTION_UP:
                setPaintColor(Color.BLUE);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
