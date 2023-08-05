package multiThreadPractice;

public class TenDown implements Runnable {
    @Override
    public void run() {
        int i = 10;
        while(true) {
            if (i < 1) break;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i--);
        }
    }

    public static void main(String[] args) {
        new Thread(new TenDown()).start();
    }
}
