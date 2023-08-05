package com.example.floatwindow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button floatWindowControl;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatWindowControl = findViewById(R.id.floatWindowControl);
        intent = new Intent(this, FloatWindowService.class);
        checkFloatWindowPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (! isServiceRunning()) {
            floatWindowControl.setText("打开悬浮窗");
        } else {
            floatWindowControl.setText("关闭悬浮窗");
        }
    }

    public void startFloatWindow(View view) {
        if (! isServiceRunning()) {
            startService(intent);
            floatWindowControl.setText("关闭悬浮窗");
        } else {
            stopService(intent);
            floatWindowControl.setText("打开悬浮窗");
        }
    }

    private boolean isServiceRunning() {
        return ((App)getApplication()).floatWindowService != null;
    }

    private void checkFloatWindowPermissions() {
//        todo 为什么不弹窗申请？？？
        Log.d(TAG, "checkFloatWindowPermissions: ");
        if (checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[] {Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + Arrays.toString(permissions) + "], grantResults = [" + Arrays.toString(grantResults)  + "]");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
