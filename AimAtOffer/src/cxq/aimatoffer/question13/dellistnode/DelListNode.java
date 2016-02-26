package cxq.aimatoffer.question13.dellistnode;

public class DelListNode {
    public static void delNode(ListNode head, ListNode node) {
        if (node.next == null) {
            while (head.next != node) {
                head = head.next;
            }
            head.next = null;
            return;
        }
        node.value = node.next.value;
        ListNode tmp = node.next.next;
        node.next = tmp;
    }
    
    // for test
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < 10; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        
        ListNode node2del = node;
        DelListNode.delNode(head, node2del);
        
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

}
