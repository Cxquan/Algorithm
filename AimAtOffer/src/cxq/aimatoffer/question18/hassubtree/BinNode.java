package cxq.aimatoffer.question18.hassubtree;

public class BinNode {
    int value;
    BinNode left = null;
    BinNode right = null;
    
    public BinNode(int value) {
        this.value = value;
    }
    
    public static void print(BinNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        print(root.left);
        print(root.right);
    }
}
