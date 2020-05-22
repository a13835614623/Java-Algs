package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 
 * 示例 1:
 * 
 * 输入: 4->2->1->3 输出: 1->2->3->4
 * 
 * 示例 2:
 * 
 * 输入: -1->5->3->4->0 输出: -1->0->3->4->5
 * 
 * 
 * @author zzk
 *
 */
public class SortList_148 {
	public static ListNode sortList(ListNode head) {
		if (head == null||head.next==null)
			return head;
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode node = head;
		while (node.next != null) {
			ListNode next = node.next;// 要插入的节点
			if (next.val < node.val) {// 往前移动
				node.next = next.next;
				next.next = null;
				ListNode node2 = result.next;
				if (node2 == node || node2.val >= next.val) {// 插到最前面
					result.next = next;
					next.next = node2;
				} else {
					while (node2 != node) {//寻找插入点
						if (node2.val <= next.val && node2.next.val >= next.val) {
							next.next = node2.next;
							node2.next = next;
							break;
						}
						node2 = node2.next;
					}
				}
			}else{
				node = next;
			}
		}
		return result.next;
	}

	public static void main(String[] args) {
		ListNode node = ListNodeUtil.randomListNode(50000, 10);
		System.out.println(node);
		long start = System.currentTimeMillis();
		System.out.println(sortList(node));
		long end = System.currentTimeMillis();
		System.out.println("耗时:"+(end - start)+"ms");
	}
}
