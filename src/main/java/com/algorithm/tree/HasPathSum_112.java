package com.algorithm.tree;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * <p>
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:路径总和
 * @Author: zzk
 * @Date: 2019-12-02 21:20
 */
public class HasPathSum_112 {

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        int x = sum - root.val;
        if (x == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, x) || hasPathSum(root.right, x);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left=new TreeNode(1);
        node.right=new TreeNode(4);
        node.left.right=new TreeNode(7);
        System.out.println(hasPathSum(node ,6));
    }

}
