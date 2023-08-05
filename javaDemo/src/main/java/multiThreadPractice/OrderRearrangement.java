package multiThreadPractice;

import java.util.HashSet;

/**
 * 《图灵学院 诸葛老师的高并发，讲的还行》
 *  并发编程三大特性：可见性、有序性、原子性
 *  这里演示有序性
 *   该代码
 *  正常情况下会有这三种情况[a = 0, b = 0, a = 0, b = 1, a = 1, b = 0]
 *  但，由于指令重排，a=1,b=1的情况也存在，即[a = 0, b = 0, a = 1, b = 1, a = 0, b = 1, a = 1, b = 0]
 *  jdk实现指令重排，定义了两种规范，如下：
 *  as if serial：不管怎么重排序，（单线程）程序的执行结果不能被改变 <这里jdk只保证单线程代码的重排不影响程序结果，不保证多线程重排后造成的影响>
 *  happens - before原则：包含了一堆，对发生顺序进行限制的原则，具体自行百度
 */


public class OrderRearrangement {
    private static int a, b, x, y;

    public static void main(String[] args) throws InterruptedException {
        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < 1000000; i ++) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = x;
                    y = 1;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = y;
                    x = 1;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            hashSet.add("a = " + a + ", b = " + b);
            System.out.println(hashSet);
        }
    }
}
