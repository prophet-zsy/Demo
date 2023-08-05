package com.example.rsserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 打印日志时用来过滤，快速找到调试信息
    private static final String TAG = "chanchaw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联到 res/layout/activity_main.xml，会显示其中定义的控件
        setContentView(R.layout.activity_main);
    }

    // 创建 ServiceConnection 类型的对象实例，在后面绑定服务时会用到
    ServiceConnection myServiceConnection = new ServiceConnection() {

        /**
         * 服务绑定成功后会调用本方法
         * @param name
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG, "MyRemoteService onServiceConnected");
            // 通过aidl取出数据
            IProcessInfo processInfo = IProcessInfo.Stub.asInterface(service);
            try {
                Log.i(TAG, "MyRemoteService process id = " + processInfo.getProcessId());
                Toast.makeText(MainActivity.this, "绑定成功，进程号为:" + processInfo.getProcessId(), Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "MyRemoteService onServiceDisconnected");
        }
    };

    public void bindLocalService(View v) {
        Intent intent = new Intent(this, MyRemoteService.class);
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE);

    }

    public void jumpOtherActivityInProcess(View view) {
//        Intent intent = new Intent(this, OtherActivity.class);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.rsserver", "com.example.rsserver.OtherActivity"));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myServiceConnection);
    }
}