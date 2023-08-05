package DesignPattern;

import file.FileOutputStream_;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * https://www.jianshu.com/p/5dc416ea58a2
 * 每个动态生成的代理类都继承了 Proxy 类，因此只能针对接口 创建 代理类，不能针对类创建代理类
 * 重点看博客中 4. 源码分析
 *
 * 动态代理不止一种，以上时jdk自带的动态代理工具，其他还有cglib、ASM和bytebuddy等方式
 */


public class DynamicProxy {
    public static void main(String[] args) {
        Host host = new Host();
//        静态代理
        StaticProxy staticProxy = new StaticProxy(host);
        staticProxy.rent();
//        动态代理
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(host);
        Rent proxy = myInvocationHandler.getProxy();  //动态生成的代理
        printClassInfo(proxy.getClass());
        dumpClass("$proxy0", proxy.getClass());
        proxy.rent();  // 会调用MyInvocationHandler中复写的invoke方法，debug追踪下可证明
    }

    private static void dumpClass(String className, Class c) { // 可以将动态生成的代理类的字节码写入文件，然后查看该类的构成
        byte[] bts = ProxyGenerator.generateProxyClass(className, c.getInterfaces());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./" + className + ".class"));
            fileOutputStream.write(bts);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printClassInfo(Class c) {  // 可以打印查看类的一些基本信息
        System.out.println("============");
        System.out.println("当前类的名字:");
        System.out.println(c.getName());
        System.out.println("当前类的父类们:");
        Class superClass = c.getSuperclass();
        while (superClass != null) {
            System.out.println(superClass.getName());
            superClass = superClass.getSuperclass();
        }
        System.out.println("当前类的字段:");
        for (Field declaredField : c.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        System.out.println("当前类的方法:");
        for (Method declaredMethod : c.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("============");
    }
}

interface Rent {
    public void rent();
}

class Host implements Rent {

    @Override
    public void rent() {
        System.out.println("房东出租了房子");
    }
}

// 静态代理
class StaticProxy implements Rent {
    private Rent rent;

    public StaticProxy(Rent rent) {
        this.rent = rent;
    }

    @Override
    public void rent() {
        System.out.println("中介带看");
        rent.rent();
        System.out.println("收中介费，房子租出去了");
    }
}

// 动态代理
class MyInvocationHandler implements InvocationHandler {
    private Rent rent;

    public MyInvocationHandler(Rent rent) {
        this.rent = rent;
    }

//    动态生成代理类
    public Rent getProxy() {
        return (Rent) Proxy.newProxyInstance(this.getClass().getClassLoader(), this.rent.getClass().getInterfaces(), this);
    }

//    动态代理类调用接口方法时，实际所调用的函数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("中介带看");
        method.invoke(rent, args);
        System.out.println("收中介费，房子租出去了");
        return null;
    }
}

