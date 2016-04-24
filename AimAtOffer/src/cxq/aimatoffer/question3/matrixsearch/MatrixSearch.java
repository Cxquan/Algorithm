package cxq.aimatoffer.question3.matrixsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixSearch {
    // 法1 逐行逐列排除
    public static boolean isNumInMatrix1(int num, int[][] matrix) {
        int col = matrix[0].length - 1;
        
        boolean isExist = isNumInMatrixRecursion(num, matrix, 0, col);
         
        return isExist;
    }
    
    // 递归
    private static boolean isNumInMatrixRecursion(int num, int[][]matrix, int row, int col) {
        if (row >= matrix.length || col < 0) {
            return false;
        }
        int m = matrix[row][col];
        if (num == m) {
            return true;
        } else if (num < m) {
            return isNumInMatrixRecursion(num, matrix, row, col-1);
        } else {
            return isNumInMatrixRecursion(num, matrix, row+1, col);
        }
    }

    
    // 法2 分治法，区块排除
    public static boolean isNumInMatrix2(int num, int[][] matrix) {
        // 矩阵分成四块，记录坐标太麻烦了
        return false;
    }
    
    // 法3 转为HashSet，胡扯。。干脆直接遍历判断
    public static boolean isNumInMatrix3(int num, int[][] matrix) {
        Set<Integer> set = new HashSet<Integer>();
        for (int[] one : matrix) {
            for (int a : one) {
                set.add(a);
            }
            // asList的参数是泛型，对基本类型数组识别出的类型依然为数组，比如int[]，应该用Integer
            // List<Integer> l = Arrays.asList(one); 
        }
        
        return set.contains(num) ? true : false;
    }
    
    
    // for test
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] matrix = {{1, 2, 8, 9},
                          {2, 4, 9, 12},
                          {4, 7, 10, 13},
                          {6, 8, 11, 15}
                          };
        boolean b = MatrixSearch.isNumInMatrix1(16, matrix);
        System.out.println(b);
        
        b = MatrixSearch.isNumInMatrix2(15, matrix);
        System.out.println(b);
        
        b = MatrixSearch.isNumInMatrix3(15, matrix);
        System.out.println(b);
    }

}
