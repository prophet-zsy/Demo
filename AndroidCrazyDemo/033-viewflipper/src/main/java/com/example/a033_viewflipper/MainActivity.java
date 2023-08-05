package com.example.a033_viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.view_flipper);
    }

    public void prev(View view) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showPrevious();
    }
    public void auto(View view) {
        if (((Button)view).getText().toString().equals("自动播放")) {
            viewFlipper.startFlipping();
            ((Button)view).setText("停止播放");
        } else if (((Button)view).getText().toString().equals("停止播放")) {
            viewFlipper.stopFlipping();
            ((Button)view).setText("自动播放");
        }
    }
    public void next(View view) {
        viewFlipper.setInAnimation(this, R.anim.slide_int_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        viewFlipper.showNext();
    }
}
