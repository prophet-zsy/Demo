package multiThreadPractice;

public class TestStopThread implements Runnable {

    private boolean flag = true;

    public void stop() {
        this.flag = false;
        System.out.println("线程该停止了");
    }

    public static void main(String[] args) {
        TestStopThread testStopThread = new TestStopThread();
        new Thread(testStopThread).start();
        for (int i = 0; i < 1000; i ++) {
            System.out.println("主线程在运行" + i);
            if (i == 900) testStopThread.stop();
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("线程运行中：" + i++);
        }
    }
}
