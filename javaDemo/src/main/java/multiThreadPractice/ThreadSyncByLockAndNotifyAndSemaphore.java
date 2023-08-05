package multiThreadPractice;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *   实现 两个线程 交替打印不同内容： 一个打印 1-52 数字，一个打印 A - Z 字母 ， 要求按照这样的顺序：每输出两个数字 就输出一个字母
 *   三种不同的实现方式：  通过 Object的wait、notify方法 实现， 通过 JUC的Lock、Condition 实现， 通过 JUC的Semaphore 实现
 *
 *   【使用Lock，wait/notify，Semaphore三种方式实现多线程通信】https://blog.csdn.net/chjttony/article/details/12434087
 */


public class ThreadSyncByLockAndNotifyAndSemaphore {

    public static void main(String[] args) {
        new ThreadSyncByNotify().run();
        new ThreadSyncByLock().run();
        new ThreadSyncBySemaphore().run();
    }
}

/**
 * 使用 Object 作为同步对象， 使用notify、wait方法 结合synchronized
 */
class ThreadSyncByNotify {

    private static boolean shouldA = true;
    private static final Object syncA = new Object();
    private static final Object syncB = new Object();

    private static class RunnableA implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 52; i++) {
                synchronized (syncA) {
                    while (! shouldA) {
                        try {
                            syncA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(i);
                if ((i & 1) == 0) {
                    synchronized (syncB) {
                        shouldA = false;
                        syncB.notifyAll();
                    }
                }
            }
        }
    }
    private static class RunnableB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 26; i++) {
                synchronized (syncB) {
                    while (shouldA) {
                        try {
                            syncB.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println((char) ('A' + i));
                synchronized (syncA) {
                    shouldA = true;
                    syncA.notifyAll();
                }
            }
        }
    }

    public void run () {
        System.out.println("ThreadSyncByNotify  log :");
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new RunnableA());
        service.execute(new RunnableB());
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 *  使用JUC中的 Condition 作为同步工具， 结合 Lock
 */
class ThreadSyncByLock{
    private static boolean shouldA = true;
    private static final Lock lockA = new ReentrantLock();
    private static final Condition syncA = lockA.newCondition();
    private static final Lock lockB = new ReentrantLock();
    private static final Condition syncB = lockB.newCondition();

    private static class RunnableA implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 52; i++) {
                lockA.lock();
                while (! shouldA) {
                    try {
                        syncA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockA.unlock();
                System.out.println(i);
                if ((i & 1) == 0) {
                    lockB.lock();
                    shouldA = false;
                    syncB.signalAll();
                    lockB.unlock();
                }
            }
        }
    }
    private static class RunnableB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 26; i++) {
                lockB.lock();
                while (shouldA) {
                    try {
                        syncB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockB.unlock();
                System.out.println((char) ('A' + i));
                lockA.lock();
                shouldA = true;
                syncA.signalAll();
                lockA.unlock();
            }
        }
    }

    public void run () {
        System.out.println("ThreadSyncByLock  log :");
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ThreadSyncByLock.RunnableA());
        service.execute(new ThreadSyncByLock.RunnableB());
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 *  使用JUC中的 Semaphore 作为同步工具， Semaphore的acquire和release本身具备原子性 不需要单独上锁
 */

class ThreadSyncBySemaphore {

    private static Semaphore semaphoreA = new Semaphore(2);
    private static Semaphore semaphoreB = new Semaphore(0);

    private static class RunnableA implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 52; i++) {
                try {
                    semaphoreA.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                if ((i & 1) == 0) {
                    semaphoreB.release(1);
                }
            }
        }
    }
    private static class RunnableB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 26; i++) {
                try {
                    semaphoreB.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println((char) ('A' + i));
                semaphoreA.release(2);
            }
        }
    }

    public void run () {
        System.out.println("ThreadSyncBySemaphore  log :");
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ThreadSyncBySemaphore.RunnableA());
        service.execute(new ThreadSyncBySemaphore.RunnableB());
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





