package com.example.mvvmdatabinding.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmdatabinding.BR;

import static com.example.mvvmdatabinding.BR.*;


/**
 * 使bean类继承BaseObservable
 * 给需要观察的字段添加注解
 * 在set方法里进行notifyPropertyChanged
 * 然后将该变量赋给xml中定义的变量以进行绑定
 */

public class Star extends BaseObservable {
    @Bindable
    private String name;
    @Bindable
    private int fanNum;

    public Star() {
    }

    public Star(String name, int fanNum) {
        this.name = name;
        this.fanNum = fanNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getFanNum() {
        return fanNum;
    }

    public void setFanNum(int fanNum) {
        this.fanNum = fanNum;
        notifyPropertyChanged(BR.fanNum);
    }
}
