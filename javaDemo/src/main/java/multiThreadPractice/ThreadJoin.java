package multiThreadPractice;

public class ThreadJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 200; i ++) {
            System.out.println("子线程运行" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadJoin());
        thread.start();
        for (int i = 0; i < 200; i ++) {
            if (i == 100) thread.join();
            System.out.println("主线程运行" + i);
        }
    }
}
