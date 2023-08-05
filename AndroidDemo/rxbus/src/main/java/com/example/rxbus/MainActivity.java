package com.example.rxbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    private TextView tvContent;

    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tvContent);

        Subscription subscription = RxBus.getDefault().toObservable(EventBean.class)
                .subscribe(new Action1<EventBean>() {
                    @Override
                    public void call(EventBean eventBean) {
                        tvContent.setText(eventBean.getUserId() + "------" + eventBean.getNickName());
                    }
                });
        rxBusList.add(subscription);

    }

    public void sendEvent(View view) {
        RxBus.getDefault().post(new EventBean(num++, "听说名字长回头率很高"));
    }

}
