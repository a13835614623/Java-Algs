package com.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zzk
 * @Date: 2019-12-02 21:39
 */
public class PathSum_113 {
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        return func(root, sum, new ArrayList<>());
    }

    public static List<List<Integer>> func(TreeNode root, int sum, List<Integer> preList) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        sum -= root.val;
        preList.add(root.val);
        if (sum == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(preList));
        } else {
            if (root.left == null) result.addAll(func(root.right, sum, preList));
            else if (root.right == null) result.addAll(func(root.left, sum, preList));
            else {
                List<List<Integer>> leftList = func(root.left, sum, preList);
                List<List<Integer>> rightList = func(root.right, sum, preList);
                result.addAll(leftList);
                result.addAll(rightList);
            }
        }
        preList.remove(preList.size() - 1);
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.left.left = new TreeNode(11);
        node.left.left.left = new TreeNode(7);
        node.left.left.right = new TreeNode(2);
        node.right = new TreeNode(8);
        node.right.left = new TreeNode(13);
        node.right.right = new TreeNode(4);
        node.right.right.left = new TreeNode(5);
        node.right.right.right = new TreeNode(1);
        System.out.println(pathSum(node, 22));
    }
}
