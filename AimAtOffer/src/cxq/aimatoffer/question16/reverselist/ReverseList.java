package cxq.aimatoffer.question16.reverselist;

public class ReverseList {
    public static ListNode reverse(ListNode head) throws Exception{
        if (head == null) {
            throw new Exception("bad arg");
        }
        ListNode mid = head.next;
        if (mid == null) {
            return head;
        }
        
        head.next = null;
        ListNode right;
        while (mid != null) {
            right = mid.next;
            mid.next = head;
            head = mid;
            mid = right;
        }
        return head;
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < 3; i++){
            node.next = new ListNode(i);
            node = node.next;
        }
        ListNode.print(head);
        
        try {
            ListNode.print(ReverseList.reverse(head));
            ListNode.print(ReverseList.reverse(new ListNode(10)));
            ListNode.print(ReverseList.reverse(null));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
