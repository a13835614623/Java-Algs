package com.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * CQueue_9
 * @author 张子宽
 * @date 2022/07/09
 */
public class CQueue_9 {
    static class CQueue {
        Deque<Integer> inStack = new ArrayDeque<>();
        Deque<Integer> outStack = new ArrayDeque<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.isEmpty()) {
                if (inStack.isEmpty()) {
                    return -1;
                }
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

}
