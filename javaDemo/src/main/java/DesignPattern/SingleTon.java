package DesignPattern;

public class SingleTon {
    public static void main(String[] args) {
        SingleTon1 singleTon1 = SingleTon1.getInstance();
        SingleTon2 singleTon2 = SingleTon2.getInstance();
    }
}

// 懒汉式double check写法 (懒加载) (只有这种写法线程安全)
class SingleTon1 {
    private static volatile SingleTon1 singleTon1;  // 这里需要声明volatile
    private SingleTon1() {

    }
    public static SingleTon1 getInstance() {
        if (singleTon1 == null) {
            synchronized (SingleTon1.class) {
                if (singleTon1 == null) {
                    singleTon1 = new SingleTon1();
                }
            }
        }
        return singleTon1;
    }
}

// 饿汉式写法 (这种写法天然线程安全)
class SingleTon2 {
    private static final SingleTon2 singleTon2 = new SingleTon2();  // 因为单例全局仅初始化一次，所以这里可以加上final
    private SingleTon2() {

    }
    public static SingleTon2 getInstance() {
        return singleTon2;
    }
}


