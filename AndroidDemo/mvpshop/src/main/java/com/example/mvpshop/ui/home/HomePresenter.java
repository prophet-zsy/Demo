package com.example.mvpshop.ui.home;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomeContract.IPresenter {

    private HomeContract.IModel iModel;
    private HomeContract.IView iView;

    public HomePresenter(HomeContract.IView iView) {
        this.iView = iView;
        this.iModel = new HomeModel();
    }


    @Override
    public void getData() {
        if (this.iModel != null && this.iView != null) {
            Flowable<BaseBean<List<Goods>>> baseBeanFlowable = this.iModel.getData();
            baseBeanFlowable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                        @Override
                        public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                            iView.onGetDataSuccess(listBaseBean.getData());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            iView.onGetDataFailed(throwable);
                        }
                    });
        }
    }
}
