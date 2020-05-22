package com.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:二叉树的所有路径
 * @Author: zzk
 * @Date: 2019-12-13 20:23
 */
public class BinaryTreePaths_257 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        find(root, null, result);
        return result;
    }

    public static void find(TreeNode root, StringBuilder base, List<String> list) {
        if (root == null) return;
        if (base == null) {
            base = new StringBuilder(root.val+"");
        } else {
            base.append("->").append(root.val);
        }
        String baseStr = base.toString();
        if (root.left == null && root.right == null) {
            list.add(base.toString());
        }else {
            find(root.left, base, list);
            find(root.right, new StringBuilder(baseStr), list);
        }
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
        System.out.println(binaryTreePaths(node));
    }
}
