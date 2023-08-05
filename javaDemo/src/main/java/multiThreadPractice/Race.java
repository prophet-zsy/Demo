package multiThreadPractice;

public class Race implements Runnable {

    private String winner;

    private boolean gameOver(int i) {
        if (winner != null) return true;
        if (i == 100) {
            winner = Thread.currentThread().getName();
            System.out.println(winner + "赢了");
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i ++) {
            if(gameOver(i)) break;
            System.out.println(Thread.currentThread().getName() + "跑到" + i + "步");
        }
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
