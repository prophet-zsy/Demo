package com.example.a055_sendsmslistenerbyoutterclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText address;
    private EditText content;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = findViewById(R.id.address);
        content = findViewById(R.id.content);
        send = findViewById(R.id.button);

        send.setOnLongClickListener(new SendSmsListener(this, address, content));
    }
}
