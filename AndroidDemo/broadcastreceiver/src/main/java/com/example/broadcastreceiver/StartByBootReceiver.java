package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * 开机自启功能的实现
 *
 * 静态注册的广播接收器  接收开机广播
 * 实现开机自启， （只要广播可以接收到，就可以拉起自己的进程）
 * 但在API29及以上，不允许在用户没有交互的情况下擅自加载Activity，因此API29及以上不允许在Service及广播接收器的onReceive方法中直接startActivity
 *      对于以上情况，可以启动一个前台Service或Notification，用户主动点击后再加载对应的Activity
 * 【Android 接收开机广播启动service/activity】https://blog.csdn.net/qq_43540406/article/details/113413244
 *
 * 开机自启的层面：
 *   在API29以下，是可以通过广播拉起进程并主动加载Activity的
 *   API29及以上只能拉起进程，加载界面需要用户主动操作
 *   因此API29及以上，监听开机广播只能后台拉起进程，进入对应APP界面需要用户点击
 */

public class StartByBootReceiver extends BroadcastReceiver {

    private static final String TAG = "StartByBootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String processName = getCurrentProcessName();  // 打印onReceive执行的进程名字
        Log.i(TAG, "Toast  " + processName);
        Toast.makeText(context, "收到开机广播了 : " + processName, Toast.LENGTH_SHORT).show();

//        跳转到MainActivity中（仅支持API28及以下）
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // https://blog.csdn.net/u012548528/article/details/23032289
        context.startActivity(intent1);
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
