package com.example.testt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SingTopActivity extends AppCompatActivity {

    private TextView selfId;
    private Button into_self;
    private Button into_MainActivity;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_top);
        selfId = findViewById(R.id.selfId);
        into_self = findViewById(R.id.into_self);
        selfId.setText(this.toString());
        into_MainActivity = findViewById(R.id.into_MainActivity);
        into_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SingTopActivity.this, SingTopActivity.class);
                startActivity(intent);
            }
        });
        into_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SingTopActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(SingTopActivity.this, "调用了onNewIntent" + count ++, Toast.LENGTH_SHORT).show();
    }
}
