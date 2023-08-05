package com.example.a032_imageswitcher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ImageSwitcher 和 ViewSwitcher 的使用相似，
 * 1、先设置生成对应的View的工厂方法，2、然后对Switcher进行控制即可
 *
 * TextSwitcher 和 ImageSwitcher 同样的 逻辑，此处不再举例
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GridView gridView;
    private ImageSwitcher imageSwitcher;

    private int[] images = new int[] {
            R.drawable.img111,
            R.drawable.img222,
            R.drawable.img333,
    };
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        imageSwitcher = findViewById(R.id.image_switcher);
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int i) {
                return images[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_view_item, viewGroup, false);
                }
                ((ImageView) view).setImageResource(images[i]);
                return view;
            }
        };
        gridView.setAdapter(adapter);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageSwitcher.setImageResource(images[(int)l]);
            }
        });
    }
}
