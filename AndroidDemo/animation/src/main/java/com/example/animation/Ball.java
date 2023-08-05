package com.example.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class Ball extends View {

    private Paint paint;
    private Float cx;
    private Float cy;
    private Float radius;

    private void  init() {
        paint = new Paint();
        radius = 10.0f;
        cx = 20.0f;
        cy = 20.0f;
    }

    public Ball(Context context) {
        super(context);
        init();
    }

    public Ball(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    public boolean setLocation(Point point) {
        cx = point.getPointX();
        cy = point.getPointY();
        return true;
    }

    // 描述小球运动轨迹坐标
    public static class Point {
        private float pointX;
        private float pointY;

        public Point(float pointX, float pointY) {
            this.pointX = pointX;
            this.pointY = pointY;
        }

        public float getPointX() {
            return pointX;
        }

        public float getPointY() {
            return pointY;
        }
    }
}
