package com.algorithm.linkList.util;

import java.util.List;
import java.util.Random;

/**
 * 链表工具类
 * @author zzk
 *
 */
public class ListNodeUtil {
	/**
	 * 将list转为链表
	 * @param list
	 * @return
	 */
	public static ListNode list2ListNode(List<Integer> list) {
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
	/**
	 * 将数组转为链表
	 * @param list
	 * @return
	 */
	public static ListNode array2ListNode(int ...list) {
		ListNode result = null;
		ListNode listNode = null;
		if (list == null || list.length == 0)
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
	/**
	 * 生成随机链表
	 * @param length 链表长度
	 * @param bound 链表元素范围
	 */
	public static ListNode randomListNode(int length,int bound){
		Random r = new Random();
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[i]=r.nextInt(bound);
		}
		return array2ListNode(list);
	}
	/**
	 * 返回链表长度
	 * @param head 链表
	 * @return 链表长度
	 */
	public static int length(ListNode head){
		int length=0;
		ListNode node = head;
		while(node!=null){
			node=node.next;
			length++;
		}
		return length;
	}
	/**
	 * 获取链表尾
	 * @param head 链表头
	 * @return 链表尾部
	 */
	public static ListNode tail(ListNode head) {
		if (head == null)
			return null;
		ListNode cur = head;
		while (cur.next != null) {
			cur = cur.next;
		}
		return cur;
	}

}
