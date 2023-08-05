package com.example.rsserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.rsserver.IProcessInfoImpl;

public class MyRemoteService extends Service {
    private static final String TAG = "chanchaw";
    IProcessInfoImpl mProcessInfo = new IProcessInfoImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MyRemoteService thread id = " + Thread.currentThread().getId());
        return mProcessInfo;
    }
}