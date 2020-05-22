package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 示例 1:
 * 
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 
 * 示例 2:
 * 
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 
 * 
 * @author zzk
 *
 */
public class ReorderList_143 {

	public static void reorderList(ListNode head) {
		List<ListNode> list = new ArrayList<>();
		while (head != null) {
			ListNode next = head.next;
			head.next = null;
			list.add(head);
			head = next;
		}
		int length = list.size();
		ListNode result = new ListNode(0);
		ListNode node = result;
		for (int i = 0; i < length >> 1; i++) {
			node.next = list.get(i);
			node.next.next = list.get(length - 1 - i);
			node = node.next.next;
		}
		if (length % 2 == 1) {
			node.next = list.get(length >> 1);
		}
		head = result.next;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.randomListNode(6, 10);
		System.out.println(node);
		reorderList(node);
		System.out.println(node);
	}
}
