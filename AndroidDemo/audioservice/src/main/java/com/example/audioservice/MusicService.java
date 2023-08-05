package com.example.audioservice;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    private MyReceiver myReceiver;

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.run_away);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter("audio_action"));
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void start() {
        if (mediaPlayer == null) mediaPlayer = MediaPlayer.create(this, R.raw.run_away);
        mediaPlayer.start();
    }

    private void pause() {
        mediaPlayer.pause();
    }

    private void stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int cmd = intent.getIntExtra("cmd", -1);
            switch (cmd) {
                case CmdTools.START:
//                case R.id.start:  // 直接使用控件id来区分按钮也行
                    start();
                    break;
                case CmdTools.PAUSE:
                    pause();
                    break;
                case CmdTools.STOP:
                    stop();
                    break;
            }
        }
    }
}
