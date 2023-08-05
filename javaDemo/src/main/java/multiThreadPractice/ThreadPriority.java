package multiThreadPractice;

public class ThreadPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getPriority());
        ThreadPriority threadPriority = new ThreadPriority();
        Thread t1 = new Thread(threadPriority);
        t1.setPriority(1);
        t1.start();
        Thread t2 = new Thread(threadPriority);
        t2.setPriority(3);
        t2.start();
        Thread t3 = new Thread(threadPriority);
        t3.setPriority(6);
        t3.start();
        Thread t4 = new Thread(threadPriority);
        t4.setPriority(7);
        t4.start();
        Thread t5 = new Thread(threadPriority);
        t5.setPriority(11);
        t5.start();
    }
}
