package com.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:二叉搜索树的最近公共祖先
 * @Author: zzk
 * @Date: 2019-12-11 12:18
 */
public class LowestCommonAncestor_235 {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = p.val, max = q.val;
        if (min > max) {
            min = q.val;
            max = p.val;
        }
        return helper(root,min,max);
    }
    public static TreeNode helper(TreeNode root, int min, int max) {
        int val = root.val;
        if (min > val)
            return helper(root.right, min, max);
        if (max < val)
            return helper(root.left, min, max);
        return root;
    }
    public static TreeNode newNode(int val) {
        return new TreeNode(val);
    }

    public static void main(String[] args) {
        TreeNode node = newNode(6);
        node.left = newNode(2);
        node.left.left = newNode(0);
        node.left.right = newNode(4);
        node.left.right.left = newNode(3);
        node.left.right.right = newNode(5);
        node.right = newNode(8);
        node.right.left = newNode(7);
        node.right.right = newNode(9);
        System.out.println(lowestCommonAncestor(node, node.left.right.right, node.left.left));
    }
}
