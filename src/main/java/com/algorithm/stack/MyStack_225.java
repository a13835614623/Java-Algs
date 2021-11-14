package com.algorithm.stack;

import java.nio.channels.ServerSocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue=new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int size = queue.size(),i=0;
        queue.add(x);
        while (i++<size){
            queue.add(queue.remove());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

public class MyStack_225 {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();

    }
}
