package com.example.a044_datepickerdialogandtimepickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        Calendar calendar = Calendar.getInstance();  // todo 安卓中Calendar获取的是手机系统的当前时间
        switch (view.getId()) {
            case R.id.datePickerDialog:
                DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Toast.makeText(MainActivity.this, "您选择了" + i + "年" + (i1+1)  + "月" + i2 + "日", Toast.LENGTH_SHORT).show();
                    }
                };
                new DatePickerDialog(this, listener1,   // 没有使用Builder
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case R.id.timePickerDialog:
                TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Toast.makeText(MainActivity.this, "您选择了" + i + "时" + i1 + "分", Toast.LENGTH_SHORT).show();
                    }
                };
                new TimePickerDialog(this, listener2,   // 没有使用Builder
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true)
                        .show();
                break;
        }
    }
}
