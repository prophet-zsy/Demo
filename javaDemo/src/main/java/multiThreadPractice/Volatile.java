package multiThreadPractice;

/**
 *  volatile仅限于一写多读，如果多写，则需要上锁了
 *  并发编程三大特性：可见性、有序性、原子性
 *  这里演示可见性
 *
 *  每个线程有自己的工作内存， 线程工作内存 与 主存 之间需要进行同步；
 *  每次线程修改了工作内存的变量，必然同步到主存，但不加volatile，主存中变量的修改信息，其他线程是不可见的，因此其他线程针对该变量仍为其修改之前的认识
 *  volatile在底层通过总线嗅探机制来侦测对应变量是否有修改
 */

public class Volatile {
    //    这里不加 volatile 线程2修改的flag，线程1不一定可见
    static volatile Boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1启动");
                while (! flag) {

                }
                System.out.println("线程1结束");
            }
        }).start();
        Thread.sleep(2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2启动");
                flag = true;
                System.out.println("线程2结束");
            }
        }).start();
    }
}
