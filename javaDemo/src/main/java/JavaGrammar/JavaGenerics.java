package JavaGrammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaGenerics {

    public static void main(String[] args) {
        test1(); // 泛型不具有协变性
        test2(); // 泛型类与泛型方法
        test3(); // 泛型接口
        test4(); // 泛型擦除
//        test5(); // 泛型限定符、通配符（由于没法像数组那样通过协变性赋值， Cat[] b; Animal[] a = b;，因此引入泛型通配符？）<引用泛型类的对象时使用>
        test6(); // 泛型 多重上界、 尚未见有 多重下界       <定义泛型类或泛型方法时使用>
        test7(); // 数组与集合放入不同类型的元素
        test8();
    }

    private static void test1() {
        Object[] a = new Object[5];
        Integer[] b = new Integer[5];
        a = b;  // 数据具有协变性
        a[0] = 5;
        List<Object> aa = new ArrayList<>();
        List<Integer> bb = new ArrayList<>();
        aa.add(5);
//        aa = bb;  // 泛型不具备协变性  // 在java中的父子关系，对应到泛型中并不是父子关系；java中的继承并不会对应到泛型中
    }

    private static void test2() {
        new Ins<Integer>().meth(555);
        new Ins<Integer>().meth3(89);
        Ins.meth2("456");
    }

    private static void test3() {
        new Ins2().show("asdf");
        new Ins3<Integer>().show(11);
    }

    private static void test4() {
        Object object1 = new Ins<String>();
        Object object2 = new Ins<Integer>();
        Object object3 = new Ins3<Integer>();
        System.out.println(object1.getClass() == object2.getClass()); // 同一个类下的泛型在运行阶段会被擦除，所以泛型只存在于编码、编译阶段
        System.out.println(object1.getClass() == object3.getClass()); // 不同类下的泛型擦除后，在运行阶段依然是两个不同的类
    }

    private static void test5() {
        new Ins4<>();// Integer为Number的子类
//        new JavaGrammar.Ins4<String>();  // String 并非Number的子类，所以报错
//        泛型定义时，只能使用extends进行泛型类型的限制

//        在引用泛型对象时，可以不使用某一具体的数据类型，而使用通配符“？”来放宽其适用范围 （注意：不能直接创建一个通配符的对象）
//        ? 表示任意类型，不清楚类型的集合 自然只能读，不能写
//        List<Integer> list = new ArrayList<>();
//        list.add(56);
//        ArrayList<?> arrayList = (ArrayList<?>) list;  // 添加元素后强转为ArrayList<?>类型
        ArrayList<?> arrayList = new ArrayList<>();
//        arrayList.add(45);
        Object object = arrayList.get(0);  // 能取，但list中无元素
//        ? extends Integer  只能读，不能写，且可以将读取的元素安全的强转为Integer
        ArrayList<? extends Integer> arrayList1 = new ArrayList<>();
//        arrayList1.add(55);
        int res = arrayList1.get(0);
//        ? super Integer  只能写，不能读
        ArrayList<? super Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(55);
//        int res2 = arrayList2.get(0);
        Object res3 = (Object) arrayList2.get(0);  // 除非将读到的元素强转为Object，才可以读
//        读写限制总结：无下界，无法接收，则不能写；无上界，外界无法接收，则不能读
//        不能写：没有使用super，  无法提供承接类型的下界，list中存储的类型可能为无限低，则不能承诺被添加元素类型能被？覆盖，即不能写；
//        不能读：没有使用extends，无法提供承接类型的上界，list中存储的类型可能为无限高，则不能承诺获取的元素类型能被某一类型覆盖，即不能读；（所有类型都可以被Object类型覆盖，但取出的是Object类型，不能正常使用，也没有用）
    }

    private static void test6() {
        class Insss<T extends Ins2 & Inter<String>> {  // 多重上界，  上界中最多只能有一个类（且应在第一个位置上），其余为接口
            T t;
        }
    }

    private static void test7() { // 数组和集合均可以存储不同子类类型的元素，实际放入其中的是对象的引用
        ArrayList<Object> aaa = new ArrayList<>();
        aaa.add(45);
        aaa.add("36");
        System.out.println(aaa.get(0) + ", " + aaa.get(1));
        Object[] bbb = new Object[5];
        bbb[0] = 45;
        bbb[1] = "36";
        System.out.println(bbb[0] + ", " + bbb[1]);
    }

    private static void test8() {  // 验证 MyClass<Foo>与MyClass<Bar>会共享静态变量
        Ins4<Integer> ins4 = new Ins4<>();// Integer为Number的子类
        Ins4<Double> ins41 = new Ins4<>();// Double也为Number的子类
        System.out.println(ins4.staticVar);  // 通过实例访问静态变量
        ins4.staticVar = 30;
        System.out.println(ins41.staticVar); // 通过实例访问静态变量
    }
}
//        泛型类，针对类定义了参数化类型，可以在整个类中将其当作正常的类型使用，除了静态部分
class Ins<T> {
    public void meth(T param) {
        System.out.println(param);
    }
//    泛型类的普通方法也可以脱离当前类定义的泛型，自己定义自己的泛型进行使用
    public <M> void meth3(M param) {
        System.out.println(param);
    }
    //        类中的静态泛型方法，需要单独定义
    public static <W> void meth2(W param) {
        System.out.println(param);
    }
}

interface Inter<T> {  // 泛型接口
    void show(T name);
}
class Ins2 implements Inter<String> { // 实现接口的时候，对接口中的类型参数使用       具体类型     进行初始化
    @Override
    public void show(String name) {
        System.out.println(name);
    }
}
class Ins3<T> implements Inter<T> {   // 实现接口的时候，对接口中的类型参数使用   当前类的泛型参数   进行初始化
    @Override
    public void show(T name) {
        System.out.println(name);
    }
}

class Ins4<T extends Number> {  // 表示实例化Ins4时，指定的类型只能为Number或其子类
    public static int staticVar = 3;  // 泛型类传入不同类型参数，实例化后是共享该静态变量的；因此，给T传入不同的类型参数，从而实例化出来的对象本质上是同一个类（不同于C++）
    public void show(T name) {
        System.out.println(name);
    }
}


// note：这里没办法再继承泛型类型（因为这里的K 实际类型在运行时就已经被擦除了，编译期进行检查只能按照参数代号K进行检查，java泛型具体类型不存在于运行时<而父类子类的关系既在编译时需要又在运行时需要>，因此java中类不能继承泛型类型）
//class Ttest<K> extends K {
//
//}


