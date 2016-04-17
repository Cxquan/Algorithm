package cxq.aimatoffer.question36.invertednum;

public class InvertedNum {
    public static void swap(int[] input, int i, int j) {
        // check
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
    public static int countInv(int[] input) {
        if(input==null) return -1;
        if(input.length==1 || input.length==0) return 0;
        if(input.length==2) {
            if (input[0] > input[1]) {
                swap(input, 0, 1);
                return 1;
            } else {
                return 0;
            }
        }
        int mid = input.length >>1;
        
        int[] leftArr = new int[mid];
        int[] rightArr = new int[input.length-mid];
        for(int i=0; i<mid; i++){leftArr[i]=input[i];}
        for(int i=0; i<input.length-mid; i++){rightArr[i]=input[mid+i];}
        
        int left = countInv(leftArr);
        int right = countInv(rightArr);
        int merge = countAndMerge(input, leftArr, rightArr);
        return left+right+merge;
    }

    public static int countAndMerge(int[] input, int[] left, int[] right){
        int i=0,j=0,k=0;
        int count=0;
        while(i<left.length && j<right.length) {
            if (left[i]>right[j]) {
                count += (left.length-i);
                input[k] = right[j];
                k++;
                j++;
            } else {
                input[k] = left[i];
                k++;
                i++;
            }
        }
        while(i < left.length){input[k] = left[i];i++;k++;}
        while(j < right.length){input[k] = right[j];j++;k++;}
        return count;
    }
    public static void main(String[] args) {
        int[] input = {7,5,6,4};
        System.out.println(countInv(input));

    }

}
