package com.example.customtitle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Type;

public class MyTitle2 extends LinearLayout {

    private Button backPack;
    private TextView title;
    private String titleText = "自定义标题2";

    public MyTitle2(Context context) {
        super(context);
        init(context);
    }

    public MyTitle2(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitle2Attrs);
        titleText = ta.getString(R.styleable.MyTitle2Attrs_title);
        init(context);
    }

    public MyTitle2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTitle2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        backPack = new Button(context);
        backPack.setText("返回");
        title = new TextView(context);
        title.setText(titleText);
        title.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        title.setLayoutParams(
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        this.addView(backPack);
        this.addView(title);
        this.setOrientation(HORIZONTAL);
    }
}
