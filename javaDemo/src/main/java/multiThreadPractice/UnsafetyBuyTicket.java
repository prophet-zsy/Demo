package multiThreadPractice;

public class UnsafetyBuyTicket implements Runnable{

    private int ticketNum = 10;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticketNum <= 0) break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get " + ticketNum-- + " ticket");
            }
        }
    }

    public static void main(String[] args) {
        UnsafetyBuyTicket unsafetyBuyTicket = new UnsafetyBuyTicket();
        new Thread(unsafetyBuyTicket, "me").start();
        new Thread(unsafetyBuyTicket, "you").start();
        new Thread(unsafetyBuyTicket, "he").start();
    }
}
