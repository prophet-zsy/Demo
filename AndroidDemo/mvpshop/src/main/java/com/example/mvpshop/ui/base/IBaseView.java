package com.example.mvpshop.ui.base;

import androidx.lifecycle.LifecycleObserver;

/**
 * view层的顶层接口
 * 所有关于UI应具备的通用功能可以写在这里
 */

public interface IBaseView {
//    void addLifecycleObserver(LifecycleObserver lifecycleObserver);
    void showErrorMessage(String msg);
}
