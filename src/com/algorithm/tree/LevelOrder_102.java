package com.algorithm.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:二叉树的层次遍历
 * @Author: zzk
 * @Date: 2019-12-02 13:14
 */
public class LevelOrder_102 {
    public static List<List<Integer>> func(List<TreeNode> nodes, List<List<Integer>> lists) {
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
            lists.add(list);
        return func(childNodes, lists);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        result.add(Arrays.asList(root.val));
        return func(Arrays.asList(root.left, root.right), result);
    }
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(7);
        System.out.println(levelOrder(node));
    }
}
