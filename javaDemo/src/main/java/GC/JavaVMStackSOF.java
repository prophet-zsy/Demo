package GC;


//  《深入理解Java虚拟机》 代码清单2-4 虚拟机栈和本地方法栈测试
//   VM Args： -Xss28k
public class JavaVMStackSOF {

    private int stackLength = 1;
    public void stackRecur() {
        stackLength ++;
        stackRecur();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackRecur();
        }catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }

}
