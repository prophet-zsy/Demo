package com.example.aidllearn;

import android.app.Service;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyService extends Service implements Parcelable {
    private static final String TAG = "MyService";

    public MyService() {
    }

    protected MyService(Parcel in) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
//        return new MyBinder();   // 不使用aidl
        return new IMyAidlInterface.Stub() {  // 使用aidl
            public MyService getService() throws RemoteException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                throw new RemoteException("sadfsadf");
//                killCurProcessAfter5seconds();  // 5s后杀掉:s进程，MainActivity的进程依然在，客户端注册的binderDied方法会被调用
                Log.d(TAG, "getService: " + Thread.currentThread().getName());  // 打印当前执行的线程名
                return MyService.this;
            }

            @Override
            public Map<String, String> queryMessages() throws RemoteException {
                return null;
            }

            @Override
            public void insertCallLogs(List<ContentProviderOperation> ops) {
                ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                for (int i = 0; i < ops.size(); i++) {
                    arrayList.add(ops.get(i));
                }
            }
        };
    }

    class MyBinder extends Binder {
        public Service getService() {
            return MyService.this;
        }
    }


    public static final Creator<MyService> CREATOR = new Creator<MyService>() {
        @Override
        public MyService createFromParcel(Parcel in) {
            return new MyService(in);
        }

        @Override
        public MyService[] newArray(int size) {
            return new MyService[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    private void killCurProcessAfter5seconds() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);  // 退出当前进程
            }
        }.start();
    }
}
