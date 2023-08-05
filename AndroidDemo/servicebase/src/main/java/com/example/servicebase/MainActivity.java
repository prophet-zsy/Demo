package com.example.servicebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            连接成功时，调用
            Log.i("Activity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            连接失败时，调用
            Log.i("Activity", "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick (View view) {
        Intent intent = new Intent(this, MyService.class);
        switch (view.getId()) {
            case R.id.startService:
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case  R.id.BindService:
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.UnbindService:
                unbindService(conn);
                break;
        }
    }

}
