package com.algorithm;


public class MyLinkedList {
	int val;
	MyLinkedList next;

	public MyLinkedList() {
		val = -1;
		next = null;
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index) {
		MyLinkedList list = getLinkedList(index);
		return list == null ? -1 : list.val;
	}

	public MyLinkedList getLinkedList(int index) {
		if (index < 0)
			return null;
		MyLinkedList list = this;
		while (index-- > 0) {
			if (list == null)
				return null;
			list = list.next;
		}
		return list;
	}

	/**
	 * Add a node of value val before the first element of the linked list.
	 * After the insertion, the new node will be the first node of the linked
	 * list.
	 */
	public void addAtHead(int val) {
		if (this.val == -1 && this.next == null) {
			this.val = val;
			return;
		}
		MyLinkedList list = new MyLinkedList();
		list.val = this.val;
		list.next = this.next;
		this.val = val;
		this.next = list;
	}

	public MyLinkedList tail() {
		MyLinkedList tail = this;
		while (tail.next != null) {
			tail = tail.next;
		}
		return tail;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		MyLinkedList newTail = new MyLinkedList();
		newTail.val = val;
		newTail.next = null;
		MyLinkedList tail = tail();
		tail.next = newTail;
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If
	 * index equals to the length of linked list, the node will be appended to
	 * the end of linked list. If index is greater than the length, the node
	 * will not be inserted.
	 */
	public void addAtIndex(int index, int val) {
		if (index < 0)
			return;
		if (index == 0)
			addAtHead(val);
		if (this.val == -1 && this.next == null) {
			return;
		}
		MyLinkedList last = getLinkedList(index - 1);
		if (last == null)
			return;
		if (last.next == null) {
			addAtTail(val);
			return;
		}
		MyLinkedList newList = new MyLinkedList();
		newList.val = val;
		newList.next = last.next;
		last.next = newList;
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		MyLinkedList last = getLinkedList(index - 1);
		if (last.next != null)
			last.next = last.next.next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		MyLinkedList list = this;
		while (list != null) {
			sb.append(list.val + "->");
			list = list.next;
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

	public static void main(String[] args) {
		MyLinkedList linkedList = new MyLinkedList();
		System.out.println(linkedList.get(0));
		linkedList.addAtIndex(1, 2);
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		linkedList.addAtIndex(0, 1);
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
	}
}
