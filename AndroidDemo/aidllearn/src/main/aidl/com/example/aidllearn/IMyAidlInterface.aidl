// IMyAidlInterface.aidl
package com.example.aidllearn;

import android.content.ContentProviderOperation;
//import java.util.ArrayList;
import java.util.List;
// Declare any non-default types here with import statements
import com.example.aidllearn.MyService;

//    todo aidl为何没有代码提示

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    MyService getService();  // 默认同步调用，加了oneway 修饰才是异步，加了oneway后，返回值只能是void，不可以带有out或inout的参数
    Map<String, String> queryMessages();
    void insertCallLogs(in List<ContentProviderOperation> ops);
}
