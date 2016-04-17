package cxq.aimatoffer.question15.KthToTail;

public class ListNode {
    int value;
    ListNode next = null;
    
    public ListNode(int value) {
        this.value = value;
    }
    
    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
