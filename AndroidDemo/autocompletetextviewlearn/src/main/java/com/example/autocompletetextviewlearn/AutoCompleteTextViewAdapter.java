package com.example.autocompletetextviewlearn;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * AutoCompleteTextView控件不支持实时更新待检索的数据（AllData），因此通过自定义过滤规则，自己写更新AllData的逻辑
 */

public class AutoCompleteTextViewAdapter extends ArrayAdapter<String> {
    private static final String TAG = "AutoCompleteTextViewAdapter";
    public static volatile List<String> allData = new ArrayList<>();
    private static List<String> showData = new ArrayList<>();
    public AutoCompleteTextViewAdapter(Context context, Set<String> data) {
        super(context, android.R.layout.simple_list_item_1, showData);
        notifyAllData(data);
    }

//    通知allData
    public void notifyAllData(Set<String> newData) {
        allData.clear();
        allData.addAll(newData);
    }
//    通知showData
    public void notifyShowData(List<String> newShowData) {
        showData.clear();
        showData.addAll(newShowData);
        super.notifyDataSetChanged();
    }

    /**
     * adapter自带filter方案，但是不支持allData的更新，所有这里重新实现一下filter
     * @return
     */
    @NonNull
    @Override
    public Filter getFilter() {
        return new AutoCompleteFilter(this);
    }
}
