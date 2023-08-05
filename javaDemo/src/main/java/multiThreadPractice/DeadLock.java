package multiThreadPractice;

public class DeadLock {

    public static void main(String[] args) {
        Use u1 = new Use(0, "灰姑娘");
        Use u2 = new Use(1, "白雪公主");

        u1.start();
        u2.start();
    }
}

class AAA {}
class BBB {}

class Use extends Thread {

    static AAA aaa = new AAA();
    static BBB bbb = new BBB();
    private int choice;

    public Use(int choice, String name) {
        super(name);
        this.choice = choice;
    }

    @Override
    public void run() {
        if (choice == 0) {
            synchronized (aaa) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "获得了aaa");
                synchronized (bbb) {
                    System.out.println(this.getName() + "获得了bbb");
                }
            }
        } else {
            synchronized (bbb) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "获得了bbb");
                synchronized (aaa) {
                    System.out.println(this.getName() + "获得了aaa");
                }
            }
        }
    }
}
