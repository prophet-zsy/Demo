package com.example.a022_listviewbysimpleadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView listView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("header", imgIds[i]);
            map.put("name", names[i]);
            map.put("desc", descs[i]);
            mapList.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                mapList, R.layout.simple_item,
                new String[] {"header", "name", "desc"},            // from
                new int[] {R.id.header, R.id.name, R.id.describe}   // to
                );
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, names[(int)l] + "被点击了");
            }
        });
    }
}
