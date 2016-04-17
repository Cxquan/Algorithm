package cxq.aimatoffer.question38.counttimes;

public class CountTimes {


private static int partition(int[] input, int low, int high, int n){
    if(high == low) {
        return input[low] == n ? low : -1;
    }
    int mid = (high+low) >> 1;
    if (input[mid] == n){
        return mid;
    }
    if (input[mid] < n) {
        return partition(input, mid+1, high, n);
    } else {
        return partition(input, low, mid-1, n);
    }   
}

public static int appearTimes(int[] input, int n){
    if(input==null || input.length==0) return -1;
    int idx = partition(input, 0, input.length-1, n);
    if (idx == -1) return -1;
    int left=idx-1;
    int right=idx+1;
    while(left>=0 && input[left] == n) left--;
    while(right<input.length && input[right] == n) right++;
    
    return right - left-1;
}


    public static void main(String[] args) {
        int[] a = {3,3,3,3};
        System.out.println(appearTimes(a, 1));

    }

}
