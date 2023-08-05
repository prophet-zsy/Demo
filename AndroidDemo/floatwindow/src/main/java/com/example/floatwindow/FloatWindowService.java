package com.example.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.concurrent.ExecutorService;

/**
 * 【Android悬浮窗看这篇就够了】https://blog.csdn.net/qq_37750825/article/details/115754647
 * 记得申请权限，并同意权限
 * 在Activity中创建悬浮窗也可以，但是Activity返回销毁后，悬浮穿就不依赖于四大组件了，容易被回收，因此将其包裹在Service中
 * 悬浮窗中的所有工作可以在这个Service中做
 */

public class FloatWindowService extends Service {

    private View view;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;
    private boolean isWindowShown;

    @Override
    public void onCreate() {
        super.onCreate();
        ((App)getApplication()).floatWindowService = this;  // 新启动，在App中登记下
        prepareWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeWindow();
        ((App)getApplication()).floatWindowService = null;  // 销毁，在App中登记下
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        showWindow();
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void prepareWindow() {
        view = LayoutInflater.from(this).inflate(R.layout.float_window, null, false);
        view.setOnTouchListener(new WindowMoveListener());  // 父view的onTouch重写不影响子view响应事件，因为父view不拦截事件的情况下，子view优先级更高

        layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;  // 窗口类型
        layoutParams.format = PixelFormat.RGBA_8888;  // 图像格式  （或TRANSPARENT也行）
//      FLAG_NOT_FOCUSABLE 不允许获得焦点 (且 不接收返回键)  FLAG_NOT_TOUCH_MODAL 不接收窗口范围之外的事件（发送给后面的窗口处理）
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        isWindowShown = false;
    }
    private void showWindow() {  // 可以上把锁
        if (! isWindowShown) {
            windowManager.addView(view, layoutParams);
            isWindowShown = true;
        }
    }
    private void closeWindow() {  // 可以上把锁
        if (isWindowShown) {
            windowManager.removeView(view);
            isWindowShown = false;
        }
    }

    class WindowMoveListener implements View.OnTouchListener {
        private int lastX;  // 可以用int算，也可以直接用float，一个快点但粗略点，一个精准点但慢点
        private int lastY;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) motionEvent.getRawX();  // getRawX 直接获取相对于屏幕的位置，而不是相对于窗口
                    lastY = (int) motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
    //                        计算与上次位置的差值
                    int moveX = rawX - lastX;
                    int moveY = rawY - lastY;
                    lastX = rawX; lastY = rawY;
    //                        更新窗口布局
                    layoutParams.x += moveX;
                    layoutParams.y += moveY;
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
            }
            return false;
        }
    }
}
