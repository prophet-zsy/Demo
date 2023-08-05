package com.example.a043_popupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * PopupWindow 非常适合显示一些浮动显示的内容，就是位置不太固定的dialog
 *
 */

public class MainActivity extends AppCompatActivity {

    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow, null, false);
        popupWindow = new PopupWindow(inflate, 560, 720);
    }

    public void showPopupWindow(View view){
        if (((Button) view).getText().toString().equals("弹出popup窗口")) {
//            popupWindow.showAsDropDown(view);   // 以View的下拉组件显示出来
            popupWindow.showAtLocation(view, Gravity.CENTER, 20, 20);  // 在指定位置显示出来
            ((Button) view).setText("关闭popup窗口");
        } else if (((Button) view).getText().toString().equals("关闭popup窗口")) {
            popupWindow.dismiss();
            ((Button) view).setText("弹出popup窗口");
        }
    }
}
