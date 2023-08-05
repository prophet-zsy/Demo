
public class DropFrame {

    private int item = 0;

    public static void main(String[] args) {
        System.out.println();
        new DropFrame().t4();
    }

    // dropframe并不支持将当前栈帧中修改过的其他对象（对象范围大于该该函数）的成员变量再修改回去，因此dropframe只关于对外界变量无修改的函数是安全的
    public void t1 () {
        item = 1;
        System.out.println("t1");
    }

    public void t2 () {
        item = 2;
        t1();
    }

    public void t3 () {
        item = 3;
        t2();
    }

    public void t4 () {
        item = 4;
        t3();
    }
}
