package cxq.aimatoffer.question24.bstorder;

public class BstVerifier {
    public static boolean verify(int[] seq) {
        if (seq == null) {
            System.out.println("bad args");
            return false;
        }
        if (seq.length == 0 || seq.length == 1) {
            return true;
        }
        int head = seq[seq.length - 1];
        int i = 0;
        while (i < seq.length - 1 && seq[i] < head) {
            i += 1;
        }
        
        // 挑出循环后，i为右子节点，边界为seq.length-1, 0
        int[] seqLeft = new int[i];
        int[] seqRight = new int[seq.length - 1 - i];
        for (int j = 0; j < i; j++) {
            seqLeft[j] = seq[i];
        }
        
        for (int j = i; j < seq.length - 1; j++) { //注意边界
            seqRight[j-i] = seq[j];
            if (head > seq[j]) {
                return false; // 这步才算是验证
            }
        }
        
        return verify(seqLeft) && verify(seqRight);
    }
    
    // for test
    public static void main(String[] args) {
//        int[] bst = {5,7,6,9,11,10,8};
        int[] bst = {7,8,9};
        System.out.println(BstVerifier.verify(bst));
    }
}
