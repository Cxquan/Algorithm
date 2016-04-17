package cxq.aimatoffer.question28.strgroup;
import java.util.List;
import java.util.ArrayList;

public class NumGroup {
 // 数字和
    public static void genNum(int n, int m) {
        if (n<0 || m <0 || (1+n)*n/2 < m) return;
        List<Integer> output = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            genGroup(1, i, n, m, output);
        }
    }
    
    public static void genGroup(int head, int k, int n, int m, List<Integer> output) {
        if(k==0) {
            int sum = 0;
            for(int a : output) {
                sum += a;
            }
            if (sum == m) {
                System.out.println(output);
            }
            return;
        }
        if (head > n) return;
        // put
        output.add(head);
        head += 1;
        genGroup(head, k-1, n, m, output);
        //not put
        output.remove(output.size()-1);
        genGroup(head, k, n, m, output);
    }
    
    public static void main(String[] args) {
        genNum(10, 10);
    }
}
