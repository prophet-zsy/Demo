package multiThreadPractice;

import java.text.SimpleDateFormat;

public class PrintTime implements Runnable{
    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
            i ++;
        }
    }

    public static void main(String[] args) {
        new Thread(new PrintTime()).start();
    }
}
