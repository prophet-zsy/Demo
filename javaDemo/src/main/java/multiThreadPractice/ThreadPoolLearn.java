package multiThreadPractice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolLearn {
    public static void main(String[] args) {
//        线程池工厂方法
        ExecutorService executorService0 = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newScheduledThreadPool(10);
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        ExecutorService executorService4 = Executors.newFixedThreadPool(10);

        executorService3.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub thread");
            }
        });
        System.out.println("main thread");
        executorService3.shutdown();  // 不再接收新任务
//        executorService.shutdownNow();  // 立刻停止运行任务及接收任务，处理好收尾工作
    }
}
