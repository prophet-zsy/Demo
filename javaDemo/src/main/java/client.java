//public class client {
//    private int number = 0;
//
//    private void read() {
//        System.out.println("number = "+ number);
//    }
//
//    private synchronized void write(int change) throws InterruptedException {
//        int res = number + change;
//        System.out.println(change);
//        Thread.sleep(1);
//        number = res;
//    }
//
//    public void test() throws InterruptedException {
//        // 开启一个线程加 10000 次
//        new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                try {
//                    write(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("增加 10000 次已完成");
//        }).start();
//
//        // 开启一个线程减 10000 次
//        new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                try {
//                    write(-1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("减少 10000 次已完成");
//        }).start();
//
//        // 睡眠一秒保证线程执行完成
//        Thread.sleep(100000);
//        // 读取结果
//        read();
//    }
//}