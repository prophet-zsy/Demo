package com.example.a037_numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker lowNumPicker;
    private NumberPicker highNumPicker;

    private int lowVal = 25;
    private int highVal = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lowNumPicker = findViewById(R.id.LowNumberPicker);
        lowNumPicker.setMaxValue(50);  // 必须设置，否则不知道在哪个区间滚动
        lowNumPicker.setMinValue(10);  // 必须设置，否则不知道在哪个区间滚动
        lowNumPicker.setValue(25);     // 非必须，不设置初始化，默认从最小值开始
        lowNumPicker.setWrapSelectorWheel(false);  // 设置不能循环滚动，必须在setMaxValue和setMinValue之后调用才生效
        highNumPicker = findViewById(R.id.HighNumberPicker);
        highNumPicker.setMaxValue(100);
        highNumPicker.setMinValue(50);
        highNumPicker.setValue(75);
        highNumPicker.setWrapSelectorWheel(false);

        lowNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {  // i 为旧值 i1 为新值
                lowVal = i1;
                showMsg();
            }
        });
        highNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                highVal = i1;
                showMsg();
            }
        });
    }

    private void showMsg() {
        Toast.makeText(this, "您选择的最低价格为" + lowVal + " 选择的最高价格为" + highVal, Toast.LENGTH_SHORT).show();
    }
}
