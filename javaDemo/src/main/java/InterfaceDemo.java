public class InterfaceDemo {

    interface Iface1 {
        void test();
    }
    interface Iface2 {
        void test();
    }

//     同一个类实现了两个不同的接口，这两个接口中包含相同的方法，并不会产生冲突，因为实现只有一个
    static class FaceImpl implements Iface1, Iface2 {

        @Override
        public void test() {
            System.out.println("FaceImpl::test()");
        }
    }

    public static void main(String[] args) {
        Iface1 face = new FaceImpl();
        face.test();
        Iface2 face2 = new FaceImpl();
        face2.test();
    }
}
