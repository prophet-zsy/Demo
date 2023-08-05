package com.example.mvvmdatabinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdatabinding.bean.Star4;

public class Star4ViewModel extends ViewModel {

    private MutableLiveData<Star4> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Star4> getMutableLiveData() {
        return mutableLiveData;
    }


}
