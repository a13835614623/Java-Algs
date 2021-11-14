package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 
 * 
 * 示例:
 * 
 * 给定 1->2->3->4->5->6, 你应该返回 2->1->4->3->6->5.
 * 
 * 
 * @author zzk
 *
 */
public class SwapPairs_24 {
	public static ListNode swapPairs(ListNode head) {
		ListNode result = null;
		if (head == null || head.next == null)
			return head;
		ListNode tmp = null;
		ListNode curNode = head;
		while (curNode != null && curNode.next != null) {
			ListNode next = curNode.next;// 保存2
			curNode.next = next.next;// 1指向3
			next.next = curNode;// 2指向1
			if (result == null) {
				result = tmp = next;
			} else {
				tmp.next.next = next;
				tmp = next;
			}
			curNode = curNode.next;
			// out(result);
		}
		return result;
	}

	public static ListNode getNode(ListNode node, int index) {
		int i = 0;
		ListNode resultNode = node;
		while (i < index) {
			if (resultNode != null) {
				resultNode = resultNode.next;
				i++;
			} else {
				return null;
			}
		}
		return resultNode;
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
		ListNode list6 = new ListNode(6);
		list5.next = list6;
		list4.next = list5;
		list3.next = list4;
		list2.next = list3;
		list.next = list2;
		// out(list);
		System.out.println("-----------------------------------");
		out(swapPairs(list));
	}
}
