package cxq.aimatoffer.question40.uniqnum;

public class UniqNum {
    public static void findUniq(int[] input) {
        int f = 0;
        for (int a : input) {
            f ^= a;
        }
        int first1 = getFirst1(f);
        int ux=0;
        int uy = 0;
        for (int b : input) {
            if ((b & first1) == 0) {
                ux ^= b;
            } else {
                uy ^= b;
            }
        }
        System.out.println("uniq x=" + ux + ", y=" + uy);
    }

    public static int getFirst1(int f) {
        int a = 0x1;
        int cnt = 0;
        while((f & 0x1) != 1) {
            f = f>>1;
            cnt += 1;
        }
        return 0x1 << cnt;
    }
    
    public static void main(String[] args) {
        int[] input = {2,4,3,6,3,2,5,5};
        findUniq(input);
    }
}
