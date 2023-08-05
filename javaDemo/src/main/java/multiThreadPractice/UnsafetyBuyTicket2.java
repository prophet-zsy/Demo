package multiThreadPractice;

import java.util.concurrent.locks.ReentrantLock;

public class UnsafetyBuyTicket2 implements Runnable{

    private ReentrantLock reentrantLock = new ReentrantLock();
    private int ticketNum = 10;

    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {
                if (ticketNum <= 0) break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get " + ticketNum-- + " ticket");
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        UnsafetyBuyTicket2 unsafetyBuyTicket = new UnsafetyBuyTicket2();
        new Thread(unsafetyBuyTicket, "me").start();
        new Thread(unsafetyBuyTicket, "you").start();
        new Thread(unsafetyBuyTicket, "he").start();
    }
}
