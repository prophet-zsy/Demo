package com.sunmi.testservicerequestpermission;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 验证无界面的Service是否可以请求权限
 * 验证结果：是可以的
 */

public class MyService extends Service {
    private static final String TAG = "MyService";
    private UsbManager mUsbManager;
    private UsbDevice device;
    private PendingIntent mPermissionIntent;
    private static final String ACTION_USB_PERMISSION = "com.HPRTSDKSample";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
                mPermissionIntent = PendingIntent.getBroadcast(MyService.this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
                Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
                while(deviceIterator.hasNext()){
                    device = deviceIterator.next();
                    int count = device.getInterfaceCount();
                    for (int i = 0; i < count; i++){
                        UsbInterface intf = device.getInterface(i);
                        if (intf.getInterfaceClass() == 7){
                            mUsbManager.requestPermission(device, mPermissionIntent);
                        }
                    }
                }
            }
        }).start();

        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }



}