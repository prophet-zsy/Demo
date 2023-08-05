package com.example.rxbus;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import rx.Subscription;

public class BaseActivity extends AppCompatActivity {
    protected ArrayList<Subscription> rxBusList = new ArrayList<>();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        clearSubscription();
    }

    /**
     * 取消该页面所有订阅
     */
    private void clearSubscription() {
        for (Subscription subscription : rxBusList) {
            if (subscription != null && subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}