package com.example.a059_listenscreenorientation;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button changeScreenOri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeScreenOri = findViewById(R.id.changeScreenOri);
        changeScreenOri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration configuration = MainActivity.this.getResources().getConfiguration();
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }

//         重写Activity的方法，可以监听系统设置的更改
//   note：重写完该方法，还需要在AndroidManifest.xml清单文件中 activity 配置上 要监听的信息，否则，监听不到
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
        Toast.makeText(this, "系统的屏幕方向发生改变\n 修改后的屏幕方向为：" + screen, Toast.LENGTH_SHORT).show();
    }
}
