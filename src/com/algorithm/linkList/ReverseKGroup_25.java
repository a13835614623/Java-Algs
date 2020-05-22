package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 
 * 示例 :
 * 
 * 给定这个链表：1->2->3->4->5
 * 
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 
 * 说明 :
 * 
 * 你的算法只能使用常数的额外空间。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * @author zzk
 *
 */
public class ReverseKGroup_25 {
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode result = new ListNode(0);
		ListNode node = head;
		ListNode Head = null;
		ListNode Tail = null;
		int i = 0;
		if (k == 1)
			return head;

		while (node != null) {
			ListNode next = node.next;
			if (i % k == 0) {
				Head = node;
			} else if (i % k == k - 1) {
				node.next = null;
				if (result.next == null) {
					result.next = reverseList(Head);
				} else {
					Tail.next = reverseList(Head);
				}
				Tail = Head;
				Tail.next = next;
			}
			node = next;
			i++;
		}
		if (i < k) {
			return head;
		}
		return result.next;
	}

	/**
	 * 将链表逆序
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverseList(ListNode head) {
		ListNode result = new ListNode(0);
		ListNode h = result;
		ListNode node = head;
		while (node != null) {
			ListNode next = node.next;
			h = result.next;
			result.next = node;
			node.next = h;
			node = next;
		}

		return result.next;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		System.out.println(reverseKGroup(array2ListNode(list), 20));
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
