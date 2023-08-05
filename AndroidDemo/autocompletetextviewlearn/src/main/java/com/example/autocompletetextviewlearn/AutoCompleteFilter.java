package com.example.autocompletetextviewlearn;

import android.text.TextUtils;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 【Android API之android.widget.Filterable】https://www.cnblogs.com/fengzhblog/p/3182040.html
 */

public class AutoCompleteFilter extends Filter {
    private AutoCompleteTextViewAdapter adapter;

    public AutoCompleteFilter(AutoCompleteTextViewAdapter adapter) {
        this.adapter = adapter;
    }

//    在worker线程中调用
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();
        List<String> matchRes = match(charSequence);
        filterResults.values = matchRes;
        filterResults.count = matchRes.size();
        return filterResults;
    }
//    从所有数据中筛选出匹配成功的数据
    private List<String> match(CharSequence charSequence) {
        List<String> list = new ArrayList<>();
        List<String> dataForMatch = AutoCompleteTextViewAdapter.allData;
        if (!TextUtils.isEmpty(charSequence)) {
            for (int i = 0; i < dataForMatch.size(); i++) {
                String s = dataForMatch.get(i);
                if (s.startsWith(String.valueOf(charSequence)))
                    list.add(s);
            }
        }
        return list;
    }

//    在UI线程中调用
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.notifyShowData((List<String>) filterResults.values);
    }
}
