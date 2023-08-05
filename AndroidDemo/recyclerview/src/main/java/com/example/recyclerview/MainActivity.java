package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    RecyclerView recyclerView;

    String[] content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListView();
        initRecyclerView();
    }

    private void init() {
        listView = findViewById(R.id.list_view);
        recyclerView = findViewById(R.id.recycler_view);
        int len = 100000;
        content = new String[len];
        for (int i = 0; i < len; i++) {
            content[i] = String.valueOf(i);
        }

    }

    private void initListView() {
        //          ArrayAdapter使用
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
//        SimpleAdapter使用
//        List<Map<String, String>> data = new ArrayList<>();
//        for (int i = 0; i < content.length; i++) {
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("str", content[i]);
//            data.add(hashMap);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1, new String[]{"str"}, new int[]{android.R.id.text1});
//        BaseAdapter继承使用
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return content.length;
            }

            @Override
            public Object getItem(int position) {
                return content[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                    textView = convertView.findViewById(android.R.id.text1);
                    convertView.setTag(textView);
                } else
                    textView = (TextView) convertView.getTag();
                textView.setText(content[position]);
                return convertView;
            }
//            viewHolder使用(就是使用一个类来保存多个view树上的节点，以避免下次再使用findViewById寻找view树上的节点)
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                ViewHolder viewHolder;
//                if (convertView == null) {
//                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
//                    viewHolder = new ViewHolder();
//                    viewHolder.textView = convertView.findViewById(android.R.id.text1);
//                    convertView.setTag(viewHolder);
//                } else
//                    viewHolder = (ViewHolder) convertView.getTag();
//                viewHolder.textView.setText(content[position]);
//                return convertView;
//            }
//            class ViewHolder {
//                TextView textView;
//            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "you click on " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        class VH extends RecyclerView.ViewHolder {
            TextView textView;
            VH(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
        RecyclerView.Adapter<VH> adapter = new RecyclerView.Adapter<VH>() {
            @NonNull
            @Override
            public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
                VH vh = new VH(view);
                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull VH holder, final int position) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                holder.textView.setText(content[position]);
            }

            @Override
            public int getItemCount() {
                return content.length;
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}
