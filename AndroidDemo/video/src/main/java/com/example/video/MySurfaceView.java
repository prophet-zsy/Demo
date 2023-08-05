package com.example.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Paint paint;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void draw () {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null) {
            canvas.drawCircle(100, 100, 50, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                Log.i("TEST", "surfaceCreated");
                draw();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                Log.i("TEST", "surfaceChanged");
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                Log.i("TEST", "surfaceDestroyed");
            }
        });
    }
}
