package cxq.aimatoffer.question18.hassubtree;

public class HasSubtree {
    public static boolean hasSub(BinNode a, BinNode b) {
        if (a == null || b == null) {
//            throw new Exception("bad args");
            return false;
        }
        boolean result = false;
        if (a.value == b.value) {
            result = hasSameStruct(a, b);            
        } 
        if (!result) {
            result = hasSub(a.left, b);
        }
        if (!result) { // 左子树中没有b结构，再遍历右子树
            result = hasSub(a.right, b);                
        }
        return result;
    }
    
    public static boolean hasSameStruct(BinNode aa, BinNode bb) {
        if (aa == null && bb == null) {
            return true; //
        }
        if (aa == null) {
            return false;
        }
        if (bb == null) {
            return true; //
        }
        
        boolean result = false;
        if (aa.value == bb.value) {
//            if (bb.left != null) {
//                left = hasSameStruct(aa.left, bb.left);
//            } else {
//                left = true;
//            }            
            result = hasSameStruct(aa.left, bb.left) && hasSameStruct(aa.right, bb.right);
        } else {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        BinNode head = new BinNode(0);
        head.left = new BinNode(1);
        head.right = new BinNode(3);
        
        BinNode.print(head);
        System.out.println();
        
        BinNode child = new BinNode(0);
        child.left = new BinNode(1);
        child.right = new BinNode(2);
        BinNode.print(child);
        System.out.println();
        
        System.out.println(hasSub(head, child));
    }

}
