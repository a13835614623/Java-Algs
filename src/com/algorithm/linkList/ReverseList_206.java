package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 反转一个单链表。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
 * 
 * @author zzk
 *
 */
public class ReverseList_206 {
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
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ListNode list = ListNodeUtil.array2ListNode(arr);
		System.out.println(reverseList(list));
	}
}
