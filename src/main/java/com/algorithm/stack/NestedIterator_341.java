package com.algorithm.stack;

import java.util.*;

/**
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:扁平化嵌套列表迭代器
 */
public class NestedIterator_341 implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator_341(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        if (nestedList.size() == 0) return;
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty())return false;
        NestedInteger top = stack.peek();
        if (top.isInteger()) {
            return true;
        } else {
            stack.pop();
            List<NestedInteger> list = top.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
            return hasNext();
        }
    }

    public static void main(String[] args) {
        NestedInteger nestedInteger1 = new MyNestedInteger(1);
        NestedInteger nestedInteger2 = new MyNestedInteger(2);
        NestedInteger nestedInteger3 = new MyNestedInteger(Arrays.asList(nestedInteger1, nestedInteger2));
        NestedInteger list = new MyNestedInteger(Arrays.asList(nestedInteger1, nestedInteger2, nestedInteger3));
        NestedIterator_341 iterator341 = new NestedIterator_341(Arrays.asList(list));

        while (iterator341.hasNext()) {
            System.out.println(iterator341.next());
        }

    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class MyNestedInteger implements NestedInteger {

        Integer integer;
        List<NestedInteger> list;

        public MyNestedInteger(Integer integer) {
            this.integer = integer;
        }

        public MyNestedInteger(List<NestedInteger> list) {
            this.list = list;
        }

        @Override
        public boolean isInteger() {
            return integer != null;
        }

        @Override
        public Integer getInteger() {
            return integer;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
}
