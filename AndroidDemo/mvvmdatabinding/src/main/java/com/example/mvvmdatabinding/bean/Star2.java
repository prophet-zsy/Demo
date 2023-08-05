package com.example.mvvmdatabinding.bean;

import android.database.Observable;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

public class Star2 {
    private ObservableField<String> name;
    private ObservableField<Integer> fanNum;

    public Star2() {
    }

    public Star2(ObservableField<String> name, ObservableField<Integer> fanNum) {
        this.name = name;
        this.fanNum = fanNum;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<Integer> getFanNum() {
        return fanNum;
    }

    public void setFanNum(ObservableField<Integer> fanNum) {
        this.fanNum = fanNum;
    }
}
