package JavaGrammar;

class Fatherr {

}

/**
 *       这里匿名内部类和局部内部类都是在局部域中，前者无名(初始化时直接定义)，后者有名(先定义，后使用)
 * 总结： 非静态内部类（成员内部类、匿名内部类）会隐式持有外部类的引用，所以他们可以访问外部的所有属性和方法（包括私有）
 *       静态内部类（静态成员内部类、静态域的匿名内部类）不会隐式持有外部类的引用，所以他们只能访问外部类的静态属性和方法（包括私有）
 *       外部类没有静态之说，因为它不依赖于其他任何实例，本身就算是静态的，所以java规定外部类不能用static修饰
 */

public class InnerClass extends Fatherr {

    private static int aInner = 0;
    private int bInner = 0;

    static class StaticInClass {  //todo 静态（成员）内部类不持有外部类的引用
        public void test () {
            System.out.println(this.getClass().getName());
            System.out.println(super.getClass().getName());
            aInner = 5;  // 静态内部类访问外部类静态成员变量
//            bInner = 5;  // 静态内部类访问外部类成员变量
        }
    }

    private class InClass {  //todo 成员内部类持有外部类的引用
        public void test () {
            System.out.println(this.getClass().getName());
            System.out.println(super.getClass().getName());
            aInner = 5;  // 内部类访问外部类静态成员变量
            bInner = 5;  // 内部类访问外部类成员变量
        }
    }

    public static void staticOutTest () {
        System.out.println(InnerClass.class.getName());
        System.out.println(InnerClass.class.getName());
//        局部内部类
        final int aIn = 0;
        final int[] bIn = {0};
        class SubStaticInClass extends StaticInClass{
            @Override
            public void test() {
                super.test();
            }
        }
        StaticInClass staticInClass0 = new SubStaticInClass();  // 局部内部类（分为有名和匿名）
        StaticInClass staticInClass = new StaticInClass() { // 这是InClass的子类，不是InClass类的实例；  //todo 静态域的匿名内部类不持有外部类的引用
            @Override
            public void test() {
                super.test();
                aInner = 5;  // 静态局部内部类访问外部类静态成员变量
//                bInner = 5;  // 静态局部内部类不能访问外部类成员变量
//                aIn = 5;   // 静态局部内部类访问对应方法中的局部变量，只能将局部变量声明为final
                bIn[0] = 5;  // 静态局部内部类如需对方法中的局部变量做出修改，可以将其内容包裹在对象中
            }
        };
//        InClass inClass = new InClass(){  // todo 静态域的匿名内部类要求其父类也是静态类
//            @Override
//            public void test() {
//
//            }
//        };
    }

    public void outTest () {
        System.out.println(this.getClass().getName());
//        getClass方法获取的是上下文对应的类，而不是跟调用实例一致
        System.out.println(super.getClass().getName());
//        局部内部类
        final int aIn = 0;
        final int[] bIn = {0};
        StaticInClass staticInClass = new StaticInClass(){  // todo 非静态域的匿名内部类的父类可以是静态类
            @Override
            public void test() {
                super.test();
            }
        };
        InClass inClass = new InClass() { // 这是InClass的子类，不是InClass类的实例；  //todo 匿名内部类持有外部类的引用
            @Override
            public void test() {
                super.test();
                aInner = 5;  // 局部内部类访问外部类静态成员变量
                bInner = 5;  // 局部内部类访问外部类成员变量
//                aIn = 5;   // 局部内部类访问对应方法中的局部变量，只能将局部变量声明为final
                bIn[0] = 5;  // 局部内部类如需对方法中的局部变量做出修改，可以将其内容包裹在对象中
            }
        };
    }

    public static void main(String[] args) {
        StaticInClass staticInClass = new StaticInClass();  // 静态内部类的初始化
        staticInClass.test();
        InClass inClass = new InnerClass().new InClass();  // 成员内部类的初始化依赖于外部类的初始化
        inClass.test();
        InnerClass.staticOutTest(); //  静态域的匿名内部类的使用
        new InnerClass().outTest();  //       匿名内部类的使用
    }

    public InClass get() {
        return new InClass();
    }
}

/**
 * 内部类的访问修饰符
 * （如果类的访问修饰比字段、方法的访问修饰更严格一点，那么会采用严格的）
 */
class TEST {
    public void test() {
//        私有的内部类的公有方法在外部类外面不能使用，会受到类 私有属性的限制
//        new InnerClass().get().test();  // 私有属性的内部类在外部类中实例化后通过外部类的公有方法获得后，再调用该实例的公有方法，是不行的
    }
}
