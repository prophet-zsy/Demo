package com.example.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

/*
使用mediaPlayer和surfaceView配合使用来播放视频
 */

public class MainActivity3 extends AppCompatActivity {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        surfaceView = findViewById(R.id.surface_view);
        // MediaPlayer既可以播放音频，也可以播放视频，但是它没有可以展示画面的窗口，所以播放视频时需要和SurfaceView一同使用
        mediaPlayer = MediaPlayer.create(this, R.raw.sun_rise);
    }

    public void onButton(View view) {
        switch (view.getId()) {
            case R.id.start:
                if (mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(this, R.raw.sun_rise);
                mediaPlayer.setDisplay(surfaceView.getHolder());
                mediaPlayer.start();
                break;
            case R.id.pause:
                if (mediaPlayer != null && mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.stop:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                break;
        }
    }
}
