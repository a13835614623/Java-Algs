package com.algorithm.tree;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * <p>
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description: 二叉树展开为链表
 * @Author: zzk
 * @Date: 2019-12-03 21:25
 */
public class Flatten_114 {
    public static void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        getRightest(root).right = right;
    }
    public static void flatten2(TreeNode root) {
        while (root != null) {
            if(root.left == null) {
                root = root.right;
            }else {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }
    public static TreeNode getRightest(TreeNode root) {
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(5);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);
        flatten(root);
        System.out.println(root);
    }
}
