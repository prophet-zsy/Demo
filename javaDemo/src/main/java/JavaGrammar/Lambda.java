package JavaGrammar;


public class Lambda {

    public static void main(String[] args) {
        Love impl = new Impl();
        impl.love(1, 2);

    }
}


class Impl implements Love {

    @Override
    public void love(int a, int b) {
        System.out.println("正在测试实现接口" + a + " " + b);
    }
}

interface Love {
    void love(int a, int b);
}




