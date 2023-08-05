package multiThreadPractice;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnsafetyArrayList {

    private static ArrayList<String> arr = new ArrayList<>();
    private static ArrayList<String> arr2 = new ArrayList<>();
    private static CopyOnWriteArrayList<String> arr3 = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Ensurer ensurer = new Ensurer();
        for (int i = 0; i < 10000; i ++) {
            Thread t = new Thread(()->{arr.add(Thread.currentThread().getName());});
            t.start();
            ensurer.add(t);
        }
        ensurer.join();
        System.out.println(arr.size());

//        multiThreadPractice.Ensurer ensurer = new multiThreadPractice.Ensurer();
//        for (int i = 0; i < 10000; i ++) {
//            Thread t = new Thread(()-> {
//                synchronized(arr2){
//                    arr2.add(Thread.currentThread().getName());
//                }
//            });
//            t.start();
//            ensurer.add(t);
//        }
//        ensurer.join();
//        System.out.println(arr2.size());

//        multiThreadPractice.Ensurer ensurer = new multiThreadPractice.Ensurer();
//        for (int i = 0; i < 10000; i++) {
//            Thread t = new Thread(()-> {
//                arr3.add(Thread.currentThread().getName());
//            });
//            t.start();
//            ensurer.add(t);
//        }
//        ensurer.join();
//        System.out.println(arr3.size());
    }

}

/**
 * 确保所有线程都执行完毕
 */
class Ensurer {
    ArrayList<Thread> tList;

    public Ensurer() {
        this.tList = new ArrayList<>();
    }
    public void add(Thread thread) {
        tList.add(thread);
    }
    public void join() throws InterruptedException {
        for (Thread thread : tList) {
            thread.join();
        }
    }
}

