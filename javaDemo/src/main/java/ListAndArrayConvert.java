import java.util.ArrayList;
import java.util.Arrays;


// todo 将list array int[] Integer 之间的互转补全
public class ListAndArrayConvert {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(5);
        ints.add(5);
        ints.add(5);
        ints.add(5);
        Integer[] ts = ints.toArray(new Integer[0]);
        int[] ints1 = Arrays.stream(ts).mapToInt(Integer::valueOf).toArray();
        for (int str:ints1
        ) {
            System.out.println(str);
        }
    }
}
