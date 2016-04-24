package cxq.sa.sort;

import java.util.Arrays;

public class ShellSort {
    public static void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp; 
    }

    public static int[] shell(int[] input, int increment) {
        for (int i=0; i<input.length; i++) {
            for (int j=i; j<input.length; j+=increment) {
                int before = j - increment;
                while(before >=0 && input[j] < input[before]) {
                    swap(input, j, before);
                    before -= increment;
                }           
            }
        }
        if (increment == 1) return input;
        return shell(input, increment / 3 + 1);
    }

    public static int[] shellSort(int[] input) {
        if(input.length == 0) {
            System.out.println("bad args");
            return null;
        }
        return shell(input, input.length / 3 + 1);
    }
    
    public static void main(String[] args) {
        int[] input = {49, 38, 65, 97, 76, 13, 27, 49, 55, 4};
        System.out.println(Arrays.toString(shellSort(input)));
    }
}
