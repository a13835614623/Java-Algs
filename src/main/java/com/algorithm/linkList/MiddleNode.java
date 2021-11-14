package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

public class MiddleNode {
	public static ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.array2ListNode(1);
		System.out.println(middleNode(node));
	}
}
