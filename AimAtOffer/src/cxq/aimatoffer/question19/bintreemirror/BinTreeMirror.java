package cxq.aimatoffer.question19.bintreemirror;

public class BinTreeMirror {
    public static BinNode genMirror(BinNode head) {
        if (head == null) {
            return null;
        }
        BinNode tmp = head.left;
        head.left = genMirror(head.right);
        head.right = genMirror(tmp);
        return head;
    }
    
    //
    public static void main(String[] args) {
        BinNode head = new BinNode(0);
        head.left = new BinNode(1);
        head.right = new BinNode(2);
        head.right.right = new BinNode(3);
        BinNode.print(head);
        System.out.println();
        
        BinNode.print(genMirror(head));
    }

}
