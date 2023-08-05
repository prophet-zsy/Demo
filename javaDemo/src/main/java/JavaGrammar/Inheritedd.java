package JavaGrammar;

class Third {

}
class Fath extends Third {
    public void callAdd() {
        add();  // 这里看调用的实例是父类，还是子类，也是多态的一种写法
    }

    public void add() {
        System.out.println("JavaGrammar.Fath add...");
    }
}
class Sonn extends Fath {
    @Override
    public void add() {
        System.out.println("JavaGrammar.Sonn add...");
    }
}


public class Inheritedd {
    public static void main(String[] args) {
        Fath fath = new Fath();
        Fath fath2 = new Sonn();
        fath.callAdd(); // 输出"JavaGrammar.Fath add..."
        fath2.callAdd(); // 输出"JavaGrammar.Sonn add..."
//        todo 上述也是多态，调用父类还是子类方法，根据实例来
        Sonn sonn = new Sonn();
//        JavaGrammar.Sonn tem1 = (JavaGrammar.Sonn) fath;   // 实例类型大于转向的类型，转型会失败（即引用类型应大于等于实例类型）
        Sonn tem2 = (Sonn) fath2;
        Third third = fath;
        Fath tem3 = (Fath) sonn;
    }
}
