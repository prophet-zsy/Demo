package multiThreadPractice;

public class ThreadDeamon {
    public static void main(String[] args) {
        Thread tGod = new Thread(new God());
        tGod.setDaemon(true);
        tGod.start();
        Thread tWe = new Thread(new We());
        tWe.start();
    }
}

class We implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i ++)
            System.out.println("multiThreadPractice.We are living...");
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true)
            System.out.println("multiThreadPractice.God is guarding us...");
    }
}
