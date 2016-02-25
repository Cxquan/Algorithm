package cxq.aimatoffer.question5.upsidedownList;

import java.util.ArrayList;
import java.util.Stack;

public class UpsidedownList {
    // 使用Stack
    public static ArrayList<Integer> doUpsidedown1(ListNode head) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.value);
            node = node.next;
        }
//        rst.addAll(stack); //此方法不能打印出倒置的序列，因为Stack是继承了用vector，依旧是顺序的
        while (!stack.empty()) {
            rst.add(stack.pop());
        }
        return rst;
    }
    
    
    // 使用递归
    public static ArrayList<Integer> doUpsidedown2(ListNode head) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        doUpsidedownRecursion(list, head);
        return list;
    }
    
    private static void doUpsidedownRecursion(ArrayList<Integer> list, ListNode node) {
        if (node == null) {
            return;
        } else {
            doUpsidedownRecursion(list, node.next);
            list.add(node.value);
        }
    }
    

    // for test
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < 10; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        ArrayList rst = doUpsidedown1(head);
        System.out.println(rst);
        ArrayList rst2 = doUpsidedown2(head);
        System.out.println(rst2);
    }

}
