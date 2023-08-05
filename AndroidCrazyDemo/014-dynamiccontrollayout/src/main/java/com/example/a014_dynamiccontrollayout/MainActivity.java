package com.example.a014_dynamiccontrollayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private Switch aSwitch;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void initView() {
        toggleButton = findViewById(R.id.toggle);
        aSwitch = findViewById(R.id.switcher);
        linearLayout = findViewById(R.id.linearLayout);
    }

    private void registerListener() {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    toggleButton.setChecked(true);
                    aSwitch.setChecked(true);
                } else {
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    toggleButton.setChecked(false);
                    aSwitch.setChecked(false);
                }
            }
        };
        toggleButton.setOnCheckedChangeListener(onCheckedChangeListener);
        aSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
