package com.example.mvpshop.network;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface GoodsService {

    @GET("goods_list")
    public Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("goods_detail")
    public Flowable<BaseBean<GoodsDetail>> getGoodsDetail();
}
