package com.example.mvvmdatabinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.lifecycle.Observer;

import com.example.mvvmdatabinding.bean.Star;
import com.example.mvvmdatabinding.bean.Star2;
import com.example.mvvmdatabinding.bean.Star4;
import com.example.mvvmdatabinding.databinding.ActivityMainBinding;


/***
 * 【Android Jetpack 之ViewBinding和DataBinding】https://huanglinqing.blog.csdn.net/article/details/106228067
 *  ViewBinding 自动生成xml文件中id对应的view变量；DataBinding 需要在xml布局文件中定义Bean类，然后然后可以通过修改Bean类实时更新界面上的值
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bindStar(viewDataBinding); // 使用bean类继承BaseObservable
        bindStar2(viewDataBinding); // ObservableField包装bean中的字段
        bindStar3(viewDataBinding); // 使用可被观察的集合
        bindStar4(viewDataBinding); // 使用liveData实现双向绑定
    }

    public void bindStar(ActivityMainBinding viewDataBinding) {
        final Star star = new Star("蔡徐坤", 1000);
        viewDataBinding.setStar(star);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    star.setFanNum(star.getFanNum()+1);
                }
            }
        }).start();
    }
    public void bindStar2(ActivityMainBinding viewDataBinding) {
        final Star2 star = new Star2(new ObservableField<String>("蔡徐坤"), new ObservableField<Integer>(1000));
        viewDataBinding.setStar2(star);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    star.getFanNum().set(star.getFanNum().get()+1);
//                    star.setFanNum(new ObservableField<Integer>(star.getFanNum().get()+1));  // 这种写法不行，被观察的是对象ObservableField，换对象并不能同步到界面，需要重新setStar2
                }
            }
        }).start();
    }
    public void bindStar3(ActivityMainBinding viewDataBinding) {
        final ObservableArrayMap star = new ObservableArrayMap();
        star.put("name", "蔡徐坤");
        star.put("fanNum", 1000);
        viewDataBinding.setStar3(star);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    star.put("fanNum", (int)star.get("fanNum") + 1);
                }
            }
        }).start();
    }
    public void bindStar4(final ActivityMainBinding viewDataBinding) {
        final Star4 star4 = new Star4("蔡徐坤", 1000);
        final Star4ViewModel star4ViewModel = new Star4ViewModel();
        star4ViewModel.getMutableLiveData().setValue(star4);
        viewDataBinding.setStar4(star4ViewModel);
        star4ViewModel.getMutableLiveData().observe(this, new Observer<Star4>() {
            @Override
            public void onChanged(Star4 star4) {
                viewDataBinding.text.setText(star4.getName() + " " + star4.getFanNum());
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    star4.setFanNum(star4.getFanNum() + 1);
                    star4ViewModel.getMutableLiveData().postValue(star4);
                }
            }
        }).start();
    }
}
