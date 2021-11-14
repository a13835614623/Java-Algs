package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;
import com.algorithm.tree.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 
 * 示例:
 * 
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * 
 * 0 / \ -3 9 / / -10 5
 * 
 * @author zzk
 *
 */
public class SortedListToBST_142 {

	public static TreeNode sortedListToBST(ListNode head) {
		ListNode node = head;
		if (head == null)
			return null;
		List<Integer> listNodes = new ArrayList<>();
		while (node != null) {
			listNodes.add(node.val);
			node = node.next;
		}
		return getTreeNode(listNodes, 0, listNodes.size() - 1);
	}

	public static TreeNode getTreeNode(List<Integer> list, int left, int right) {
		int mid = (left + right) >> 1;
		TreeNode tree = new TreeNode(list.get(mid));
		if (right == left) {
			return tree;
		} else if (right - left == 1) {
			tree.left = null;
			tree.right = new TreeNode(list.get(right));
		} else if (right - left == 2) {
			tree.left = new TreeNode(list.get(left));
			tree.right = new TreeNode(list.get(right));
		} else {
			tree.left = getTreeNode(list, left, mid - 1);
			tree.right = getTreeNode(list, mid + 1, right);
		}
		return tree;
	}

	public static void main(String[] args) {
		int arr[] = { -10, -3, 0, 5, 9 };
		ListNode node = ListNodeUtil.array2ListNode(arr);
		System.out.println(sortedListToBST(node));
	}
}
