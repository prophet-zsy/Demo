package com.example.learnprofiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Test test = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        测试直线view
//        setContentView(new MyView2(this));

//        按键分配大量内存
        setContentView(R.layout.activity_main2);

////        大量动画增长内存
//        setContentView(R.layout.activity_main);
//        GridLayout gridLayout = findViewById(R.id.parent);
//
//        traverseViewGroup(gridLayout);
//
//        getAndroiodScreenProperty();
    }
//    测试内存
    public void allocate(View view) {
        Test newTest = new Test();
        newTest.next = test;
        test = newTest;
    }

    // 遍历viewGroup
    public int traverseViewGroup(View view) {
        int viewCount = 0;
        if (null == view) {
            return 0;
        }
        if (view instanceof ViewGroup) {
            //遍历ViewGroup,是子view加1，是ViewGroup递归调用
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup) {
                    viewCount += traverseViewGroup(((ViewGroup) view).getChildAt(i));
                } else {
                    new Thread((MyView)child).start();
                    viewCount++;
                }
            }
        } else {
            viewCount++;
        }
        return viewCount;
    }

    public void getAndroiodScreenProperty() {
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        Log.d("h_bl", "屏幕宽度（像素）：" + width);
        Log.d("h_bl", "屏幕高度（像素）：" + height);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
    }

}


class Test {
    private byte[] content = new byte[1000 * 1000 * 40];
    Test next = null;
    Test() {
        for (int i = 0; i < content.length; i++) {
            content[i] = 1;
        }
    }
}
