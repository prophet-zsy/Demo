package com.example.mvpshop.ui.base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment implements IBaseView {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return view;
    }

    protected abstract void initViews();
    protected abstract int getLayoutId();
    protected <T> T find(int resId) {
        return (T) view.findViewById(resId);
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
