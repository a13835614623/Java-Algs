package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * 
 * 示例:
 * 
 * 输入: 1->2->6->3->4->5->6, val = 6 输出: 1->2->3->4->5
 * 
 * 
 * @author zzk
 *
 */
public class RemoveElements_203 {
	public static ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return null;
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode node = result;
		while (node.next != null) {
			ListNode next = node.next;
			if (next.val == val) {
				node.next = next.next;
			}else{
				node = next;
			}
		}
		return result.next;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.array2ListNode(1,1,1,1,2,1);
		System.out.println(removeElements(node, 1));
	}
}
