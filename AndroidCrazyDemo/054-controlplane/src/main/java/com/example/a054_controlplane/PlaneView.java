package com.example.a054_controlplane;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class PlaneView extends View {
    private static final String TAG = "PlaneView";

    float curX;
    float curY;

    Context context;
    Paint paint;
    Bitmap plane0;
    Bitmap plane1;
    int idx = 0;

    public PlaneView(Context context) {
        super(context);
        init(context);
    }

    public PlaneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        plane0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane0);
        plane1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane1);
        plane0 = rotate(plane0, 180);
        plane1 = rotate(plane1, 180);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                idx = (idx + 1) % 2;
                PlaneView.this.invalidate();
            }
        }, 0, 1000);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i(TAG, String.valueOf(idx));
//        Log.i(TAG, String.valueOf(idx == 0 ? plane0 : plane1));
//        Log.i(TAG, curX + "," + curY);
        canvas.drawBitmap(idx == 0 ? plane0 : plane1, curX, curY, paint);
    }

    private Bitmap rotate(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
