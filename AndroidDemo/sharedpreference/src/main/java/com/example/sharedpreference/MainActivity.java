package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit (View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = ((EditText) findViewById(R.id.submitContent)).getText().toString();
        editor.putString("the key", value);
        editor.commit();
        Toast.makeText(this, "写入SharedPreference成功", Toast.LENGTH_SHORT).show();
    }

    public void readSP (View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        Toast.makeText(this, "读取SharedPreference成功" + sharedPreferences.getString("the key", "-1"), Toast.LENGTH_SHORT).show();
    }
}
