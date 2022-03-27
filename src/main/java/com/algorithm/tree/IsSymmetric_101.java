package com.algorithm.tree;

/**
 * @Description:
 * @Author: zzk
 * @Date: 2019-12-01 21:20
 */
public class IsSymmetric_101 {
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left,root.right);
    }

    public static boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(5);
        p.left.left=new TreeNode(4);
        p.left.right=new TreeNode(2);
        p.right = new TreeNode(5);
        p.right.right=new TreeNode(4);
        p.right.left=new TreeNode(2);
        System.out.println(isSymmetric(p));
    }
}
