package cxq.aimatoffer.question7.twostackqueue;

import java.util.Stack;

public class TwoStackQueue<T> {
    private Stack<T> add_stack = null;
    private Stack<T> delete_stack = null;
    
    public TwoStackQueue() {
        add_stack = new Stack<T>();
        delete_stack = new Stack<T>();
    }
    
    public void appendTail(T element) {
        add_stack.push(element);
    }
    
    public T deleteHead() {
        if (delete_stack.isEmpty() && add_stack.isEmpty()) {
            return null;
        }
        if (delete_stack.isEmpty()) {
            while (!add_stack.isEmpty()) {
                delete_stack.push(add_stack.pop());
            }
        }
        return delete_stack.pop();
    }

    // for test
    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();
        for (int i=0; i < 5; i++) {
            queue.appendTail(i);
        }
        for (int i=0; i < 6; i++) {
            Integer a = queue.deleteHead();
            System.out.print(a);
        }
        for (int i=5; i < 10; i++) {
            queue.appendTail(i);
        }
        for (int i=0; i < 5; i++) {
            int a = queue.deleteHead();
            System.out.print(a);
        }

    }

}
