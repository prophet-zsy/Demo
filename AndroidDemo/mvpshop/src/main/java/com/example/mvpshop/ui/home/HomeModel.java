package com.example.mvpshop.ui.home;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.network.Services;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeModel implements HomeContract.IModel {

    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return Services.GOODS_SERVICE.getGoods();
    }

}
