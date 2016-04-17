package cxq.aimatoffer.question39.balancedtree;

public class BalancedTree {

 // getDepth

    public static int getDepth(Node head) {
        if(head==null) return 0;
        int left = getDepth(head.left);
        int right = getDepth(head.right);
        return 1 + (left > right ? left : right);
    }



    // isBalancedTree
    public static Rst isBalancedTree(Node head) {
        if(head == null) {
            return new Rst(0, true);
        }

        Rst left = isBalancedTree(head.left);
        Rst right = isBalancedTree(head.right);
        if(left.isBalanced && right.isBalanced) {
            int dif = left.depth - right.depth;
            if (dif >= -1 || dif <= 1) {
                return new Rst(1+(left.depth>right.depth ? left.depth : right.depth), true);
            }
        }
        return new Rst(-1, false);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
