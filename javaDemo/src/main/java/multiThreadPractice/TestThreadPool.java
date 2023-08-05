package multiThreadPractice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool implements Runnable, Callable<String> {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new TestThreadPool());
        executorService.execute(new TestThreadPool());
        executorService.execute(new TestThreadPool());
        executorService.execute(new TestThreadPool());
        executorService.submit((Callable) new TestThreadPool());
        executorService.shutdown();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "调用成功";
    }
}
