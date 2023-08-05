package com.example.a027_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * ListView 使用起来缺乏灵活性， 安卓推荐RecyclerView来代替ListView
 * 这样RecyclerView通过LayoutManager可以灵活地设置布局
 * RecyclerView不再提供setOnItemClickListener方法，如果需要绑定事件监听，应在onCreateViewHolder方法中
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<ViewHolder> adapter = new RecyclerView.Adapter<ViewHolder>() {

            private int[] imgIds = new int[] {
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
            };
            private String[] names = new String[] {
                    "虎头",
                    "弄玉",
                    "李清照",
                    "李白",
            };
            private String[] descs = new String[] {
                    "可爱的小孩",
                    "一个擅长音乐的小女孩",
                    "一个擅长文学的女性",
                    "浪漫主义诗人",
            };

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_item, parent, false);
                ViewHolder holder = new ViewHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.header.setImageResource(imgIds[position]);
                holder.name.setText(names[position]);
                holder.desc.setText(descs[position]);
            }

            @Override
            public int getItemCount() {
                return names.length;
            }
        };
        recyclerView.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView header;
        TextView name;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.describe);
        }
    }
}
