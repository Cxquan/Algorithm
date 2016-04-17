package cxq.sa.tree;

public class MaxDiff {
 // 节点最大差值

    public class MyInteger {
        public int value;
        public MyInteger(int value) {this.value = value;}
    }
    public static int maxDiff(Node head) {
        if (head == null) return -1;
        MyInteger max = new MaxDiff().new MyInteger(Integer.MIN_VALUE);
        MyInteger min = new MaxDiff().new MyInteger(Integer.MAX_VALUE);
        getMaxMin(head, max, min);
        return max.value - min.value;
    }

    public static void getMaxMin(Node head, MyInteger max, MyInteger min) {
        if (head == null) return;
        if (head.value > max.value) {
            max.value = head.value;
        }
        if (head.value < min.value) {
            min.value = head.value;
        }
        getMaxMin(head.left, max, min);
        getMaxMin(head.right, max, min);
        long a = 9223372036854775807L;
        
    }

}
