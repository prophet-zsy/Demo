package file;

import java.io.Serializable;

public class Dog implements Serializable {
    String name;
    int age;

//   序列化对象时，默认将里面的所有属性全都序列化，但除了static和transient修饰的成员
    private static String nation;
    private transient String color;

//    序列化对象时，要求里面属性的类型也需要实现序列化接口
    private Master master;

//    serialVersionUID 序列化的版本号，可以提高兼容性（使用该常量标识对应的类，从而避免修改类中属性时造成编译阶段不再识得此类而造成需要重新编译）
    private static final long serialVersionUID = 1L;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nation='" + nation + '\'' +
                ", color='" + color + '\'' +
                ", master=" + master +
                '}';
    }
}

class Master implements Serializable{

}
