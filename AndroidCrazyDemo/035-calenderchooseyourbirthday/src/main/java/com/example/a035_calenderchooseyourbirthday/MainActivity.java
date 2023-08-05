package com.example.a035_calenderchooseyourbirthday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {  // 后三个参数：年月日 (月份从0开始，所以要+1)
                Toast.makeText(MainActivity.this, "您选择的生日为"+ i + "年" + (i1 + 1) + "月" + i2 + "日", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
