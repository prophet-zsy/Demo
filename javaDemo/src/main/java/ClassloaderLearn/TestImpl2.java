package ClassloaderLearn;

public class TestImpl2 {

    private static final TestImpl2 ins = new TestImpl2();
    public static TestImpl2 getInstance() {
        return ins;
    }

    public void test2() {
        System.out.println("TestImpl2::test2");
    }
}
