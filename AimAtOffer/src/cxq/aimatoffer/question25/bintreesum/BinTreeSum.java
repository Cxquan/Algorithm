package cxq.aimatoffer.question25.bintreesum;

import java.util.Stack;

public class BinTreeSum {
    public static void printPath(BinNode head, int exceptedSum) {
        if (head == null) {
            return;
        }
        Stack<Integer> path = new Stack<Integer>();
        int curSum = 0;
        print(head, exceptedSum, path, curSum);
    }
    
    private static void print(BinNode head, int exceptedSum, Stack<Integer> path, int curSum) {
        if (head == null) {
            return;
        }
        path.add(head.value);
        curSum += head.value;
        
        if (curSum == exceptedSum) {
            for (Integer a : path) { // 用Vector也可
                System.out.print(a+" ");
            }
            System.out.println();
        }
        if (curSum < exceptedSum && head.left != null) {
            print(head.left, exceptedSum, path, curSum);
        }
        if (curSum < exceptedSum && head.right != null) {
            print(head.right, exceptedSum, path, curSum);
        }
        // 返回上一个节点
        path.pop();
        curSum -= head.value;
    }
    
    // for test
    public static void main(String[] args) {
        BinNode head = new BinNode(10);
        head.left = new BinNode(5);
        head.right = new BinNode(12);
        head.left.left = new BinNode(4);
        head.left.right = new BinNode(7);
        BinTreeSum.printPath(head, 10);
    }
}
