package com.example.mvpshop.ui.GoodsDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpshop.R;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.ui.base.BaseActivity;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.IView, View.OnClickListener, LifecycleObserver {

    public static final String GOODS_ID = "goods_id";
    private GoodsDetailContract.IPresenter iPresenter;
    private TextView text;
    private Toolbar toolBar;
    private int[] data;

    private static final String TAG = "GoodsDetailActivity";

    @Override
    protected void initViews() {
        this.text = find(R.id.text);
        this.toolBar = find(R.id.tool_bar);
        this.toolBar.setNavigationOnClickListener(this);
//        this.iPresenter = new GoodsDetailPresenter(this);
        this.iPresenter = GoodsDetailPresenter.getInstance(this);  // 这里将GoodsDetailPresenter设计成静态单例，为了其和GCRoot连接，以验证该类实例发生了内存泄漏
        this.iPresenter.attachView(this);
        int goods_id = getIntent().getIntExtra(GOODS_ID, -1);
        this.iPresenter.getData(goods_id);

        newData();
    }

    private void newData() {
        int len = 1024 * 1024 * 10;
        this.data = new int[len]; // 增加该类持有的内存量，查看内存泄漏
        for (int i = 0; i < len; i++) {
            this.data[i] = i;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.iPresenter.detachView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected LifecycleObserver getLifecycleObserver() {
        return this;
    }

    @Override
    public void onGetDataSuccess(GoodsDetail goodsDetail) {
        toolBar.setTitle(goodsDetail.getName());
        text.setText(goodsDetail.getDescription());
    }

    @Override
    public void onGetDataFailed(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void lifeFun (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeFun");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void lifeOnCreate (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnCreate");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void lifeOnStart (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnStart");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void lifeOnResume (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnResume");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void lifeOnPause (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnPause");

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void lifeOnStop (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnStop");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void lifeOnDestroy (LifecycleOwner lifecycleOwner) {
        Log.i(TAG, "lifeOnDestroy");
    }
}
