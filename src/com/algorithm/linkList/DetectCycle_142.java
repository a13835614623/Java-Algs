package com.algorithm.linkList;

import java.util.HashSet;
import java.util.Set;

import com.algorithm.linkList.util.ListNode;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 
 * 说明：不允许修改给定的链表。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：head = [3,2,0,-4], pos = 1 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2], pos = 0 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 
 * 示例 3：
 * 
 * 输入：head = [1], pos = -1 输出：no cycle 解释：链表中没有环。
 * 
 * 
 * @author zzk
 *
 */
public class DetectCycle_142 {
	/**
	 * 暴力法
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		while (head != null) {
			if (set.contains(head)) {
				return head;
			}
			set.add(head);
			head = head.next;
		}
		return null;
	}
	/**
	 * 快慢双指针，快指针速度等于慢指针书店二倍
	 * @param head
	 * @return
	 */
	public ListNode detectCycle2(ListNode head) {
		ListNode fast = head.next;
		ListNode slow = head;
		boolean hasCycle = false;// 是否存在环
		while (slow.next != null && fast.next.next != null) {
			if (slow == fast) {
				hasCycle = true;
				break;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		if (hasCycle) {
			ListNode node = head;
			while (node != slow) {
				slow = slow.next;
				node = node.next;
			}
			return node;
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
