package com.algorithm.stack;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * <p>
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:最小栈
 * @Author: zzk
 * @Date: 2019-12-15 10:51
 */
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack=new Stack<>();
    }

    public void push(int x) {
        if (stack.empty()||x<=getMin()){
            minStack.push(x);
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop()==getMin()){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

public class MinStack_155 {
    public static void main(String[] args) {
    }
}
