package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 对链表进行插入排序。 插入排序算法：
 * 
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 重复直到所有输入数据插入完为止。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入: 4->2->1->3 输出: 1->2->3->4
 * 
 * 示例 2：
 * 
 * 输入: -1->5->3->4->0 输出: -1->0->3->4->5
 * 
 * @author zzk
 *
 */
public class InsertionSortList_147 {
	public static ListNode insertionSortList(ListNode head) {
		if (head == null)
			return null;
		ListNode result = new ListNode(0);
		ListNode node = head;
		ListNode node2 = null;
		while (node != null) {
			ListNode nodeNext = node.next;
			if (result.next == null) {
				node2 = node;
				node2.next = null;
				result.next = node2;
			} else {
				node2 = result.next;
				while (node2 != null) {
					if (node.val > node2.val) {
						if (node2.next == null) {
							node2.next = node;
							node.next = null;
							break;
						} else if (node2.next.val >= node.val) {
							ListNode next = node2.next;
							node2.next = node;
							node.next = next;
							break;
						}
					} else {
						node.next = node2;
						result.next = node;
						break;
					}
					node2 = node2.next;
				}
			}
			node = nodeNext;
		}
		return result.next;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.randomListNode(6, 10);
		System.out.println(node);
		System.out.println(insertionSortList(node));
	}
}
