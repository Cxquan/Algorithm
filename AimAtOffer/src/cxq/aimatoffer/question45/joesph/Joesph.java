package cxq.aimatoffer.question45.joesph;

public class Joesph {


    public static int joseph(int[] input, int m) {
        if (input== null || input.length == 0) return -1;
        Node head = new Node(input[0]);
        Node node = head;
        for (int i=1; i < input.length; i++) {
            node.next = new Node(input[i]);
            node = node.next;
        }
        node.next = head;
        int i = 1;
        while (head.next != head) {
            while(i < m-1) {
                head = head.next;
                i++;
            }
            i = 1;
            System.out.println(head.next.value);
            head.next = head.next.next;
            head = head.next;
        }
        return head.value;   
    }
    
    public static void main(String[] args) {
        int[] input = {0,1,2,3,4};
        System.out.println(joseph(input, 3));
    }
}
