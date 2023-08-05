package com.example.mvpshop.network;

import com.example.mvpshop.bean.Goods;

public class Services {
    public static final GoodsService GOODS_SERVICE = RetrofitClient.getInstance().getService(GoodsService.class);
}
