package Algorithm;

import com.alibaba.druid.sql.ast.statement.SQLUniqueConstraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class practiseSort {

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            array.add(random.nextInt(100));
        }
        System.out.println(array);
        System.out.println(quickSort(array));
    }

    public static <T extends Comparable> List<T> quickSort(List<T> array) {
        int len = array.size();
        partition(array, 0, len - 1);
        return array;
    }

    private static  <T extends Comparable> void partition(List<T> array, int l, int r) {
        if (l >= r) return;
        T partItem = array.get(l);
        int i = l, j = r;
        while (i < j) {
            while (j > i && array.get(j).compareTo(partItem) >= 0) j--;
            if (j > i && array.get(j).compareTo(partItem) < 0) array.set(i++, array.get(j));
            while (j > i && array.get(i).compareTo(partItem) < 0) i++;
            if (j > i && array.get(i).compareTo(partItem) >= 0) array.set(j--, array.get(i));
        }
        array.set(i, (T) partItem);
//        System.out.print(i + "," + j);
//        System.out.println(array);
        partition(array, l, i - 1);
        partition(array, i + 1, r);
    }


}
