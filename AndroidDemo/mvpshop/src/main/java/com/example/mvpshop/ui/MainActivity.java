package com.example.mvpshop.ui;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.app.Application;
import android.content.Intent;
import android.media.Ringtone;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mvpshop.R;
import com.example.mvpshop.ui.base.BaseActivity;
import com.example.mvpshop.ui.base.BaseFragment;
import com.example.mvpshop.ui.cart.CartFragment;
import com.example.mvpshop.ui.home.HomeFragment;
import com.example.mvpshop.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavBar;

    private BaseFragment[] fragments;
    int lastFragmentIdx = -1;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        bottomNavBar = find(R.id.bottom_nav_bar);
        bottomNavBar.setOnNavigationItemSelectedListener(this);
        fragments = new BaseFragment[] {
                new HomeFragment(), new CartFragment(), new MineFragment()
        };
        switchFragment(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected LifecycleObserver getLifecycleObserver() {
        return new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                switch (event) {
                    case ON_ANY:
                        Log.i(TAG, "ON_ANY");
                        break;
                    case ON_STOP:
                        Log.i(TAG, "ON_STOP");
                        break;
                    case ON_PAUSE:
                        Log.i(TAG, "ON_PAUSE");
                        break;
                    case ON_START:
                        Log.i(TAG, "ON_START");
                        break;
                    case ON_CREATE:
                        Log.i(TAG, "ON_CREATE");
                        break;
                    case ON_RESUME:
                        Log.i(TAG, "ON_RESUME");
                        break;
                    case ON_DESTROY:
                        Log.i(TAG, "ON_DESTROY");
                        break;
                }
            }
        };
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                switchFragment(0);
                break;
            case R.id.nav_cart:
                switchFragment(1);
                break;
            case R.id.nav_mine:
                switchFragment(2);
                break;
        }
        return false;
    }

    public void switchFragment(int to) {
        if (to == lastFragmentIdx) return;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (! fragments[to].isAdded()) fragmentTransaction.add(R.id.container, fragments[to]);
        else fragmentTransaction.show(fragments[to]);
        if (lastFragmentIdx != -1) fragmentTransaction.hide(fragments[lastFragmentIdx]);
        fragmentTransaction.commit();
        lastFragmentIdx = to;
    }
}
