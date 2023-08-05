package JUnit;

import org.junit.Test;

public class Compute {

    /**
     * 计算从start到end的和
     * @param start
     * @param end
     * @return
     */
    public long computeSum(int start, int end) {
        if (start > end) throw new RuntimeException("start should be less than end");
        long res = 0;
        for (int i = start; i <= end; i++) {
            res += i;
        }
        return res;
    }

    /**
     * 计算从start到end的积
     * @param start
     * @param end
     * @return
     */
    public long computeMultiply(int start, int end) {
        if (start > end) throw new RuntimeException("start should be less than end");
        long res = 1;
        for (int i = start; i <= end; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long sum = new Compute().computeSum(1,100000000);
//        long multiply = new Compute().computeMultiply(1,10);
        System.out.println(sum);
//        System.out.println(multiply);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }
}
