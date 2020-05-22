package com.algorithm.linkList;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 
 * 要求返回这个链表的深拷贝。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * 
 * 解释： 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 * 
 * @author zzk
 *
 */
class Node {
	public int val;
	public Node next;
	public Node random;

	public Node() {
	}

	public Node(int _val, Node _next, Node _random) {
		val = _val;
		next = _next;
		random = _random;
	}

	@Override
	public String toString() {
		return this.hashCode()+"";
	}

};

public class CopyRandomList_138 {
	public static Node copyRandomList(Node head) {
		if (head == null)
			return null;
		Node result = new Node();
		result.val = 0;
		Node tail = result;
		Node node = head;
		result.val = head.val;
		// 随机链表集合
		List<Node> randomNodeList = new ArrayList<>();
		// 新链表集合
		List<Node> newNodeList = new ArrayList<>();
		// 旧链表集合
		List<Node> oldNodeList = new ArrayList<>();
		while (node != null) {// 复制val和next
			Node next = node.next;
			Node newNode = new Node();
			newNode.val = node.val;
			newNode.random = node.random;
			tail.next = newNode;
			tail = tail.next;
			randomNodeList.add(node.random);
			newNodeList.add(newNode);
			oldNodeList.add(node);
			node = next;
		}
		for (Node node2 : newNodeList) {
			if (node2.random != null)
				node2.random = newNodeList.get(oldNodeList.indexOf(node2.random));
		}
		return result.next;
	}

	public static void main(String[] args) {
		Node node = new Node();
		Node node2 = new Node();
		node2.val = 2;
		node2.next = null;
		node2.random = node2;
		node.val = 1;
		node.next = node2;
		node.random = node2;
		copyRandomList(node);
	}
}
