package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(10);
        }
        printArray(array);
        printArray(insertSort(Arrays.copyOf(array, array.length)));
//        printArray(selectSort(Arrays.copyOf(array, array.length)));
//        printArray(popSort(Arrays.copyOf(array, array.length)));
//        printArray(heapSort(Arrays.copyOf(array, array.length)));
    }

    public static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1)
                System.out.print(",");
        }
        System.out.println("]");
    }

    public static int[] insertSort(int[] nums) {  // O(n^2)
        int len = nums.length;
        for (int i = 1; i < len; i ++) {
            int tem = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > tem) {
                nums[j + 1] = nums[j];
                j --;
            }
            nums[j+1] = tem;
        }
        return nums;
    }

//    选择排序因为在某一轮找到最小值后，将其安排到正确位置，是通过远距离交换来实现的，
//    交换两者中间夹着无序的元素，因为这些无序元素的存在，会产生相等元素之间相对顺序发生变化，会破坏稳定性，
//    而插入和冒泡不存在相等元素之间相对顺序发生变化的情况，所以是稳定的
    public static int[] selectSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int idx = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[idx]) idx = j;
            }
            int tem = nums[i];
            nums[i] = nums[idx];
            nums[idx] = tem;
        }
        return nums;
    }

    public static int[] popSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            boolean isOrder = true;
            int j = len - 1;
            while (j > i) {
                if (nums[j] < nums[j-1]) {
                    int tem = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tem;
                    isOrder = false;
                }
                j --;
            }
            if (isOrder) break;
        }
        return nums;
    }

//    public static int[] quickSort(int[] nums) {
//
//    }
    public static int[] heapSort(int[] nums) {
        int len = nums.length;
        for (int i = len / 2; i >= 0; i--) {
            adjustHeap(nums, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            int tem = nums[0];
            nums[0] = nums[i];
            nums[i] = tem;
            adjustHeap(nums, 0, i);
        }
        return nums;
    }
    private static void adjustHeap(int[] nums, int k, int len) {
        while (k < len) {
            int maxIdx;
            if (2*k+2 < len) {
                if (nums[2*k+1] > nums[2*k+2]) maxIdx = 2*k+1;
                else maxIdx = 2*k+2;
            } else if (2*k+1 < len) maxIdx = 2*k+1;
            else return;
            if (nums[k] > nums[maxIdx]) return;
            else {
                int tem = nums[k];
                nums[k] = nums[maxIdx];
                nums[maxIdx] = tem;
                k = maxIdx;
            }
        }
    }

//    public static int[] mergeSort(int[] nums) {
//
//    }
//    public static int[] shellSort(int[] nums) {
//
//    }
//    public static int[] countSort(int[] nums) {
//
//    }
//    public static int[] radixSort(int[] nums) {
//
//    }
//    public static int[] bucketSort(int[] nums) {
//
//    }
}
