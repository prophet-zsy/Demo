package com.example.mvpshop.ui.GoodsDetail;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.ui.base.IBasePresenter;
import com.example.mvpshop.ui.base.IBaseView;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface GoodsDetailContract {

    interface IPresenter extends IBasePresenter {
        void getData(int goods_id);
        void attachView(GoodsDetailContract.IView iView);
        void detachView();
    }

    interface IView extends IBaseView {
        void onGetDataSuccess(GoodsDetail goodsDetail);

        void onGetDataFailed(Throwable throwable);
    }

    interface IModel {
        Flowable<BaseBean<GoodsDetail>> getGoodsDetail();
    }
}
