package multiThreadPractice;

import java.sql.Time;
import java.util.concurrent.atomic.AtomicInteger;






public class UnsafetyIncrement {
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }
    private static void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        A a = new A();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    a.incre();
                }
            }
        });
        th.start();
        for (int i = 0; i < 10000000; i++) {
            a.incre();
        }
        th.join();
        long end = System.currentTimeMillis();
        System.out.println(a.getValue());
        System.out.println(end - start + "ms");
    }

    private static void test2() throws InterruptedException {
        long start = System.currentTimeMillis();
        AA2 a = new AA2();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    a.incre();
                }
            }
        });
        th.start();
        for (int i = 0; i < 10000000; i++) {
            a.incre();
        }
        th.join();
        long end = System.currentTimeMillis();
        System.out.println(a.getValue());
        System.out.println(end - start + "ms");
    }
}


class A {
    private int value;
    A() {
        value = 0;
    }
    public int getValue() {
        return value;
    }
    // 这里加上synchronized，便不会再有同步问题，但时间从15ms延长到了1000ms
    public synchronized void incre() {
        value ++;
    }
}

class AA2 {
    private AtomicInteger value2;
    AA2() {
        value2 = new AtomicInteger(0);
    }
    public int getValue() {
        return value2.get();
    }
//    这里不需要加synchronized，因为AtomicInteger的操作本身就是原子性的，它通过while（true）+ CAS来实现的
//    CAS（无锁、自旋锁、乐观锁、轻量级锁），CAS操作在汇编层面加锁（缓存行锁/总线锁），保证了其操作的原子性
    public void incre() {
        value2.getAndIncrement();
    }
}

