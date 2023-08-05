package com.example.mvplogin.presenter;

import com.example.mvplogin.IView;
import com.example.mvplogin.model.Callback;
import com.example.mvplogin.model.IModel;
import com.example.mvplogin.model.LoginModel;
import com.example.mvplogin.model.UserBean;

/**
 * P层将M层和V层隔离开来，他俩交互均通过P层
 * P持有M和V的引用，V持有P的引用（为防止内存泄漏，最好为弱引用），M通过执行P给M的回调来反馈
 * 【Android架构之MVC、MVP、MVVM详解】https://qwerhuan.gitee.io/2020/08/16/android/android-jia-gou-zhi-mvc-mvp-mvvm-xiang-jie/
 */

public class MainPresenter implements IPresenter, Callback {
    private IView iView;
    private IModel iModel;

    public MainPresenter(IView iView) {
        this.iView = iView;
        this.iModel = new LoginModel();
    }

    @Override
    public void login(String user, String password) {
        iView.showProgress();
        iModel.login(user, password, this);
    }

    @Override
    public void onSuccess(UserBean userBean) {
        iView.hideProgress();
        iView.toastForSuccess(userBean);
    }

    @Override
    public void onFailure(Throwable throwable) {
        iView.hideProgress();
        iView.toastForFailure(throwable);
    }
}
