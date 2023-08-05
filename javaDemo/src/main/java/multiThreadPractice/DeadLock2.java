package multiThreadPractice;

public class DeadLock2 {
    public static void main(String[] args) {
        new Thread(()->new A1()).start();
        System.out.println("A1正在创建");
        new Thread(()->new A2()).start();
        System.out.println("A2正在创建");
    }
}


class A1 {
    static public final Integer s1 = new Integer(0);
    public A1() {
        synchronized (A1.s1) {
            System.out.println("A1获得A1.s1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A2.s2) {
                System.out.println("A1获得A2.s2");
            }
        }
    }
}

class A2 {
//    要同步的对象，不能为基础数据类型！！！
    static public final Integer s2 = new Integer(0);
    public A2() {
        synchronized (A2.s2) {
            System.out.println("A2获得A2.s2");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A1.s1) {
                System.out.println("A2获得A1.s1");
            }
        }
    }
}