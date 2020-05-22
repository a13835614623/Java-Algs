package com.algorithm.linkList.util;
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode list = this;
		while (list != null) {
			sb.append(list.val + "->");
			list = list.next;
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
}
