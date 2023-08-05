package multiThreadPractice;

public class ThreadYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "stop");
    }

    public static void main(String[] args) {
        ThreadYield threadYield = new ThreadYield();
        new Thread(threadYield, "a").start();
        new Thread(threadYield, "b").start();
    }
}
