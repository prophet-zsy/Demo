package com.example.networktools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Tools {
    public static boolean NETWORK_STATE = false;
    public static void checkNetWorkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            NETWORK_STATE = false;
            Toast.makeText(context, "无可用网络", Toast.LENGTH_SHORT).show();
        } else {
            if (activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    NETWORK_STATE = true;
                    Toast.makeText(context, "当前使用移动网络", Toast.LENGTH_SHORT).show();
                } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    NETWORK_STATE = true;
                    Toast.makeText(context, "当前使用无线网络", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
