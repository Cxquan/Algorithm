package cxq.aimatoffer.question6.rebuildbinarytree;

import java.util.Arrays;

public class RebuildBinaryTree {
    public static BinNode rebuild(Integer[] pre, Integer[] in) {
        if (pre.length <= 0 || in.length <= 0) {
            return null;
        } else if (pre.length == 1) {
            return new BinNode(pre[0]);
        }
        int root_val = pre[0];
        BinNode root = new BinNode(root_val);
        int root_idx = Arrays.asList(in).indexOf(root_val); // 将int[]换成Integer[]即可用此法
        int l_len = root_idx;
        
        // get pre left and right
        Integer[] pre_left = new Integer[l_len];
        System.arraycopy(pre, 1, pre_left, 0, l_len);
        
        int r_len = pre.length - l_len - 1;
        Integer[] pre_right = new Integer[r_len];
        System.arraycopy(pre, 1+root_idx, pre_right, 0, r_len);
        
        // get in left and right
        Integer[] in_left = new Integer[l_len];
        Integer[] in_right = new Integer[r_len];
        System.arraycopy(in, 0, in_left, 0, l_len);
        System.arraycopy(in, root_idx+1, in_right, 0, r_len);
        
        root.left = rebuild(pre_left, in_left);
        root.right = rebuild(pre_right, in_right);
        
        return root;
    }
    
    // 前序遍历，用于验证
    public static void printBinTree(BinNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value);
        printBinTree(root.left);        
        printBinTree(root.right);
    }
   
    // for test
    public static void main(String[] args) {
        Integer[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        Integer[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        BinNode root = RebuildBinaryTree.rebuild(pre, in);
        RebuildBinaryTree.printBinTree(root);        
    }

}
