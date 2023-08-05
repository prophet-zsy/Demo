package ClassloaderLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


// todo 写下 加载jar包的demo，不同 类加载器什么时候使用？

public class Main2 {

    public static void main(String[] args) throws InterruptedException {

        TestImpl.getInstance().test();
        System.out.println(TestImpl.getInstance());

        String className = "ClassloaderLearn.TestImpl";
        Class<?> cls = null;
        try {
            cls = Class.forName(className,false, Main2.class.getClassLoader());
            Method getInstance = cls.getDeclaredMethod("getInstance");
            Object o = getInstance.invoke(null);
            Field ins = cls.getDeclaredField("ins");
            ins.setAccessible(true);
            System.out.println(ins.get(o));

            Method test = cls.getDeclaredMethod("test");
            Object res = test.invoke(o);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


//        try {
//            className = "com.sunmi.bluetooth.SunmiBluetoothAdapterImpl";
//            String classPackage = "sunmi-services.jar";
//            cls = Class.forName(className,false, Main2.class.getClassLoader());
//            Constructor<?> constructor = cls.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            ISunmiBluetoothAdapter mSunmiBluetoothAdapter = (ISunmiBluetoothAdapter)constructor.newInstance();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }

        try {  // todo 这里的initialize 可能是指对象的初始化
            cls = Class.forName("ClassloaderLearn.AA",false, Main2.class.getClassLoader());
            Constructor<?> constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            AA aa = (AA)constructor.newInstance();
            System.out.println(aa.test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class AA {
    static int test = 1;
    static {
        test = 25;
    }
}
