package com.example.mvpshop.ui.home.adapter;

import android.view.ViewStructure;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvpshop.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class GridSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {

    private List<Goods> data;

    public GridSpanSizeLookUp() {
        this.data = new ArrayList<>();
    }

    public void setData(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }
}
