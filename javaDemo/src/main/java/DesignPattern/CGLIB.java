package DesignPattern;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 实现的动态代理
 * 被代理的类 可以不实现接口， 直接生成代理子类
 */

class Dao {
    public void insert() {
        System.out.println("insert");
    }
    public void delete() {
        System.out.println("delete");
    }
}

class Proxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after invoke");
        return null;
    }
}


public class CGLIB {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(new Proxy());
        Dao dao =  (Dao) enhancer.create();
        dao.insert();
        dao.delete();
    }
}
