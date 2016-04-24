package cxq.sa.dynamicprogram;

import java.util.Stack;
/**
 * 给出一个直方图，求面积最大矩形
 */
public class MaxRect {
    /**
     * 法一
     * 复杂度：3n，用栈只需要1n
     */
    public static long maxRectangle(int[] heigh) {       
        int[] left = new int[heigh.length];
        int[] right = new int[heigh.length];
    
        left[0] = 1;
        for (int i = 1; i < heigh.length; i++) {
            left[i] = 1;
            if (heigh[i] == heigh[i-1]) {
                left[i] = left[i-1] +1;
            } else if (heigh[i] > heigh[i-1]){
                left[i] = 1;
            } else {
                int p = i-1;
                while(p >=0 && heigh[i] <= heigh[p]) {
                    left[i] += left[p];
                    p = p - left[p];
                }
            }           
        }
        
        right[heigh.length-1] = 1;
        for (int i = heigh.length-2; i > -1; i--) {
            right[i]= 1;
            if (heigh[i] == heigh[i+1]) {
                right[i] = right[i+1] +1;
            } else if (heigh[i] > heigh[i+1]){
                right[i] = 1;
            } else {
                int p = i+1;
                while(p < heigh.length && heigh[i] <= heigh[p]) {
                    right[i] += right[p];
                    p = p + right[p];
                }
            }           
        }
        
        long maxMatrix = 0;
        for (int i = 0; i < heigh.length; i++) {
//            System.out.println(left[i] + " " + right[i]);
            long tmp = (left[i] + right[i] -1) * heigh[i];
            maxMatrix = maxMatrix > tmp ? maxMatrix : tmp;
        }
        return maxMatrix;
    }
    
    public static int getMaxSpaceByStack(int[] heigh) {
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i=1; i<heigh.length; i++) {
            int top = heigh[stack.peek()];
            if (top <= heigh[i]) { // i-1和i相等，对求最大space无影响
                stack.push(i);
            } else { // top > heigh[i]
                int space = 0;
                for (int cur=stack.peek();
                        !stack.empty() && top>heigh[i];) {
                    space = top * (i-cur);
                    if (space > max) max = space;
                    stack.pop();
                    if (!stack.empty()) { // pop之后注意判断
                        cur = stack.peek();
                        top = heigh[cur];
                    }
                }
                stack.push(i);
            }
        }
        int right = stack.peek();
        while (!stack.empty()) {
            int left = stack.pop();
            int space = (right-left+1) * heigh[left]; // heigh[left]即当前高度
            if(space > max) max = space;
        }
        return max;
    }
    
    public static void main(String args[]) {
        System.out.println(maxRectangle(new int[]{1,1,2,3,4,1,1}));
        System.out.println(getMaxSpaceByStack(new int[]{1,1,2,3,4,1,1}));
    }
}
