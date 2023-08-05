package ClassloaderLearn;

public class TestImpl {

    private static final TestImpl ins = new TestImpl();
    public static TestImpl getInstance() {
        return ins;
    }

    public void test() {
        System.out.println("TestImpl::test");
    }
}
