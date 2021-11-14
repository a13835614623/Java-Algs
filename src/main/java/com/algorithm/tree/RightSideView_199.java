package com.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:二叉树的右视图
 * @Author: zzk
 * @Date: 2019-12-10 21:07
 */
public class RightSideView_199 {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null)return result;
        result.add(root.val);
        func(Arrays.asList(root), result);
        return result;
    }

    public static void func(List<TreeNode> parentNodes, List<Integer> rightestList) {
        if (parentNodes == null || parentNodes.size() == 0) return;
        List<TreeNode> childNodes = new ArrayList<>();
        for (int i = 0, len = parentNodes.size(); i < len; i++) {
            TreeNode node = parentNodes.get(i);
            if (node == null) continue;
            if (node.left != null)
                childNodes.add(node.left);
            if (node.right != null)
                childNodes.add(node.right);
        }
        int size = childNodes.size();
        if (size > 0) {
            rightestList.add(childNodes.get(size - 1).val);
        }
        func(childNodes, rightestList);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(7);
        System.out.println(rightSideView(node));
    }
}
