package multiThreadPractice;

public class Proxy implements Marrying {

    private You you;

    Proxy(You you) {
        this.you = you;
    }

    @Override
    public void marry() {
        System.out.println("婚庆公司准备婚礼ing");
        this.you.marry();
        System.out.println("婚庆公司处理善后事宜");
    }
}

interface Marrying {
    void marry();
}

class You implements Marrying{
    @Override
    public void marry() {
        System.out.println("我结婚啦");
    }

    public static void main(String[] args) {
        new Proxy(new You()).marry();
        new Thread(()-> System.out.println("asdfsa")).start();
    }
}


