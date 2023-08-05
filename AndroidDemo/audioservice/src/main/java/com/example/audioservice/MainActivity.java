package com.example.audioservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MusicService.class));
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, MusicService.class));
        super.onDestroy();
    }

    public void onButton(View view) {
        Intent intent = new Intent("audio_action");
        switch (view.getId()) {
            case R.id.start:
                intent.putExtra("cmd", CmdTools.START);
//                intent.putExtra("cmd",  R.id.start);   // 直接使用控件id来区分按钮也行
                sendBroadcast(intent);
                break;
            case R.id.pause:
                intent.putExtra("cmd", CmdTools.PAUSE);
                sendBroadcast(intent);
                break;
            case R.id.stop:
                intent.putExtra("cmd", CmdTools.STOP);
                sendBroadcast(intent);
                break;
        }
    }
}
