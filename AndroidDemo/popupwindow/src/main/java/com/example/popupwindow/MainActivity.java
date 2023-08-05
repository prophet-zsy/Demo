package com.example.popupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.showPopupWindow);

        popupWindow = new PopupWindow(this);
        TextView textView = new TextView(this);
        textView.setText("popupWindow");
        popupWindow.setContentView(textView);
    }

    public void onClick(View view) {
        if (popupWindow.isShowing()) popupWindow.dismiss();
        else popupWindow.showAsDropDown(button);
    }
}
