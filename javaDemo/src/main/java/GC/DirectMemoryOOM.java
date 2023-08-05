package GC;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

//  《深入理解Java虚拟机》 代码清单2-10 使用unsafe分配本机内存
//   VM Args： -Xmx20m -XX:MaxDirectMemorySize=10m
//   mac版java18上运行该程序会造成死机，慎重！！！
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
