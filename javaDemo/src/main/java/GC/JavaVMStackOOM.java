package GC;


//  《深入理解Java虚拟机》 代码清单2-6 创建线程导致内存溢出异常
//   VM Args： -Xss2M
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackOOMByThread() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackOOMByThread();
    }
}
