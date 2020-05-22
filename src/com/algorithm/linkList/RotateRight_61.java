package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;

/**
 * 
 * @author zzk 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 *         示例 1:
 * 
 *         输入: 1->2->3->4->5->NULL, k = 2 输出: 4->5->1->2->3->NULL 解释: 向右旋转 1 步:
 *         5->1->2->3->4->NULL 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 *         示例 2:
 * 
 *         输入: 0->1->2->NULL, k = 4 输出: 2->0->1->NULL 解释: 向右旋转 1 步:
 *         2->0->1->NULL 向右旋转 2 步: 1->2->0->NULL 向右旋转 3 步: 0->1->2->NULL 向右旋转 4
 *         步: 2->0->1->NULL
 *
 *
 * 
 * 
 */
public class RotateRight_61 {

	public static ListNode rotateRight(ListNode head, int k) {
		if (k == 0 || head == null || head.next == null)
			return head;
		ListNode node = head;
		ListNode result = null;
		// 将链表改为循环链表
		int length = 0;
		while (node != null) {
			length++;
			if (node.next == null) {
				node.next = head;
				break;
			}
			node = node.next;
		}
		k = k % length;
		result = node = head;// 1,2,3,4,5,6,1,2,3,4...
		int i = length - k;
		while (i > 0) {
			result = result.next;
			if (i == 1) {
				node.next = null;
				break;
			}
			node = node.next;
			i--;
		}
		return result;
	}

	public static void out(ListNode node) {
		ListNode list = node;
		StringBuilder sb = new StringBuilder();
		while (list != null) {
			sb.append(list.val + "->");
			list = list.next;
		}
		if (sb.length() > 2)
			sb.delete(sb.length() - 2, sb.length());
		System.out.println(sb);
	}

	public static void main(String[] args) {
		ListNode list = new ListNode(1);
		ListNode list2 = new ListNode(2);
		ListNode list3 = new ListNode(3);
		ListNode list4 = new ListNode(4);
		ListNode list5 = new ListNode(5);
		// ListNode list6 = new ListNode(6);
		// list5.next = list6;
		list4.next = list5;
		list3.next = list4;
		list2.next = list3;
		list.next = list2;
		out(rotateRight(list, 1));
	}
}
