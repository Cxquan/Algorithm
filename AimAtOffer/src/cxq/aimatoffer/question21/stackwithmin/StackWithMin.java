package cxq.aimatoffer.question21.stackwithmin;

import java.util.Stack;
public class StackWithMin {
    private Stack<Integer> dataStack = null;
    private Stack<Integer> minStack = null;
    
    public StackWithMin() {
        dataStack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int value) {
        dataStack.push(value);
        if (minStack.empty()) {
            minStack.push(value);
        } else {
            Integer min = minStack.peek();
            if (min > value) {
                minStack.push(value);
            } else {
                minStack.push(min);
            }   
        }
    }
    
    public Integer pop() throws Exception {
        if (minStack.empty()) {
            throw new Exception("empty");
        }
        minStack.pop();
        return dataStack.pop();
    }
    
    public Integer min() throws Exception {
        if (minStack.empty()) {
            throw new Exception("empty");
        }
        return minStack.peek();
    }
    
    // for test
    public static void main(String[] args) throws Exception {
        StackWithMin stack = new StackWithMin();
//        stack.pop();
        stack.push(3);
        stack.push(4);
        System.out.println(stack.min());
        stack.push(2);
        System.out.println(stack.min());
        stack.pop();
        stack.pop();

        System.out.println(stack.min());
        
    }
    
    
}
