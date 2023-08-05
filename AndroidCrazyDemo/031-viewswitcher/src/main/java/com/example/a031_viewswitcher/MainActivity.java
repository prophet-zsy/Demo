package com.example.a031_viewswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
//    数据
    private static class DataItem {
        String dataName;
        Drawable drawable;
        public DataItem(String dataName, Drawable drawable) {
            this.dataName = dataName;
            this.drawable = drawable;
        }
    }
    private class ViewHolder {
        private ImageView image;
        private TextView text;
    }
    private List<DataItem> dataItemList = new ArrayList<>();
    private static final int NUMBER_PER_SCREEN = 12;
    private int screenNo = -1;
    private int screenCount = 0;
    private ViewSwitcher viewSwitcher;
    private BaseAdapter adapter = new BaseAdapter() {  // 每一页是一个GridView, 每个GridView设置一下adapter
        @Override
        public int getCount() {
            if (screenNo == screenCount - 1 && dataItemList.size() % NUMBER_PER_SCREEN != 0) {
                return dataItemList.size() % NUMBER_PER_SCREEN;
            }
            return NUMBER_PER_SCREEN;
        }

        @Override
        public Object getItem(int i) {
            Log.d(TAG, "getItem:  i " + i + "   itemId : " + (screenNo * NUMBER_PER_SCREEN + i));
            return dataItemList.get(screenNo * NUMBER_PER_SCREEN + i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.launcher_icon, viewGroup, false);
                ViewHolder holder = new ViewHolder();
                holder.image = view.findViewById(R.id.image);
                holder.text = view.findViewById(R.id.text);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.image.setImageDrawable(((DataItem) getItem(i)).drawable);
            holder.text.setText(((DataItem) getItem(i)).dataName);
            Log.d(TAG, "getView: " + i + "  " + holder.text.getText().toString());
            return view;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 40; i++) {
            dataItemList.add(new DataItem(String.valueOf(i), getResources().getDrawable(R.mipmap.ic_launcher, null)));
        }
        screenCount = (dataItemList.size() + NUMBER_PER_SCREEN - 1) / NUMBER_PER_SCREEN;
        viewSwitcher = findViewById(R.id.view_switcher);
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                GridView gridView = new GridView(MainActivity.this);
                gridView.setNumColumns(4);
//                gridView.setAdapter(adapter);  // adapter为什么不在这里设置，在这里设置，会出现问题
                return gridView;
            }
        });
        next(null);
    }

    public void prev(View view) {
        if (screenNo == 0) return;
        viewSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        viewSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
        ((GridView) viewSwitcher.getNextView()).setAdapter(adapter);  // todo adapter为什么要在这里设置，在创建的时候设置，会出现问题
        viewSwitcher.showPrevious();
        screenNo --;
    }
    public void next(View view) {
        if (screenNo == screenCount - 1) return;
        viewSwitcher.setInAnimation(this, R.anim.slide_in_right);
        viewSwitcher.setOutAnimation(this, R.anim.slide_out_left);
        ((GridView) viewSwitcher.getNextView()).setAdapter(adapter);
        viewSwitcher.showNext();
        screenNo ++;
    }
}
