package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->3->4->4->5 输出: 1->2->5
 * 
 * 示例 2:
 * 
 * 输入: 1->1->1->2->3 输出: 2->3
 * 
 * 
 * @author zzk
 *
 */
public class DeleteDuplicates_82 {
	// 1 1 2 2 3 3 4 4
	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode result = new ListNode(0);
		ListNode node = head;
		ListNode temp = result;// 指向result的链表尾部
		while (node != null) {
			boolean isDuplicate = false;
			while (node.next != null && node.val == node.next.val) {
				isDuplicate = true;
				node = node.next;
			}
			if (!isDuplicate) {// 没有发生重复
				temp = temp.next = node;
			} else {
				temp.next = null;
			}
			node = node.next;
		}
		return result.next;
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

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			list.add(i);
			if (i == 1 || i == 5 | i == 9) {
				list.add(i);
			}
		}
		ListNode l = deleteDuplicates(array2ListNode(list));
		System.out.println(l);
	}
}
