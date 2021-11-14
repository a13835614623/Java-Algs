package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 
 * 示例:
 * 
 * 输入: head = 1->4->3->2->5->2, x = 3 输出: 1->2->2->4->3->5
 *
 * 
 * 
 */
public class Partition_86 {

	public static ListNode partition(ListNode head, int x) {
		ListNode left = new ListNode(0);
		ListNode lTail = left;
		ListNode right = new ListNode(0);
		ListNode rTail = right;
		ListNode node = head;
		while (node != null) {
			if (node.val < x) {
				lTail = lTail.next = new ListNode(node.val);
			} else {
				rTail = rTail.next = new ListNode(node.val);
			}
			node = node.next;
		}
		lTail.next = right.next;
		return left.next;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 10; i >0; i--) {
			list.add(i);
			if (i == 1 || i == 5 | i == 9) {
				list.add(i);
			}
		}
		ListNode l = partition(array2ListNode(list),5);
		System.out.println(l);
	}

	public static ListNode array2ListNode(List<Integer> list) {
		ListNode result = null;
		ListNode listNode = null;
		if (list == null || list.size() == 0)
			return null;
		for (Integer i : list) {
			if (listNode == null)
				result = listNode = new ListNode(i);
			else {
				listNode.next = new ListNode(i);
				listNode = listNode.next;
			}
		}
		return result;
	}
}
