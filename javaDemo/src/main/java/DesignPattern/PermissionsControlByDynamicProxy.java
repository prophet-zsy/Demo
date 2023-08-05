package DesignPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

interface Cla1 {
    void methodA1();
    void methodA2();
}

interface Cla2 {
    void methodB1();
    void methodB2();
}

interface Cla3 {
    void methodC1();
    void methodC2();
}
interface Cla123 extends Cla1, Cla2, Cla3 {

}
class Sub123 implements Cla123 {

    @Override
    public void methodA1() {
        System.out.println("sub123.methodA1");
    }

    @Override
    public void methodA2() {
        System.out.println("sub123.methodA2");
    }

    @Override
    public void methodB1() {
        System.out.println("sub123.methodB1");
    }

    @Override
    public void methodB2() {
        System.out.println("sub123.methodB2");
    }

    @Override
    public void methodC1() {
        System.out.println("sub123.methodC1");
    }

    @Override
    public void methodC2() {
        System.out.println("sub123.methodC2");
    }
}


class PermissionProxy implements InvocationHandler {
    private Sub123 sub123 = new Sub123();

    public Object getProxy(Class[] interfaces) {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(sub123, args);
        return null;
    }
}

class PermissionProxy2 implements InvocationHandler {
    private static Sub123 sub123 = new Sub123();

    private User user;

    public static class User {
        public Set<Class> permission;

        public User(Class[] permissions) {
            this.permission = new HashSet<>(Arrays.asList(permissions));
        }
    }

    private PermissionProxy2(User user) {
        this.user = user;
    }

    public static Cla123 getProxy(User user) {
        Cla123 proxy = (Cla123) Proxy.newProxyInstance(PermissionProxy2.class.getClassLoader(), Sub123.class.getInterfaces(), new PermissionProxy2(user));
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        if (user.permission.contains(declaringClass)) method.invoke(sub123, args);
        else throw new RuntimeException("you have no permission");
        return null;
    }
}


public class PermissionsControlByDynamicProxy {

    public static void main(String[] args) {
//        只能使用Cla1和Cla2接口中的方法

//        缺点每次使用都需要类型转换
        PermissionProxy permissionProxy = new PermissionProxy();
        Object proxy = permissionProxy.getProxy(new Class[]{Cla1.class, Cla2.class});
        ((Cla1)proxy).methodA1();
        ((Cla2)proxy).methodB1();
//        ((Cla3)proxy).methodC1();  // proxy 不能使用接口Cla3中的方法

//
        Cla123 proxy2 = PermissionProxy2.getProxy(new PermissionProxy2.User(new Class[]{Cla1.class, Cla3.class}));
        proxy2.methodA1();
        proxy2.methodC1();
//        proxy2.methodB1();  // proxy2 不能使用接口Cla2的方法
    }
}
