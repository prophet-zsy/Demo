package com.example.rsserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {

    private static final String TAG = "OtherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohter2);
    }

    public void bindLocalService(View view) {
        Log.i(TAG, "bindRemoteService");
//        Intent intent = new Intent(this, MyRemoteService.class);
        Intent intent = new Intent();
//        intent.setAction("com.cyx.server.service.bind");//Service的action
//        intent.setPackage("com.example.rsserver");//App A的包名
        intent.setComponent(new ComponentName("com.example.rsserver", "com.example.rsserver.MyRemoteService") );
        boolean res = bindService(intent, mServerServiceConnection, BIND_AUTO_CREATE);
        if (res) Toast.makeText(this, "绑定成功", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "绑定失败", Toast.LENGTH_LONG).show();
    }

    ServiceConnection mServerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG, "MyRemoteService onServiceConnected");
            // 通过aidl取出数据
            IProcessInfo processInfo = IProcessInfo.Stub.asInterface(service);
            try {
                Log.i(TAG, "MyRemoteService process id = " + processInfo.getProcessId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "MyRemoteService onServiceDisconnected");
        }
    };

}