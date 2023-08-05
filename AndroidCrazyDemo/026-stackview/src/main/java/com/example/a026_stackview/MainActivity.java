package com.example.a026_stackview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private StackView stackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stackView = findViewById(R.id.stackView);

        BaseAdapter adapter = new BaseAdapter() {
            int[] imgs = new int[]{
                R.drawable.bomb0,
                R.drawable.bomb1,
                R.drawable.bomb2,
            };

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
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_view_layout, null);
                }
                ((ImageView) view).setImageResource(imgs[i]);
                return view;
            }
        };
        stackView.setAdapter(adapter);
    }

    public void last(View view) {
        stackView.showPrevious();
    }
    public void next(View view) {
        stackView.showNext();
    }
}
