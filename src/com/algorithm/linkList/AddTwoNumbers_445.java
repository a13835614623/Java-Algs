package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 
 * 
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 进阶:
 * 
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 
 * 示例:
 * 
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) 输出: 7 -> 8 -> 0 -> 7
 * 
 * 
 * @author zzk
 *
 */
public class AddTwoNumbers_445 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		StringBuilder sb1 = list2String(l1).reverse();
		StringBuilder sb2 = list2String(l2).reverse();
		int len1 = sb1.length(), len2 = sb2.length();
		int maxLen = Math.max(len1, len2);
		int carry = 0;// 进位值
		int p, q, sum;
		for (int i = 0; i < maxLen; i++) {
			p = i > len1 - 1 ? 0 : sb1.charAt(i) - '0';
			q = i > len2 - 1 ? 0 : sb2.charAt(i) - '0';
			sum = p + q + carry;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			node.next = result.next;
			result.next = node;
		}
		if (carry > 0) {
			ListNode node = new ListNode(carry);
			node.next = result.next;
			result.next = node;
		}
		return result.next;
	}

	public static StringBuilder list2String(ListNode head) {
		StringBuilder sb = new StringBuilder();
		ListNode p = head;
		while (p != null) {
			sb.append(p.val);
			p = p.next;
		}
		return sb;
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
		System.out.println(addTwoNumbers(newListNode(111), newListNode(445454)));
	}
}
