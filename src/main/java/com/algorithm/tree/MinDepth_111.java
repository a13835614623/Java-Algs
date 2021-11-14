package com.algorithm.tree;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:二叉树的最小深度
 * @Author: zzk
 * @Date: 2019-12-02 20:47
 */
public class MinDepth_111 {
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if(root.left==null)return minDepth(root.right)+1;
        if(root.right==null)return minDepth(root.left)+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left=new TreeNode(5);
//        node.right=new TreeNode(6);
        System.out.println(minDepth(node));
    }
}
