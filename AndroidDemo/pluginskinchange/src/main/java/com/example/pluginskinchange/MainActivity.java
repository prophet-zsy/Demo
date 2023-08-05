package com.example.pluginskinchange;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        系统有默认的Factory，用于实例化view，setFactory2方法只能用一次，这里要先于系统设置Factory

        getLayoutInflater().setFactory2(new LayoutInflater.Factory2() {
//            Factory2的接口   Factory2继承了Factory
            @Nullable
            @Override
            public View onCreateView(@Nullable View view, @NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
                Log.i(TAG + TAG, s);
                for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
                    Log.i(TAG, attributeSet.getAttributeName(i));
                }

//                try {
//                    context.getClassLoader().loadClass(s)
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                getResources().getIdentifier(s, )
//                LayoutInflater.from(context).inflate()
                return view;
            }
//            Factory的接口
            @Nullable
            @Override
            public View onCreateView(@NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
