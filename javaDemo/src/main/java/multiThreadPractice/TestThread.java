package multiThreadPractice;


/***
 * Thread.sleep会让出cpu,但不会释放锁
 * Object.wait也会让出cpu,但会释放锁
 */

public class TestThread {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new TestThread();

    }

    public String a = "test";

    public TestThread() {
        Thread thread1 = new WaitThread();
        Thread thread2 = new WaitThread();
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread thread3 = new NotifyThread();
        thread3.start();

    }

    class WaitThread extends Thread {
        public void run() {
            synchronized (a) {
                try {
                    System.out.println("before wait : " + Thread.currentThread() + ":" + a);
                    a.wait();
                    System.out.println("after wait : " + Thread.currentThread() + ":" + a);
                    System.out.println("before sleep : " + Thread.currentThread() + ":" + a);
                    sleep(1000);
                    System.out.println("after sleep : " + Thread.currentThread() + ":" + a);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    class NotifyThread extends Thread {
        public void run() {
            synchronized (a) {
                System.out.println("before notify : " + Thread.currentThread() + ":" + a);
                a.notifyAll();
                System.out.println("after notify : " + Thread.currentThread() + ":" + a);
            }
        }
    }
}

