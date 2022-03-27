package com.algorithm.tree;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description: 验证二叉搜索树
 * @Author: zzk
 * @Date: 2019-11-29 12:50
 */
public class IsValidBST_98 {
    public static boolean isValidBST(TreeNode root) {
        return func(root,null,null);
    }

    public static boolean func(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        if (low != null && root.val <= low) return false;
        if (high != null && root.val >= high) return false;
        if (!func(root.right,root.val,high))return false;
        if (!func(root.left,low,root.val))return false;
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
//        root.left.right.right = new TreeNode(3);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        System.out.println(isValidBST(root));
    }
}
