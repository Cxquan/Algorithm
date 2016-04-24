package cxq.aimatoffer.question15.KthToTail;

public class KthToTail {
    // 双指针法。以下实现方法不好理解，分两步走较好
    public static ListNode FindKth(int k, ListNode head) throws Exception{
        if (head == null || k <= 0) {
            throw new Exception("bad args");
        }
        
        ListNode kthNode = head;
        boolean startMove = false;
        int count = 1;
        while (head != null) {
            head = head.next;

            if (startMove) {
                kthNode = kthNode.next;
            }
            if (count == k) {
                startMove = true;
            }
            count += 1;
        }
        
        if (count <= k) { // careful for count += 1
            throw new Exception(" bad k arg");
        }
        return kthNode;
    }
    
    // 类似问题：求链表中间节点，双指针分别一步两步
    
    // for test
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < 10; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        try {
            System.out.println(KthToTail.FindKth(3, head).value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
