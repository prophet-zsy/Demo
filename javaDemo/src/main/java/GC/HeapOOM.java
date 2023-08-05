package GC;

import java.util.ArrayList;
import java.util.List;


//  《深入理解Java虚拟机》 代码清单2-3 Java堆内存溢出尝试
//   VM Args： -Xms1m -Xmx1m -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {

    static class OOMObject{
//        int[] tem = new int[1024 * 1024];
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
