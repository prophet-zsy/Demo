package com.example.mvpshop;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.bean.GoodsDetail;
import com.example.mvpshop.network.GoodsService;
import com.example.mvpshop.network.RetrofitClient;

import org.junit.Test;

import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testNetwork() {
        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        Consumer<Throwable> consumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                throwable.printStackTrace();
            }
        };
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println(listBaseBean);
            }
        }, consumer);
        goodsService.getGoodsDetail().subscribe(new Consumer<BaseBean<GoodsDetail>>() {
            @Override
            public void accept(BaseBean<GoodsDetail> goodsDetailBaseBean) throws Throwable {
                System.out.println(goodsDetailBaseBean);
            }

        }, consumer);
        while (true);
    }
}