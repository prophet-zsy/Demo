package com.sunmi.gradleflavor;

import android.util.Log;

/**
 * 通过switch分支选择执行不同风味的代码
 */
public class PayHelper {
    private static final String TAG = "PayHelper";
    public void pay() {
        switch (BuildConfig.FLAVOR) {
            case "huawei":
                huaweiPay();
                break;
            case "xiaomi":
                xiaomiPay();
                break;
        }
    }

    private void huaweiPay() {
        Log.d(TAG, "huaweiPay: 通过使用华为联运sdk提供的api实现支付共功能");

    }

    private void xiaomiPay() {
        Log.d(TAG, "xiaomiPay: 通过使用小米联运sdk提供的api实现支付共功能");
    }
}
