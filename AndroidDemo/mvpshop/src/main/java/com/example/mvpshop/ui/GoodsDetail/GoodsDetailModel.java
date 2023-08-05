package com.example.mvpshop.ui.GoodsDetail;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.network.GoodsService;
import com.example.mvpshop.network.Services;

import io.reactivex.rxjava3.core.Flowable;

public class GoodsDetailModel implements GoodsDetailContract.IModel {
    @Override
    public Flowable<BaseBean<GoodsDetail>> getGoodsDetail() {
        return Services.GOODS_SERVICE.getGoodsDetail();
    }
}
