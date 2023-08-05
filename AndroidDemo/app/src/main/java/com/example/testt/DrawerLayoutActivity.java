package com.example.testt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.lang.reflect.Field;

public class DrawerLayoutActivity extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private FrameLayout drawerContent;
    private String[] title = {"fragment1", "fragment2", "fragment3", "fragment4"};
    //    先将fragment实例化了，便不是fragment的懒加载了（懒加载，是用的时候再进行初始化）
    private Fragment[] fragments = {MyFragment.newInstance(1), MyFragment.newInstance(2), MyFragment.newInstance(3), MyFragment.newInstance(4)};

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        reflectAssignValue(fragments[0], R.color.RED);
        reflectAssignValue(fragments[1], R.color.BLUE);
        reflectAssignValue(fragments[2], R.color.GREEN);
        reflectAssignValue(fragments[3], R.color.colorPrimaryDark);

        fragmentManager = getSupportFragmentManager();
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerList = findViewById(R.id.drawerList);
        drawerContent = findViewById(R.id.drawerContent);

        drawerList.setAdapter(new ArrayAdapter<String>(DrawerLayoutActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                title));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.drawerContent, fragments[position]);
                ft.commit();
                drawerLayout.closeDrawer(drawerList);
            }
        });
    }

    public void reflectAssignValue(Fragment fragment, int colorValue) {
        try {
            Field color = fragment.getClass().getDeclaredField("color");
            color.setAccessible(true);
            color.set(fragment, colorValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
