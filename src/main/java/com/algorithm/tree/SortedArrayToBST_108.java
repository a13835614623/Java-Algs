package com.algorithm.tree;

import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5


 * @author zzk
 *
 */
public class SortedArrayToBST_108 {

	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums==null){
			return null;
		}
		return getTreeNode(nums, 0, nums.length);
	}
	
	public static TreeNode getTreeNode(int[] arr, int left, int right) {
		int mid = (left + right) >> 1;
		TreeNode tree = new TreeNode(arr[mid]);
		if (right == left) {
			return tree;
		} else if (right - left == 1) {
			tree.left = null;
			tree.right = new TreeNode(arr[right]);
		} else if (right - left == 2) {
			tree.left = new TreeNode(arr[left]);
			tree.right = new TreeNode(arr[right]);
		} else {
			tree.left = getTreeNode(arr, left, mid - 1);
			tree.right = getTreeNode(arr, mid + 1, right);
		}
		return tree;
	}
	
	public static void main(String[] args) {
		ListNodeUtil.randomListNode(10, 10);
		
	}
}
