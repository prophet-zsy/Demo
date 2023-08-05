package multiThreadPractice;


/**
 * 【线程的基本操作】
 * http://www.itsoku.com/course/1/6
 */

public class ThreadState implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i ++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getState());
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadState());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.stop();
        while (thread.getState() != Thread.State.TERMINATED) {
            Thread.sleep(200);
            System.out.println(thread.getState());
        }
        System.out.println(thread.getState());

//        thread.start();   // thread run 结束后，不能再调用start，只能回收， 如果需要循环使用，需要自己写个looper不停接任务或使用线程池
    }
}
