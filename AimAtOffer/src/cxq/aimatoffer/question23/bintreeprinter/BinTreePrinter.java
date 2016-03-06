package cxq.aimatoffer.question23.bintreeprinter;

import java.util.LinkedList;
import java.util.Queue;

public class BinTreePrinter {
    public static void print(BinNode head) {
        if (head == null) {
            System.out.println("bad args");
            return;
        }
        Queue<BinNode> queue = new LinkedList<BinNode>(); // 还有BlockingQueue等
        queue.add(head);
        while (!queue.isEmpty()) {
            BinNode bt = queue.remove();
            if (bt.left != null) {
                queue.add(bt.left);
            }
            if (bt.right != null) {
                queue.add(bt.right);
            }
            System.out.println(bt.value);
        }
    }
    
    // for test
    public static void main(String[] args) {
        BinNode head = new BinNode(0);
        head.left = new BinNode(1);
        head.right = new BinNode(2);
        head.left.left = new BinNode(3);
        head.left.right = new BinNode(4);
//        head.right.left = new BinNode(5);
        head.right.right = new BinNode(6);
        
        BinTreePrinter.print(head);
    }
}
