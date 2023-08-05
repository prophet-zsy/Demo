package com.example.testt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FixedTabActivity extends AppCompatActivity {

    private LinearLayout tabContainer;
    private String[] tabs;
    private View[] tabViews;
    private ViewPager viewPager;
    private View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_tab);
        init();
    }
    private void init() {
        tabs = new String[]{"view1", "view2", "view3"};
        tabViews = new View[3];
        tabContainer = findViewById(R.id.tabContainer);
        for (int i = 0; i < tabContainer.getChildCount(); i ++) {
            TextView tv = (TextView) tabContainer.getChildAt(i);
            tabViews[i] = tv;
            tv.setText(tabs[i]);
            tv.setTag(i);
            tv.setEnabled(true);
            tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idx = (int)v.getTag();
                    selectTitle(idx);
                    viewPager.setCurrentItem(idx);
                }
            });
        }

        views = new View[3];
        views[0] = getLayoutInflater().inflate(R.layout.list_item, null);
        views[1] = getLayoutInflater().inflate(R.layout.list_item, null);
        views[2] = getLayoutInflater().inflate(R.layout.list_item, null);
        viewPager = findViewById(R.id.viewPager2);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void selectTitle (int idx){
        for (int i = 0; i < tabViews.length; i++) {
            if (i == idx) tabViews[i].setBackgroundColor(getResources().getColor(R.color.RED));
            else tabViews[i].setBackgroundColor(getResources().getColor(R.color.BLUE));
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views[position]);
            return views[position];
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views[position]);
        }
    }
}
