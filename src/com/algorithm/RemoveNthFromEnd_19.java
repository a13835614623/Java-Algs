package com.algorithm;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * 示例：
 * 
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 
 * 说明：
 * 
 * 给定的 n 保证是有效的。
 * 
 * @author zzk
 *
 */
public class RemoveNthFromEnd_19 {

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode node = head;
		int i = 0;
		while (node != null) {
			node = node.next;
			i++;
		}
		int j = i - n;
		node = head;
		if (i == n)
			return head.next;
		i = 0;
		ListNode result = node;
		while (node != null) {
			if (i == j - 1) {
				node.next = node.next.next;
                break;
			}
			node = node.next;
			i++;
		}
		// out(result);
		return result;
	}

	public static ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode node = head;
		ListNode result = node;
		ListNode listnodes[] = new ListNode[100];
		int i = 0;
		while (node != null) {
			listnodes[i]=node;
			node = node.next;
			i++;
		}
		if (i == n)
			return head.next;
		node = listnodes[i - n - 1];
		node.next = node.next.next;
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
		ListNode list6 = new ListNode(6);
		list5.next = list6;
		list4.next = list5;
		list3.next = list4;
		list2.next = list3;
		list.next = list2;
		out(removeNthFromEnd2(list, 2));
	}
}
