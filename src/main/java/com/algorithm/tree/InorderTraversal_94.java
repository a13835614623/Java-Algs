package com.algorithm.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description: 二叉树的中序遍历
 * @Author: zzk
 * @Date: 2019-11-15 10:40
 */
public class InorderTraversal_94 {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode top = root;
        while (top!=null||!stack.isEmpty()){
            while (top!=null){
                stack.push(top);
                top =top.left;
            }
            top = stack.pop();
            list.add(top.val);
            top=top.right;
        }
        return list;
    }
    public static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        TreeNode treeNode = BuildTree_105.buildTree(preOrder, inOrder);
        System.out.println(inorderTraversal(treeNode));
    }
}
