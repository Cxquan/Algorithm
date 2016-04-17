package cxq.sa.encode;

import java.lang.Math;
import java.util.Arrays;
public class GreyCode {

    public static String[] genGreyCode(int n) {
        if (n < 1) {
            System.out.println("bad param");
        }
        int len = (int)Math.pow(2, n);
        String[] result = new String[len];
        if (n == 1) {
            result[0] = "0";
            result[1] = "1";
            return result;
        }
        String[] before = genGreyCode(n-1);
        int halflen = (int)Math.pow(2, n-1);
        for (int i=0; i<halflen; i++) {
            result[i] = "0" + before[i];
            result[len-1-i] = "1" + before[i];
        }
        return result;      
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(genGreyCode(3)));

    }

}
