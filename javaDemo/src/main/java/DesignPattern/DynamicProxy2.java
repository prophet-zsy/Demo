package DesignPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface A {
    void methodA1();
    void methodA2();
}

interface B {
    void methodB1();
    void methodB2();
}

interface C {
    void methodC1();
    void methodC2();
}
interface ABCI extends A, B, C {

}

class ABC implements ABCI {

    @Override
    public void methodA1() {
        System.out.println("ABC.methodA1");
    }

    @Override
    public void methodA2() {
        System.out.println("ABC.methodA2");
    }

    @Override
    public void methodB1() {
        System.out.println("ABC.methodB1");
    }

    @Override
    public void methodB2() {
        System.out.println("ABC.methodB2");
    }

    @Override
    public void methodC1() {
        System.out.println("ABC.methodC1");
    }

    @Override
    public void methodC2() {
        System.out.println("ABC.methodC2");
    }
}

interface D extends A, B {
//    void methodD1();
//    void methodD2();
}

interface E extends B, C {
//    void methodE1();
//    void methodE2();
}

class InvocationHandlerImpl implements InvocationHandler {
    private ABC abc = new ABC();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getDeclaringClass());
        method.invoke(abc, args);
        return null;
    }
}

public class DynamicProxy2 {

    public static void main(String[] args) {
        InvocationHandlerImpl handler = new InvocationHandlerImpl();
        /**
         *  接收代理 的类型需要为 被代理者 继承树上的元素，不包含 被代理者本身
         */

//        ABCI proxy0 = (ABCI) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{A.class, B.class, C.class}, handler);  // 不能转换为ABCI类型

        A proxy11 = (A) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{A.class}, handler);  //  只代理ABC类从A接口继承的功能
        proxy11.methodA2();

        A proxy1 = (A) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{A.class, B.class, C.class}, handler);  // 可以转换为A类型、B类型或C类型
        proxy1.methodA1();
        ((B) proxy1).methodB1();

        ABCI proxy2 = (ABCI) Proxy.newProxyInstance(handler.getClass().getClassLoader(), ABC.class.getInterfaces(), handler);  // ABCI 为ABC继承树上的元素，所以可以转换
        proxy2.methodA1();
        proxy2.methodB1();
        proxy2.methodC1();
    }
}
