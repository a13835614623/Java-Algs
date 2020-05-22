package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 示例： 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
 *
 */
public class MergeTwoLists_21 {
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode node = new ListNode(0);
		ListNode result = node;
		ListNode tmpNode1 = l1;
		ListNode tmpNode2 = l2;
		while (tmpNode1 != null || tmpNode2 != null) {
			if (tmpNode1 == null) {
				node.next = tmpNode2;
				return result.next;
			} else if (tmpNode2 == null) {
				node.next = tmpNode1;
				return result.next;
			}
			if (tmpNode1.val < tmpNode2.val) {
				node.next = new ListNode(tmpNode1.val);
				tmpNode1 = tmpNode1.next;
			} else {
				node.next = new ListNode(tmpNode2.val);
				tmpNode2 = tmpNode2.next;
			}
			node = node.next;
		}
		return result.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		ListNode l = mergeTwoLists(l1, null);
		while (l != null) {
			System.out.print(l.val + "->");
			l = l.next;
		}
	}
}
