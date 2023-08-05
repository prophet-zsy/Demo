package com.example.uitestespresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_content) TextView tvContent;
    @BindView(R.id.et_01) EditText et01;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.btn01, R.id.btn02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                //显示hello espresso!
                Log.d(TAG, "onViewClicked: hello espresso");
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("hello espresso!");
                break;
            case R.id.btn02:
                //登陆成功并且清空输入框
                Log.d(TAG, "onViewClicked: success");
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("success");
                et01.setText("");
                break;
        }
    }

}
