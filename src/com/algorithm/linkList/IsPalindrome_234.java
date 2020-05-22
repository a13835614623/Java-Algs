package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 请判断一个链表是否为回文链表。
 * 
 * 示例 1:
 * 
 * 输入: 1->2 输出: false
 * 
 * 示例 2:
 * 
 * 输入: 1->2->2->1 输出: true
 * 
 */
public class IsPalindrome_234 {
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = reverseList(slow.next);
		while (head != null && fast != null) {
			if (head.val != fast.val) {
				return false;
			}
			fast = fast.next;
			head = head.next;
		}
		return true;
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
		ListNode node = ListNodeUtil.array2ListNode(1,2,2,1);
		System.out.println(isPalindrome(node));
	}
}
