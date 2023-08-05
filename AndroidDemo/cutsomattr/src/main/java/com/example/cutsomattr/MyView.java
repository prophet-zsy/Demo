package com.example.cutsomattr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyView extends TextView {

    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
        Log.d(TAG, "MyView: ");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        属性获取方式1：
        int attributeIntValue = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "yourCustomAttr", -1);
        Log.d(TAG, "MyView: " + attributeIntValue);
//        属性获取方式2：
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int attrValue = typedArray.getInt(R.styleable.MyView_yourCustomAttr, -1);
        Log.d(TAG, "MyView: " + attrValue);
    }
}
