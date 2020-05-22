package com.algorithm.tree;
/**
 * 二叉树
 * @author zzk
 *
 */
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	public static TreeNode newNode(int val) {
		return new TreeNode(val);
	}
	@Override
	public String toString() {
		return "[" + val + "," + left + "," + right + "]";
	}
}