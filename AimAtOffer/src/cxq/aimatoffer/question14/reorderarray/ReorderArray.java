package cxq.aimatoffer.question14.reorderarray;

import java.util.Arrays;

public class ReorderArray {
    public static void reorderOddEven(int[] array) {
        if (array == null) { 
            return; 
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < right && (array[left] & 0x1) == 1) {
                left += 1;
            }
            while (left < right && (array[right] & 0x1) == 0) {
                right -= 1;
            }
            if (left < right) {
                swap(left, right, array);
            }
        }
    }
    
    private static void swap(int a, int b, int[] array) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    
    // test
    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 2, 2, 2};
        System.out.println(Arrays.toString(array));
        ReorderArray.reorderOddEven(array);
        System.out.println(Arrays.toString(array));

    }

}
