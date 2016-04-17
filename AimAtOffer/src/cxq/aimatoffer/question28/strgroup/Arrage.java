package cxq.aimatoffer.question28.strgroup;
import java.util.Arrays;

public class Arrage {
 // 排列
    public static void genArrange(char[] input) {
        if(input==null || input.length ==0) return;
        char[] output = new char[input.length];
        arrange(input, 0, output);
    }

    public static void arrange(char[] input, int k, char[] output) {
        if(k==output.length) {
            System.out.println(Arrays.toString(output));
        }
        if (input.length==0) return;
        
        for (int i=0; i< input.length; i++) {
            output[k] = input[i];
            char[] arr = new char[input.length-1];
            int p = 0;
            for(int j=0; j<input.length; j++) {
                if(j==i) continue;
                arr[p] = input[j];
                p+=1;
            }
            arrange(arr, k+1, output);
        }   
    }

public static void main(String[] args) {
    genArrange(new char[]{'a','b','c'});
}

}

