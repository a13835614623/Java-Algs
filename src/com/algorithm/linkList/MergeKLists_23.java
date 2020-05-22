package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algorithm.linkList.util.ListNode;

/**
 * 
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入: [ 1->4->5, 1->3->4, 2->6 ] 输出: 1->1->2->3->4->4->5->6
 * 
 * 
 */
public class MergeKLists_23 {
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		List<Integer> list = new ArrayList<>();
		for (ListNode listNode : lists) {
			while (listNode != null) {
				list.add(listNode.val);
				listNode = listNode.next;
			}
		}
		Collections.sort(list);
		return array2ListNode(list);
	}

	public static ListNode array2ListNode(List<Integer> list) {
		ListNode result = null;
		ListNode listNode = null;
		if (list == null || list.size() == 0)
			return null;
		for (Integer i : list) {
			if (listNode == null)
				result = listNode = new ListNode(i);
			else {
				listNode.next = new ListNode(i);
				listNode = listNode.next;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		ListNode[] lists = { l1, l2 };
		ListNode list = mergeKLists(lists);
		System.out.println(list);
	}
}
