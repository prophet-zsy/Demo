package com.example.testt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

//TODO 这里适配平板的fragment还需要再看再练习

/***
 * 将AFragment与BFragment分别定义在竖屏加载的layout文件夹下和横屏加载的layout-land文件夹下
 * 如果是竖屏设备，按照layout下定义的加载，如果是横屏设备自动按照layout-land下定义的加载
 */
public class PhoneTabletActivity extends AppCompatActivity {

    private SelectedListener listener = new SelectedListener() {
        @Override
        public void select(String city) {
            Toast.makeText(PhoneTabletActivity.this, "你选择的城市为" + city, Toast.LENGTH_SHORT).show();
            if (!island) {
                Intent intent = new Intent();
                intent.setClass(PhoneTabletActivity.this, SelectedActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            } else {
                Bfragment.setFragTv(city);
            }
        }
    };
    private AFragment Afragment;
    private BFragment Bfragment;
    private boolean island = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_tablet);
        Afragment = (AFragment) getSupportFragmentManager().findFragmentById(R.id.AFragment);
        Bfragment = (BFragment) getSupportFragmentManager().findFragmentById(R.id.BFragment);
        Afragment.setSelectedListener(listener);
        if (Bfragment == null) island = false;   // 这里根据加载的哪个判断是否横屏
        else island = true;
    }


}
