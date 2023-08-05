package com.ty.aidlstudy.size;


import android.app.Service;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ty.aidlstudy.NetByte;
import com.ty.aidlstudy.UserMessage;

public class NetService extends Service {
    private CallBack callBack;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new InnerIBinder();
    }
    public class InnerIBinder extends NetByte.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
        @Override
        public void sendMessage(UserMessage message) throws RemoteException {
            Log.d("size sendMessage impl", Thread.currentThread().getName());
            callBack.showMessage(message);
        }

        public Service getService() {
            return NetService.this;
        }
    }
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }

    public interface CallBack{
        void showMessage(UserMessage message);
    }
}