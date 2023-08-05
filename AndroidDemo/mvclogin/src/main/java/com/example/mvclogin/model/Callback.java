package com.example.mvclogin.model;

public interface Callback {
    public void onSuccess(UserBean userBean);
    public void onFailure(Throwable throwable);
}
