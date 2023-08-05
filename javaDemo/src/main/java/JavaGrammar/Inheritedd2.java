package JavaGrammar;

public class Inheritedd2 {
    public static void main(String[] args) {
        Father son = new Son();
        son.setA(4);
        son.printA();   // 4

        Father son2 = new Son();
        son2.setA(6);
        son2.printA();  // 6

        son.printA();   // 4 证明子类继承了父类private修饰的内容，只是不可访问，可以通过父类的protected方法或public方法访问
    }
}


class Father {
    private int a = 2;
    private void show() {
        System.out.println("父类的private方法");
    }
    protected void setA(int aa) {
        a = aa;
    }
    public void printA() {
        System.out.println(a);
    }
}

class Son extends Father {

}
