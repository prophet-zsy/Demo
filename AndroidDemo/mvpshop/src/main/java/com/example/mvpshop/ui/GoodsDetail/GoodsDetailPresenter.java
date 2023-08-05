package com.example.mvpshop.ui.GoodsDetail;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpshop.bean.BaseBean;
import com.example.mvpshop.bean.GoodsDetail;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GoodsDetailPresenter implements GoodsDetailContract.IPresenter {

    private GoodsDetailContract.IModel iModel;
//  使用弱引用持有GoodsDetailActivity的实例，不影响其回收
    private WeakReference<GoodsDetailContract.IView> iView;
//  设计为静态单例，以连接GCRoot，方便测试GoodsDetailActivity的内存泄漏
    private static volatile GoodsDetailPresenter ins;

    public GoodsDetailPresenter() {
        this.iModel = new GoodsDetailModel();
    }

    public void attachView(GoodsDetailContract.IView iView) {  // 每次进入GoodsDetailActivity，重新绑定对其的弱引用
        this.iView = new WeakReference<>(iView);
    }

    public void detachView() {
        this.iView.clear();
        this.iView = null;
    }

    public static GoodsDetailPresenter getInstance(GoodsDetailContract.IView iView) {
        if (ins == null) {
            synchronized (GoodsDetailPresenter.class) {
                if (ins == null) {
                    ins = new GoodsDetailPresenter();
                }
            }
        }
        return ins;
    }

    @Override
    public void getData(int goods_id) {
        if (iModel != null && iView != null) {
            iModel.getGoodsDetail().observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<BaseBean<GoodsDetail>>() {
                        @Override
                        public void accept(BaseBean<GoodsDetail> goodsDetailBaseBean) throws Throwable {
                            iView.get().onGetDataSuccess(goodsDetailBaseBean.getData());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            iView.get().onGetDataFailed(throwable);
                        }
                    });
        }
    }
}
