package com.example.testt;

import android.app.ActionBar;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

//TODO AcitonBar 该版本不能使用newTab()方法对tab进行初始化


public class ActionBarActivity extends Activity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        actionBar = getActionBar();
        TabClickListener tabClickListener = new TabClickListener();
        ActionBar.Tab tab1 = new actionBar.newTab();
        tab1.setText("TAB1");
        tab1.setTabListener(tabClickListener);
        ActionBar.Tab tab2 = new actionBar.newTab();
        tab2.setText("TAB2");
        tab2.setTabListener(tabClickListener);

    }

    class TabClickListener extends ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}
