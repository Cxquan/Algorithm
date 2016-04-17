package cxq.aimatoffer.question28.strgroup;

import java.util.List;
import java.util.ArrayList;

public class StrGroup {
    public static void genGroup(char[] input, int m, List<Character> output) {
        if(m==0) {
            System.out.println(output);
            return;
        }
        if (input.length==0)return;
        
        char[] arr = new char[input.length-1];
        for(int i=1; i< input.length;i++){ arr[i-1] = input[i];}
        // put
        output.add(input[0]);
        genGroup(arr, m-1, output);
        // not put
        output.remove(output.size()-1);
        genGroup(arr, m, output);
    }

    public static void gen(char[] input) {
        if(input==null || input.length==0) return;
        
        for(int i=1;i<=input.length; i++){
            List<Character> output = new ArrayList<>();
            genGroup(input, i, output);
        }
    }
    public static void main(String[] args) {
        
        gen(new char[]{'a', 'b', 'c'});

    }

}
