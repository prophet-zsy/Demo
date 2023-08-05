package multiThreadPractice;

public class UnsafetyDrawMoney {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 100, "你");
        Drawing girlFriend = new Drawing(account, 50, "女朋友");
        you.start();
        girlFriend.start();
    }
}

class Account {
    public int rest;
    public String name;

    public Account(int rest, String name) {
        this.rest = rest;
        this.name = name;
    }
}

class Drawing extends Thread {

    private Account account;
    private int drawNum;
    private int nowMoney;

    public Drawing(Account account, int drawNum, String name) {
        super(name);
        this.account = account;
        this.drawNum = drawNum;
    }

    @Override
    public void run() {
//        synchronized (account) {
            if (account.rest - drawNum < 0) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.rest -= drawNum;
            nowMoney += drawNum;
            System.out.println(this.getName() + " 取到了 " + drawNum);
            System.out.println("账户里的钱为 " + account.rest);
            System.out.println(this.getName() + "手里的钱为 " + nowMoney);
//        }
    }
}
