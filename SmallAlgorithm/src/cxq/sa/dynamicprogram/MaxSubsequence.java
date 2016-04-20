package cxq.sa.dynamicprogram;

import java.util.Arrays;

/**
 * 包括：最大和子序列、最大递增【连续/非连续】子序列、最长公共【连续/非连续】子序列、字符串编辑距离
 * 
 * @author Xinquan
 *
 */
public class MaxSubsequence {
    
    /**
     * 最大和子序列
     * @param input
     * @return
     */
    public static int getMaxSumSeq(int[] input) {
        int max = Integer.MIN_VALUE;
        if (input == null || input.length == 0) {
            System.err.println("bad args");
            return max;
        }
        int idx = -1;
        int sum = 0;
        for (int i=0; i<input.length; i++) {
            sum += input[i];
            if (sum < 0) {
                sum = 0;
            } else {
                if (max < sum) {
                    max = sum;
                    idx = i;
                }
            }
        }
        // List.toArray(arrayToStore);
        return max;
    }
    
    /**
     * 最大递增【连续/非连续】子序列
     * 子问题：lis[i]为以a[i]结尾的最大递增子序列长度
     * @return
     */
    public static int[] getLIS(int[] input) {
        if (input == null || input.length==0) {
            System.err.println("bad args");
            return null;
        }
        int[] lis = new int[input.length];
        lis[0] = 1;
        for (int i=1; i< input.length; i++) {
            // 非连续：lis[i] = max{lis[j]}+1, input[j]<input[i], j<i
            // 如果要求连续，则不必求出max，相等为上一个加一，不等为1
            int max = 0;
            for (int j=i-1; j >= 0; j--) {
                if (input[i]>input[j] && max < lis[j]) {
                    max = lis[j];
                }
            }
            lis[i] = max +1;    
        }
        int max = 0;
        int idx = -1;
        for (int k=0; k < lis.length; k++) {
            if (lis[k]>max) {
                max = lis[k];
                idx = k;
            }
        }
        int[] rst = new int[lis[idx]];
        rst[rst.length-1] = input[idx];
        int p = idx-1;
        for (int l=rst.length-2; l>=0; l--,p--){
            while(p>=0 && input[p]>rst[l+1]) p--;
            if (p>=0) {
                rst[l] = input[p];
            }
        }
        return rst;
    }
    
    /**
     * 最长【连续/非连续】子序列
     * LCS/LCS(continuous)
     * 子问题：lcs[i][j]为最长子序列长度
     * @param str1
     * @param str2
     * @return
     */
    public static char[] maxPublicSubstring(char[] str1, char[] str2) {
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
                    // notice!如果要求连续，则为0，非连续，则为max(matrix[i-1][j],matrix[i][j-1])
                    matrix[i][j] = 0;  
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
    
    /**
     * 最小【得分】Levenshtein编辑距离，
     * @param src
     * @param obj
     * @return
     */
    public static int levenSTDist(char[] src, char[] obj) {
        if (src==null || obj==null){
            System.err.println("bad args");
            return -1;
        }
        final int del = 3, add = 2, rep = 1; // 考虑操作delete add replace得分，越低越好
        
        int[][] leven = new int[src.length+1][obj.length+1];
        for (int i=0; i<=src.length; i++) {
            leven[i][0] = i * del;
        }
        for (int i=0; i<=obj.length; i++) {
            leven[0][i] = i * add;
        }
        for (int i=1; i<=src.length;i++) {
            for(int j=1; j<=obj.length;j++) {
                if (src[i-1] == obj[j-1]) {
                    leven[i][j] = leven[i-1][j-1];
                } else {
                    leven[i][j] = Math.min(Math.min(leven[i-1][j]+add, leven[i][j-1]+del), 
                            leven[i-1][j-1]+rep);
                }
                System.out.print(leven[i][j] + " ");
            }
            System.out.println();
        }
        return leven[src.length][obj.length];   
        
    }
    
public static void main(String[] args) {
    System.out.println(getMaxSumSeq(new int[]{2,-3,2,5}));
    System.out.println(Arrays.toString(getLIS(new int[]{0,3,1,2,5,3,4})));
    System.out.println(Arrays.toString(maxPublicSubstring(new char[]{'s','u','c','d'}, 
            new char[]{'s','u','b'})));
    System.out.println(levenSTDist(new char[]{'s','u','c','d'}, new char[]{'s','u','b'}));
}
}
