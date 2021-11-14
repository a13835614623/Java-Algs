package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 
 * 示例 1:
 * 
 * 输入: 1->1->2 输出: 1->2
 * 
 * 示例 2:
 * 
 * 输入: 1->1->2->3->3 输出: 1->2->3
 * 
 * 
 * @author zzk
 *
 */
public class DeleteDuplicates_83 {
	
	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode result = new ListNode(0);
		ListNode node = head;
		ListNode temp = result;// 指向result的链表尾部
		while (node != null) {
			while (node.next != null && node.val == node.next.val) {
				node = node.next;
			}
			temp = temp.next = node;
			node = node.next;
		}
		return result.next;
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
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			list.add(i);
			if (i == 1 || i == 5 | i == 9) {
				list.add(i);
			}
		}
		ListNode l = deleteDuplicates(array2ListNode(list));
		System.out.println(l);
	}
}
