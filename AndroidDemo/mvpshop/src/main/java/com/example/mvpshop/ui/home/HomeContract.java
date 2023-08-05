package com.example.mvpshop.ui.home;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.ui.base.IBasePresenter;
import com.example.mvpshop.ui.base.IBaseView;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomeContract {

    interface IPresenter extends IBasePresenter {
        void getData();
    }

    interface IView extends IBaseView {
        void onGetDataSuccess(List<Goods> goodsList);

        void onGetDataFailed(Throwable throwable);
    }

    interface IModel {
        Flowable<BaseBean<List<Goods>>> getData();
    }
}
