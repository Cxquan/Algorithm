package cxq.aimatoffer.question22.stackpoporder;

import java.util.Stack;

public class StackPopOrder {
    public static boolean isPopOrder(int[] input, int[] output) {
        if (input == null || output == null) {
            System.out.println("bad args");
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int idx = 0;
        for (int i = 0; i < input.length; i++) {
            stack.push(input[i]);
            while (!stack.empty() && idx < output.length && stack.peek() == output[idx]) {
                idx += 1;
                System.out.println(stack.pop());
            }
        }
        return stack.empty() ? true : false;
    }
    
    // for test
    public static void main(String[] args) {
        int[] input = {1,2,3,4,5};
        int[] output = {1,3,5,1,2};
        System.out.println(StackPopOrder.isPopOrder(input, output));
    }
    
}
