package cxq.aimatoffer.question13.dellistnode;

public class DelListNode {
    public static void delNode(ListNode head, ListNode node) {
        if (head == node) { // 头结点！
            System.out.println("ERROR: Java 函数里无法删除对参数指向的对象");
            return;
        }
        if (node.next == null) { // 尾节点
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
//        ListNode node = head;
//        for (int i = 1; i < 1; i++) {
//            node.next = new ListNode(i);
//            node = node.next;
//        }
        
//        ListNode node2del = node;
        DelListNode.delNode(head, head);
        
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

}
