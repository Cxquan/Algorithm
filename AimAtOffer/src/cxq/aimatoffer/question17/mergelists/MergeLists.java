package cxq.aimatoffer.question17.mergelists;

public class MergeLists {
    // 法1. 引入了无意义的头结点
    public static ListNode merge1(ListNode arr1, ListNode arr2) throws Exception {
        if (arr1 == null && arr2 == null) {
            throw new Exception("bad args");
        }
        if (arr1 == null) {
            return arr2;
        } else if (arr2 == null) {
            return arr1;
        }
        ListNode head = new ListNode(Integer.MAX_VALUE); // 头结点,无实际意义
        ListNode arrn = head;
        while (arr1 != null && arr2 != null) {
            if (arr1.value <= arr2.value) {
//                arrn.next = arr1; // BAD PRACTICE！
                arrn.next = new ListNode(arr1.value);
                arr1 = arr1.next;
            } else {
                arrn.next = new ListNode(arr2.value);
                arr2 = arr2.next;
            }
            arrn = arrn.next;
        }
        while (arr1 != null) {
            arrn.next = arr1;
            arrn = arrn.next;
            arr1 = arr1.next;
        }
        while (arr2 != null) {
            arrn.next = arr2;
            arrn = arrn.next;
            arr2 = arr2.next;
        }
        return head;
        
    }
    
    // 法2. 递归
    public static ListNode merge2(ListNode arr1, ListNode arr2) throws Exception {
        if (arr1 == null && arr2 == null) {
            throw new Exception("bad args");
        }
        if (arr1 == null) {
            return arr2;
        } else if (arr2 == null) {
            return arr1;
        }
        ListNode head = null;
        if (arr1.value < arr2.value) {
            head = arr1;
            head.next = merge2(arr1.next, arr2);
        } else {
            head = arr2;
            head.next = merge2(arr1, arr2.next);
        }
        return head;    
    }
    
    // for test
    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode node = head1;
        for (int i = 1; i < 20; i += 2) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node = head2;
        for (int i = 1; i < 20; i += 3) {
            node.next = new ListNode(i);
            node = node.next;
        }
        try {
            ListNode.print(MergeLists.merge1(head1, head2));
            ListNode.print(MergeLists.merge2(head1, head2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
