package multiThreadPractice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class InvokThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Mythread1().start();
        new Thread(new Mythread2()).start();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Mythread3());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

class Mythread1 extends Thread {
    @Override
    public void run() {
        System.out.println("multiThreadPractice.Mythread1");
    }
}
class Mythread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("multiThreadPractice.Mythread2");
    }
}
class Mythread3 implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("multiThreadPractice.Mythread3");
        return 100;
    }
}