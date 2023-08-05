package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import static android.provider.MediaStore.getDocumentUri;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton(View view) {
        switch (view.getId()) {
            case R.id.start:
                Log.i("TEST", "start");
                if (mediaPlayer == null) ready();
                mediaPlayer.start();
                break;
            case R.id.pause:
                Log.i("TEST", "pause");
                if (mediaPlayer != null && mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.stop:
                Log.i("TEST", "stop");
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                break;
        }
    }

    private void ready() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.run_away);
//            或者
//            由于API29将外部存储进行了分区管理，这里需要进行适配新的API，才能访问到存储在storage中的音频文件
//            mediaPlayer = new MediaPlayer();
//            Log.i("TEST", String.valueOf(Environment.getExternalStorageDirectory()));
//            Uri uri = Uri.fromFile(new File(Environment.getStorageDirectory(), "run_away.mp3"));
//            try {
//                mediaPlayer.setDataSource(this, uri);
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
