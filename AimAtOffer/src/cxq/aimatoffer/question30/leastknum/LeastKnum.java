package cxq.aimatoffer.question30.leastknum;

import java.util.Arrays;

public class LeastKnum {


public static int[] getLeastKnum(int[] input, int k) {
    if (k < 1 || input==null || input.length == 0) {
        return null;
    }
    int idx = partitionIdx(input, k, 0, input.length -1);
    int[] rst = new int[k];
    for (int i=0; i<k; i++) {
        rst[i] = input[i];
    }
    return rst;
}

public static int partitionIdx(int[] input, int k, int low, int high) {
    if (input == null || input.length == 0 || k < 1 || k >= input.length) {
        System.out.println("invalid args");
        return -1;
    }
    int num = input[k-1];
    swap(input, k-1, high);
    int i = low;
    int j = high-1;
    while (i < j) {
        while(i<j && input[i] <= num) i++; // equals
        while(i<j && input[j] > num) j--;
        if (i < j) {
            swap(input, i, j);
        }
    }
    int rst = -1;
    swap(input, j, high);
    if (j == k-1) {
        rst = input[j];
    }else if (j < k-1) {
        rst = partitionIdx(input, k, j+1, high);
    } else{
        rst = partitionIdx(input, k, low, j);
    }
    return rst;
}

public static void swap(int[] input, int i, int j) {
    // check
    int tmp = input[i];
    input[i] = input[j];
    input[j] = tmp;
}

public static int[] sort(int[] input) {
    for (int i=0; i<input.length;i++) {
        for (int j=i+1; j < input.length; j++){
            if ((input[i]+"").compareTo(input[j]+"") >=0 ) {
//            if(input[i] >= input[j]){
                swap(input, i, j);
            }
        }
    }
    return input;
}

    public static void main(String[] args) {
        int[] input = {4,5,1000,60,2,7,3,8};
        System.out.println(Arrays.toString(getLeastKnum(input, 1)));
        System.out.println(Arrays.toString(sort(input)));

    }

}
