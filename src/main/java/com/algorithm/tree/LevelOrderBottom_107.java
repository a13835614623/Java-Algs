package com.algorithm.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:二叉树的层次遍历 II
 * @Author: zzk
 * @Date: 2019-12-02 19:40
 */
public class LevelOrderBottom_107 {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        result.offerFirst(Arrays.asList(root.val));
        return func(Arrays.asList(root.left, root.right), result);
    }
    public static List<List<Integer>> func(List<TreeNode> nodes, LinkedList<List<Integer>> lists) {
        if (nodes == null || nodes.size() == 0) return lists;
        List<Integer> list = new ArrayList<>();
        List<TreeNode> childNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            if (node != null) {
                list.add(node.val);
                if (node.left != null)
                    childNodes.add(node.left);
                if (node.right != null)
                    childNodes.add(node.right);
            }
        }
        if (list.size() > 0)
            lists.offerFirst(list);
        return func(childNodes, lists);
    }
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(7);
        System.out.println(levelOrderBottom(node));
    }
}
