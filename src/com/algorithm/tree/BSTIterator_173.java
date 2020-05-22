package com.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * @Description:二叉搜索树迭代器
 * @Author: zzk
 * @Date: 2019-12-10 13:42
 */
class BSTIterator {
    List<Integer> list;
    int size;
    int index;

    public BSTIterator(TreeNode root) {
        list = inorderTraversal(root);
        size=list.size();
        index = 0;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode top = root;
        while (top != null || !stack.isEmpty()) {
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
            top = stack.pop();
            list.add(top.val);
            top = top.right;
        }
        return list;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        index%=size;
        return list.get(index++);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return index < size;
    }
}

public class BSTIterator_173 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // 返回 3
        System.out.println(iterator.next());    // 返回 7
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 9
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 15
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 20
        System.out.println(iterator.hasNext()); // 返回 false
    }
}
