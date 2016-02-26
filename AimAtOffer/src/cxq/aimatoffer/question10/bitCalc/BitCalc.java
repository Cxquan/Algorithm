package cxq.aimatoffer.question10.bitCalc;

public class BitCalc {
    // 1 左移，需注意判断条件
    public static int NumberOf1_1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) { // 不能使用flag>0作为条件
            if ((n & flag) != 0) { // 不能使用flag>0作为条件
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }
    
    // Integer bitCount
    public static int NumberOf1_2(int n) {
        return Integer.bitCount(n);
    }
    
    // minus 1，每次去掉一个1
    public static int NumberOf1_3(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count += 1;
        }
        return count;
    }
    // more solutions:  http://15838341661-139-com.iteye.com/blog/1642525
    
    //
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(BitCalc.NumberOf1_1(-1));
        // 负数二进制使用2的补码表示，http://www.ruanyifeng.com/blog/2009/08/twos_complement.html
        
        System.out.println(BitCalc.NumberOf1_2(-1)); 
        System.out.println(BitCalc.NumberOf1_3(-1));
        System.out.println((int)Math.pow(2, 31)); // int 只有32bit,最高位是符号位，再多就溢出了

    }

}
