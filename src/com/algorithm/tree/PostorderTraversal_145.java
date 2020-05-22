package com.algorithm.tree;

import jdk.nashorn.internal.ir.IfNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description:
 * @Author: zzk
 * @Date: 2019-12-04 15:18
 */
public class PostorderTraversal_145 {
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode top = root, lastAccess = null;
        while (top != null || !stack.isEmpty()) {
            if (top != null) {
                stack.push(top);
                top = top.left;
            } else {
                top = stack.peek();
                //如果右子树访问结束,访问节点
                if (top.right == null && (top.left == lastAccess || top.left == null) || top.right == lastAccess) {
                    lastAccess = stack.pop();
                    result.add(lastAccess.val);
                    top = null;
                } else    //左边为空或者上一个访问的节点为左子树,转向访问右子树
                    if (top.left == null || top.left == lastAccess) {
                        if (top.right != null) {
                            top = top.right;
                        }
                    }
            }
        }
        return result;
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode top = root, lastAccess = null;
        while (top != null || !stack.isEmpty()) {
            if (top != null) {
                stack.push(top);
                top = top.left;
            } else {
                top = stack.peek();
                if (top.right != null&&top.right != lastAccess) {
                    top = top.right;
                } else {
                    result.add(top.val);
                    lastAccess=top;
                    stack.pop();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.left.right = new TreeNode(4);
        node.left.right = new TreeNode(6);
        node.right = new TreeNode(7);
        node.right.left = new TreeNode(8);
        node.right.right = new TreeNode(9);
        List<Integer> list = postorderTraversal2(node);
        System.out.println(list);

    }
}
