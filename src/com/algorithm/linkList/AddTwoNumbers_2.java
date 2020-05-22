package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;

/**
 * 
 * zzk 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * 
 */
public class AddTwoNumbers_2 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		ListNode listNode = result;
		ListNode p = l1;
		ListNode q = l2;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			listNode.next = new ListNode(sum % 10);
			listNode = listNode.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			listNode.next = new ListNode(carry);
		}
		return result.next;
	}

	public static ListNode newListNode(long num) {// 564
		if (num < 10) {
			return new ListNode((int) num);
		} else {
			ListNode listNode = new ListNode((int) (num % 10));// 4
			listNode.next = newListNode(num / 10);// 6
			return listNode;
		}
	}

	public static void main(String[] args) {
		ListNode l = addTwoNumbers(newListNode(1), newListNode(99));
		System.out.println(l);
	}
}