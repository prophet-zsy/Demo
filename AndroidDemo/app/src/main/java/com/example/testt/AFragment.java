package com.example.testt;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;


public class AFragment extends ListFragment {
    private String[] data = {"上海", "北京", "深圳", "广州"};
    private SelectedListener selectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                data
        ));
    }

    public void setSelectedListener(SelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        selectedListener.select(data[position]);
    }
}
