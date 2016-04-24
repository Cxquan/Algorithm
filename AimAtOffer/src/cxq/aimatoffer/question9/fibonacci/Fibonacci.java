package cxq.aimatoffer.question9.fibonacci;

public class Fibonacci {
    // 法1. awful
    public static int calcFibonacci1(int index) {
        if (index <= 0) { // start from 1
            System.out.println("bad args");
            return -1;
        }
        if (index == 1) {
            return 0;
        }
        if (index == 2) {
            return 1;
        }
        return calcFibonacci1(index-1) + calcFibonacci1(index - 2);
    }
    
    // 法2. efficient
    public static int calcFibonacci2(int index) {
        if (index <= 0) { // start from 1
            System.out.println("bad args");
            return -1;
        }
        if (index < 3) {
            return index == 1 ? 0 : 1;
        }
        int a = 0, b = 1;
        for (int i = 3; i <= index; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;        
    }
    
    
    // for test
    public static void main(String[] args) {
        System.out.println(Fibonacci.calcFibonacci1(40));
        System.out.println("--");
        System.out.println(Fibonacci.calcFibonacci2(50));
    }

}
