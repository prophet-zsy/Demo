package multiThreadPractice;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * threadlocal学习
 * http://www.threadlocal.cn/#threadlocal
 *
 * 每个线程都有自己单独的ThreadLocalMap，ThreadLocal和ThreadLocalMap是多对多的关系
 * ThreadLocalMap的key是ThreadLocal(弱引用)，value是ThreadLocal对应的变量(强引用)
 *
 * ThreadLocalMap的key和value如果都是强引用，假如ThreadLocal的使用周期短于对应Thread的生命周期，
 * ThreadLocal用完了不进行remove (rm key and value)，key和value会一直保留到Thread回收，即泄漏
 *
 * ThreadLocalMap的key如果是弱引用，value是强引用，假如ThreadLocal的强引用周期短于对应Thread的生命周期，
 * ThreadLocal强引用回收了，key就可以回收(如果此时ThreadLocal还需要使用，便会造成功能上的bug)，回收之后便无法再手动remove，value会一直保留到Thread回收，即泄漏
 */

public class ThreadLocalUse {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(Thread.currentThread().getName() + "：这是主线程");
        System.out.println(threadLocal.get());
        threadLocal.remove();  // 不用了及时清除当前线程中的变量备份
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName() + "：这是子线程1");
                System.out.println(threadLocal.get());
                threadLocal.remove();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName() + "：这是子线程2");
                System.out.println(threadLocal.get());
                threadLocal.remove();
            }
        }).start();
    }
}
