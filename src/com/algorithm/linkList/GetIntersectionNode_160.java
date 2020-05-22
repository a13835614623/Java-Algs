package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 
 * @author zzk 注意：
 * 
 *         如果两个链表没有交点，返回 null. 在返回结果后，两个链表仍须保持原有的结构。 可假定整个链表结构中没有循环。 程序尽量满足
 *         O(n)时间复杂度，且仅用 O(1) 内存。
 * 
 */
public class GetIntersectionNode_160 {
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		int len1 = getListNodeLength(headA);
		int len2 = getListNodeLength(headB);
		int len = Math.abs(len2 - len1);
		boolean flag = len1 > len2;
		ListNode nodeLong = flag ? headA : headB;
		ListNode nodeSmall = flag ? headB : headA;
		while (len > 0) {
			nodeLong = nodeLong.next;
			len--;
		}
		while (nodeSmall != null) {
			if (nodeLong == nodeSmall) {
				return nodeSmall;
			}
			nodeLong = nodeLong.next;
			nodeSmall = nodeSmall.next;
		}
		return null;
	}

	/**
	 * 得到链表长度
	 * 
	 * @param head
	 * @return 长度值
	 */
	public static int getListNodeLength(ListNode head) {
		ListNode node = head;
		int len = 0;
		while (node != null) {
			node = node.next;
			len++;
		}
		return len;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.array2ListNode(1, 2, 3, 4, 5, 6);
		ListNode node2 = ListNodeUtil.array2ListNode(1, 3, 2);
		node2.next = node;
		System.out.println(getIntersectionNode(node, node2));
	}
}
