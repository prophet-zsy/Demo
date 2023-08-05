package com.example.a058_getdevicestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        button = findViewById(R.id.get_info);

        final Configuration configuration = getResources().getConfiguration();  // Configuration对象中存储了系统当前的配置信息
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
                String navName = configuration.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制":
                        (configuration.orientation == Configuration.NAVIGATION_WHEEL) ? "滚轮控制方向" :
                        (configuration.orientation == Configuration.NAVIGATION_DPAD) ? "方向键控制方向" :
                                "轨迹球控制方向";
                String touchState = configuration.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏": "支持触摸屏";
                String mncCode = configuration.mnc + "";
                text1.setText("屏幕方向：" + screen);  // 显示屏幕方向
                text2.setText("方向控制设备：" + navName);  // 显示手机方向控制设备
                text3.setText("触摸屏状态：" + touchState);  // 显示触摸屏状态
                text4.setText("移动网络代号：" + mncCode);  // 显示移动网络代号
            }
        });
    }
}
