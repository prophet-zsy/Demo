package com.example.a034_toastwithimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showToast(View view) {
        Toast.makeText(this, "普通的Toast", Toast.LENGTH_SHORT).show();
    }
    public void showToastWithImg(View view) {
        Toast toast = new Toast(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.toast_view, null, false);
        toast.setView(view1);
        toast.show();
    }
}
