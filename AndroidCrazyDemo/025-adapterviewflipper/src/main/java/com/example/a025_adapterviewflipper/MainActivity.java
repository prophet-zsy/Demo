package com.example.a025_adapterviewflipper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * AdapterViewFlipper 是 AdapterViewAnimator 的子类
 * AdapterView 不仅可以适配同时显示在界面上的元素，还可以适配 切换单个元素的场景
 * AdapterView 的任务就是根据不同场景装填合适的数据
 */

public class MainActivity extends AppCompatActivity {
    private AdapterViewFlipper flipper;
    private int[] imgs = new int[] {
            R.drawable.ic_menu_black_24dp,
            R.drawable.ic_mood_bad_black_24dp,
            R.drawable.ic_mood_black_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipper = findViewById(R.id.adapterViewFlipper);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public Object getItem(int i) {
                return imgs[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = new ImageView(MainActivity.this);
                }
                ((ImageView) view).setImageResource(imgs[i]);
                return view;
            }
        };
        flipper.setAdapter(adapter);
    }

    public void last(View view) {
        flipper.stopFlipping();
        flipper.showPrevious();
    }
    public void next(View view) {
        flipper.stopFlipping();
        flipper.showNext();
    }
    public void autoPlay(View view) {
        flipper.startFlipping();
    }
}
