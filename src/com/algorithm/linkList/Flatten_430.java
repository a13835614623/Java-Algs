package com.algorithm.linkList;

import java.util.Random;

public class Flatten_430 {
	static class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;

		public Node() {
		}

		public Node(int _val, Node _prev, Node _next, Node _child) {
			val = _val;
			prev = _prev;
			next = _next;
			child = _child;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node list = this;
			while (list != null) {
				sb.append(list.val + "--");
				list = list.next;
			}
			sb.delete(sb.length() - 2, sb.length());
			return sb.toString();
		}

	}

	public static Node flatten(Node head) {
		Node cur = head;
		while (cur != null) {
			Node childHead = cur.child;
			cur.child = null;
			Node next = cur.next;
			if (childHead != null) {
				childHead.prev = cur;
				cur.next = childHead;
				Node childTail = tail(flatten(childHead));
				if (next != null){
					childTail.next = next;
					next.prev = childTail;
				}
			}
			cur = next;
		}
		return head;
	}
	/**
	 * 获取链表的尾部
	 * @param head
	 * @return
	 */
	public static Node tail(Node head) {
		if (head == null)
			return null;
		Node cur = head;
		while (cur.next != null) {
			cur = cur.next;
		}
		return cur;
	}

	public static Node getNode(int length) {
		Node result = new Node();
		result.val = 0;
		Random r = new Random();
		Node tail = result;
		result.val = 0;
		int i = 0;
		while (i < length) {
			Node newNode = new Node();
			newNode.val = r.nextInt(length);
			newNode.prev = tail;
			tail = tail.next = newNode;
			i++;
		}
		result.next.prev = null;
		return result.next;
	}

	public static void main(String[] args) {
		Node node = getNode(5);
		Node node2 = getNode(5);
		Node node3 = getNode(5);
		node.next.child = node2;
		node2.next.child = node3;
		System.out.println(node);
		System.out.println(node2);
		System.out.println(node3);
		System.out.println("-----------------------------");
		System.out.println(flatten(node));
	}
}
