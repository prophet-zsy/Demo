package com.example.aidllearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 展示binder和aidl的使用，对比了两者的区别
 *
 * 当client和service在同一进程中，调用bindService时，在连接成功后返回的是service提供服务能力的Binder本身
 * 当client和service在不同进程中，调用bindService时，在连接成功后返回的是Binder驱动层创建的代理BinderProxy
 * 无论是直接使用binder，还是通过aidl生成的代码，都符合上述描述
 *
 * aidl提供的能力则是，如果在不同进程中，使用aidl定义RPC接口，自动生成的代码将参数和返回值的读写工作代理了，外界调用就像在本地调用一样
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, service.getClass().getName());  // 反射获取类名
//            Log.i(TAG, ((MyService.MyBinder) service).getService().toString());  // todo 不使用aidl，同一进程可以，不同进程需要将IMyAidlInterface.Stub和IMyAidlInterface.Stub.Proxy中读写参数返回值的逻辑补上
            try {
//                asInterface方法传入binder，返回的是给客户端调用的proxy       调用1
                Log.i(TAG, IMyAidlInterface.Stub.asInterface(service).getService().toString());  // todo 使用aidl，是否同一进程均可
                new Thread(){  // 线程1调用aidl接口    调用2
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Log.i(TAG, IMyAidlInterface.Stub.asInterface(service).getService().toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                new Thread() {  // 线程2调用aidl接口    调用3
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Log.i(TAG, IMyAidlInterface.Stub.asInterface(service).getService().toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onServiceConnected: " + service.pingBinder());  // todo pingBinder方法检测binder是否存活
            try {
//                todo 客户端调用linkToDeath方法设置死亡代理，当对应的binder死掉时，会回调对应的binderDied方法
                service.linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.d(TAG, "binderDied: ");
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, MyService.class), conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
