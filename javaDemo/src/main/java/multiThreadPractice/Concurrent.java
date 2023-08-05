package multiThreadPractice;

public class Concurrent implements Runnable {

    private int ticketNum = 10;


    public static void main(String[] args) {
        Concurrent ins = new Concurrent();
        new Thread(ins, "小明").start();
        new Thread(ins, "老师").start();
        new Thread(ins, "黄牛").start();
        new Thread(ins, "大明").start();
    }

    @Override
    public synchronized void run() {
        while (ticketNum > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum-- + "张票");
        }
    }
}

