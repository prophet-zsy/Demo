package com.example.mvpshop.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import android.os.Bundle;
import android.view.View;

import com.example.mvpshop.R;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        addLifecycleObserver(getLifecycleObserver());
        initViews();
    }

    protected abstract void initViews();
    protected abstract int getLayoutId();
    protected abstract LifecycleObserver getLifecycleObserver();
    protected <T> T find(int resId) {
        return (T) findViewById(resId);
    }

    public void addLifecycleObserver(LifecycleObserver lifecycleObserver) {
        getLifecycle().addObserver(lifecycleObserver);
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
