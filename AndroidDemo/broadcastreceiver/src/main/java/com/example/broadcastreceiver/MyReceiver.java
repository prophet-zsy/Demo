package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * 静态注册的广播接收器（动作名为asdf，查看AndroidManifest.xml）
 * 动态注册的广播接收器（动作名为asdf2，查看.MainActivity）
 */

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String processName = getCurrentProcessName();  // 打印onReceive执行的进程名字
        Log.i(TAG, "Toast " + processName);
        Toast.makeText(context, "收到广播了 : " + processName, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回当前的进程名
     */
    public static String getCurrentProcessName() {
        Object processName = null;

        try {
            //1. 通过ActivityThread中的currentActivityThread()方法得到ActivityThread的实例对象
            Class<?> activityThreadClass = null;

            activityThreadClass = Class.forName("android.app.ActivityThread");
            Method method = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object activityThread = method.invoke(null);

            //2. 通过activityThread的getProcessName() 方法获取进程名
            Method getProcessNameMethod = activityThreadClass.getDeclaredMethod("getProcessName");
            processName = getProcessNameMethod.invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processName == null ? "null" : processName.toString();
    }
}
