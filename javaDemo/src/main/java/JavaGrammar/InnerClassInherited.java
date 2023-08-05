package JavaGrammar;

/**
 *       这里匿名内部类和局部内部类都是在局部域中，前者无名(初始化时直接定义)，后者有名(先定义，后使用)
 * 总结： 子类继承父类，就是继承了父类的域，包括 静态的和非静态的 属性、方法、内部类
 *       子类调用，子类有的先用子类的，子类没有的去父类中找
 */

class Outter {
    static class StaticInner {
        public static void staticTest() {
            System.out.println("StaticInner => staticTest");
        }
        public void test() {

        }
    }
    static class StaticSubInner extends StaticInner {
        public static void staticTest() {  //todo 静态方法和类绑定，父类的静态方法不能在子类中重写
            System.out.println("StaticSubInner => staticTest");
        }
        public void test() {

        }
    }
    class Inner {
        public void test(){

        }
    }
    public static void staticTest() {
        StaticInner staticInner = new StaticInner() {
            @Override
            public void test() {
                super.test();
            }
        };
//        Inner inner = new Inner() {  // todo 静态域的匿名内部类要求其父类也是静态类
//            @Override
//            public void test() {
//
//            }
//        };
    }
    public void test() {
        StaticInner staticInner = new StaticInner() {  // 非静态域的匿名内部类的父类可以是静态类
            @Override
            public void test() {
                super.test();
            }
        };
        Inner inner = new Inner() {  // 非静态域的匿名内部类的父类也可以是非静态类
            @Override
            public void test() {

            }
        };
    }
}

class SubOutter extends Outter{
    public void subTest(){
        new StaticInner();          //  父类中的内部类可以在子类中直接访问
        new Inner();                //  父类中的内部类可以在子类中直接访问
        StaticInner.staticTest();
        StaticSubInner.staticTest();//  todo 父类中的静态方法可以通过子类类名访问到，如果子类有同名静态方法则调用子类的方法
    }
}

public class InnerClassInherited {
    public static void main(String[] args) {
        new SubOutter.StaticInner();  // 其他类中，父类中定义的静态内部类也可以通过子类域访问到，然后初始化
        new SubOutter().new Inner();  // 其他类中，子类实例可以访问到父类中定义的内部类
        SubOutter.staticTest();       // 其他类中，父类的静态方法可以通过子类类名访问到
        new SubOutter().test();       // 其他类中，父类的非静态方法可以通过子类实例访问到
        new SubOutter().subTest();
    }
}

