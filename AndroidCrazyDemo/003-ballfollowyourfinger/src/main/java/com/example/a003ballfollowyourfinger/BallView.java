package com.example.a003ballfollowyourfinger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/***
 * 跟手的小球
 */
//  todo 有机会弄弄清楚，父view和子view，onTouch、touchEvent、onClick几个函数的触发逻辑

public class BallView extends View {
    int X = 0;
    int Y = 0;
    Paint paint = new Paint();

    public BallView(Context context) {
        super(context);
        init();
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(X, Y, 50.0f, paint);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        rawX = (int) event.getX();
//        rawY = (int) event.getY();
//        invalidate();
//        return true;
//    }
    private void init() {
        setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("TEST", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("TEST", "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("TEST", "ACTION_MOVE");
                        break;
                }
                X = (int) motionEvent.getX();
                Y = (int) motionEvent.getY();
                invalidate();
//                return false;  // 这里返回false只有 ACTION_DOWN 时奏效
                return true;
            }
        });
    }
}
