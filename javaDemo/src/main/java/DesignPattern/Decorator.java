package DesignPattern;

public class Decorator {
    public static void main(String[] args) {
        Person person = new Person();
        HatDecorator hatDecorator = new HatDecorator();
        CoatDecorator coatDecorator = new CoatDecorator();

        hatDecorator.decorate(person);
        coatDecorator.decorate(hatDecorator);

        coatDecorator.show();
//        装饰模式本质是同类对象，组装成 对象持有链
    }
}


class Person {  // 如果有多种类型的Person，也可以再定义一个抽象接口，然后让对应类型的Person实现它
    public void show() {
        System.out.println("装饰的小强");
    }
}

class BaseDecorator extends Person {
    protected Person person;
    public void show() {
        if (person != null)
            person.show();
    }
}

class HatDecorator extends BaseDecorator {
    public void decorate(Person person) {
        this.person = person;
    }
    public void show() {
        System.out.println("戴帽子");
        super.show();
    }
}

class CoatDecorator extends BaseDecorator {
    public void decorate(Person person) {
        this.person = person;
    }
    public void show() {
        System.out.println("穿大衣");
        super.show();
    }
}
