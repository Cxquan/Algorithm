package cxq.sa.dynamic;

public class MaxSubstring {
 // 最长公共子序列

    public static char[] maxSubstring(char[] str1, char[] str2) {
        if(str1==null || str2==null || str1.length==0|| str2.length==0) return null;
        
        int[][] matrix = new int[str1.length][str2.length];
        // init
        for (int i=0; i<str2.length; i++) {
            matrix[0][i] = str1[0] == str2[i] ? 1 : 0;
        }
        for (int i=0; i<str1.length; i++) {
            matrix[i][0] = str2[0] == str1[i] ? 1 : 0;
        }
        int max = Integer.MIN_VALUE;
        int idx = -1; // str1
        // fill matrix
        for (int i=1; i<str1.length;i++) {
            for(int j=1; j<str2.length; j++) {
                if(str2[j]== str1[i]) {
                    matrix[i][j] =  matrix[i-1][j-1] + 1;
                    if (max < matrix[i][j]){
                        max = matrix[i][j];
                        idx = i;
                    }
                } else {
                    matrix[i][j] = 0;  // notice!
                }
            }
        }
        
        // trace back
        System.out.println("max substring length = " + max);
        char[] rst = new char[max];
        int k = 0;
        for (int i=idx-max+1; i<=idx; i++) {
            rst[k] = str1[i];
            k+=1;
        }
        return rst;
    }
    
public static void main(String[] args) {
    System.out.println(maxSubstring(new char[]{'s','u','c','d'}, new char[]{'s','u','b'}));
}
}
