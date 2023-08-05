package com.example.mvplogin;

import com.example.mvplogin.model.UserBean;

public interface IView {
    public void showProgress();
    public void hideProgress();

    public void toastForSuccess(UserBean userBean);
    public void toastForFailure(Throwable throwable);
}
