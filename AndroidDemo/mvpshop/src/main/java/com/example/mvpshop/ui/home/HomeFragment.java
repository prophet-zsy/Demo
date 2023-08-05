package com.example.mvpshop.ui.home;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvpshop.R;
import com.example.mvpshop.bean.Goods;
import com.example.mvpshop.ui.GoodsDetail.GoodsDetailActivity;
import com.example.mvpshop.ui.base.BaseFragment;
import com.example.mvpshop.ui.home.adapter.GridSpanSizeLookUp;
import com.example.mvpshop.ui.home.adapter.HomeRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IView, HomeRecyclerViewAdapter.OnItemClickListener {

    private HomeRecyclerViewAdapter adapter;  // use data
    private GridSpanSizeLookUp gridSpanSizeLookUp;  // use data

    private HomeContract.IPresenter iPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "HomeFragment";
    @Override
    protected void initViews() {
        swipeRefreshLayout = find(R.id.swipeRefreshLayout);
        RecyclerView recyclerView = find(R.id.recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        gridSpanSizeLookUp = new GridSpanSizeLookUp();
        gridLayoutManager.setSpanSizeLookup(gridSpanSizeLookUp);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new HomeRecyclerViewAdapter(getActivity(), recyclerView, this);
        recyclerView.setAdapter(adapter);
        iPresenter = new HomePresenter(this);
        iPresenter.getData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void onRefresh() {
        this.iPresenter.getData();
    }

    @Override
    public void onGetDataSuccess(List<Goods> goodsList) {
        this.gridSpanSizeLookUp.setData(goodsList);
        this.adapter.setData(goodsList);
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetDataFailed(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Goods goods) {
        Log.i(TAG, "onItemClick" + goods);
        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.GOODS_ID, goods.getGoodsId());
        startActivity(intent);
    }

}
