package com.example.testt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerActivity extends AppCompatActivity {

    private int[] colors;
    private View[] views;
    private ViewPager viewPager;
    private String[] tabs;
    private PagerTabStrip tabStrips;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        init();

    }

    void init() {
        colors = new int[] {R.color.RED, R.color.GREEN, R.color.BLUE};  // 现场生成view的时候使用
        views = new View[3];
        views[0] = getLayoutInflater().inflate(R.layout.list_item, null);
        views[0].setBackgroundColor(getResources().getColor(R.color.RED));
        views[1] = getLayoutInflater().inflate(R.layout.list_item, null);
        views[1].setBackgroundColor(getResources().getColor(R.color.GREEN));
        views[2] = getLayoutInflater().inflate(R.layout.list_item, null);
        views[2].setBackgroundColor(getResources().getColor(R.color.BLUE));
        tabs = new String[] {"view1", "view2", "view3"};
        tabStrips = findViewById(R.id.pagerTabStrip);
        tabStrips.setBackgroundColor(R.drawable.ic_launcher_background);
        tabStrips.setTextColor(getResources().getColor(R.color.colorPrimary));
        tabStrips.setTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        pagerAdapter = new MyAdapter();
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            选择1：
            return colors.length;
////            选择2：
//            return views.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            选择1：
            container.removeView((View) object);
////            选择2：
//            container.removeView(views[position]);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            选择1：现场生成view并载入，这种需要对应的PagerAdapter是内部类，或者将生成view所需要的材料（context，colors）通过初始化传进来
            View view = getLayoutInflater().inflate(R.layout.list_item, null);
            view.setBackgroundColor(getResources().getColor(colors[position]));
            container.addView(view);
            return view;
////            选择2：载入提前生成好的view
//            container.addView(views[position]);
//            return views[position];
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
