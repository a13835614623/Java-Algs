package com.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:二叉树的锯齿形层次遍历
 * @Author: zzk
 * @Date: 2019-12-02 13:33
 */
public class ZigzagLevelOrder_103 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        result.add(Arrays.asList(root.val));
        return func(Arrays.asList(root.left, root.right), result);
    }

    public static List<List<Integer>> func(List<TreeNode> nodes, List<List<Integer>> lists) {
        if (nodes == null || nodes.size() == 0) return lists;
        List<Integer> list = new ArrayList<>();
        List<TreeNode> childNodes = new ArrayList<>(nodes.size() * 2);
        boolean isL2R = lists.size() % 2 == 0;
        int len = nodes.size();
        for (int i = 0; i < len; i++) {
            TreeNode node = nodes.get(isL2R ? i : (len - 1 - i));
            if (node != null) list.add(node.val);
        }
        for (int i = 0; i < len; i++) {
            TreeNode node = nodes.get(i);
            if (node == null) continue;
            childNodes.add(node.left);
            childNodes.add(node.right);
        }
        if (list.size() > 0) {
            lists.add(list);
        }
        return func(childNodes, lists);
    }

    public static void main(String[] args) {

    }
}
