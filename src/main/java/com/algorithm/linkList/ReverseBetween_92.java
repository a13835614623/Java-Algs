package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明: 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4 输出: 1->4->3->2->5->NULL
 * 
 * @author zzk
 */
public class ReverseBetween_92 {
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null||m==n)
			return head;
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode node = head;// 移动变量
		int i = 1;
		ListNode Head = null;
		ListNode Tail = null;
		if (m == 1) {
			Head = result;
		} 
		while (node != null) {
			ListNode next = node.next;
			if (i == m - 1) {
				Head = node;
			} else if (i == m) {
				Tail = Head.next = node;
				node.next = null;
			} else if (i > m && i <= n) {
				node.next = Head.next;
				Head.next = node;
				if (i == n) {
					Tail.next = next;
				}
			}
			node = next;
			i++;
		}
		return result.next;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ListNode list = ListNodeUtil.array2ListNode(arr);
		System.out.println(reverseBetween(list, 2, 3));
	}
}
