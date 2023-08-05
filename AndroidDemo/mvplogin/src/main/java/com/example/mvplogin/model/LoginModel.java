package com.example.mvplogin.model;

import android.os.Handler;
import android.os.Looper;


/**
 * M层主要负责生产数据，数据来源可以为本地缓存或者网络
 */

public class LoginModel implements IModel {
    @Override
    public void login(final String user, final String password, final Callback callback) {
        Handler.createAsync(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(new UserBean(user, password));
            }
        }, 1000);
    }
}
