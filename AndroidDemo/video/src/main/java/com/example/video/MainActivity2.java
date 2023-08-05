package com.example.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

/*
展示surfaceView的使用
 */

public class MainActivity2 extends AppCompatActivity {

    private MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mySurfaceView = new MySurfaceView(this);
//        setContentView(mySurfaceView);
        setContentView(R.layout.activity_main2);
        mySurfaceView = findViewById(R.id.surface_view);
    }
}
