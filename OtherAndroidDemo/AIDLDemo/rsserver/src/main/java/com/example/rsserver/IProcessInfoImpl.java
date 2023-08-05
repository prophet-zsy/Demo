package com.example.rsserver;

import android.os.RemoteException;
import android.util.Log;

import com.example.rsserver.IProcessInfo;

public class IProcessInfoImpl extends IProcessInfo.Stub {
    private static final String TAG = "IProcessInfoImpl";
    @Override
    public int getProcessId() throws RemoteException {
        int pid = android.os.Process.myPid();
        String tname = Thread.currentThread().getName();
        Log.d(TAG, "getProcessId: " + pid + "," + tname);
        return pid;
    }
}
