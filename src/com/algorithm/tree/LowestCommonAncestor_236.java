package com.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:二叉树的最近公共祖先
 * @Author: zzk
 * @Date: 2019-12-11 12:18
 */
public class LowestCommonAncestor_236 {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();
        find(root, p, pList);
        find(root, q, qList);
        TreeNode result = null, pp = null, qq = null;
        int len = Math.min(pList.size(), qList.size());
        for (int i = 0, j = 0; i < len; i++, j++) {
            pp = pList.get(i);
            qq = qList.get(i);
            if (pp != qq) break;
            result =pp;
        }
        return result;
    }

    public static boolean find(TreeNode root, TreeNode p, List<TreeNode> list) {
        if (root == null) return false;
        list.add(root);
        if (root == p) return true;
        else {
            boolean findLeft = find(root.left, p, list);
            if (findLeft) return true;
            boolean findRight = find(root.right, p, list);
            if (findRight) return true;
            list.remove(list.size() - 1);
        }
        return false;
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
        System.out.println(lowestCommonAncestor(node, node.right.right, node.left.left));
    }
}
