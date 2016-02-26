package cxq.aimatoffer.question12.print2Max;

public class Print2Max {
    public static void printMax(int n) {
        if (n <= 0) {
            System.out.println("bad args");
            return;
        }
        
        // 初始化
        char[] num = new char[n];
        for (int i = 0; i < n; i++) {
            num[i] = '0'; 
        }
        
        while (!increment(num)) {
            print(num);
            System.out.println();
        }
    }
    
    private static boolean increment(char[] num) {
        int len = num.length;
        int takeOver = 1;
        boolean isOver = false;
        
        
        for (int i = len - 1; i >= 0; i--) {
            int sin = num[i] - '0' + takeOver; // 可直接减
            
            if (sin >= 10) {
                if (i == 0) {
                    isOver = true;
                    break;
                }
                sin = sin - 10;
                num[i] = (char)((int)'0' + sin);
            } else {
                num[i] = (char)((int)'0' + sin); // 没有进位就跳出循环了
                break;
            }
        }
        return isOver;
    }
    
    private static void print(char[] num) {
        boolean start = false;
        for (char n : num) {
            if (!start && n != '0') {
                start = true;
                System.out.print(n - '0');
            } else if (start) {
                System.out.print(n - '0');
            } else {}
        }
    }
    
    public static void main(String[] args) {
        Print2Max.printMax(3);

    }

}
