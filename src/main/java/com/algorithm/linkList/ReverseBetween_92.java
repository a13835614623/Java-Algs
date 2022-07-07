package com.algorithm.linkList;

import com.algorithm.linkList.util.ListNode;
import com.algorithm.linkList.util.ListNodeUtil;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明: 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4 输出: 1->4->3->2->5->NULL
 *
 * @author zzk
 */
public class ReverseBetween_92 {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n) {
            return head;
        }
        int i = 1;
        ListNode node = head;
        ListNode newTail = null;
        ListNode newHead = null;
        ListNode startPrev = null;
        while (i <= n) {
            ListNode next = node.next;
            if (i == m - 1) {
                startPrev = node;
            } else if (i == m) {
                newHead = newTail = node;
            } else if (i > m) {
                newTail.next = next;
                node.next = newHead;
                newHead = node;
                if (startPrev != null) {
                    startPrev.next = newHead;
                }
            }
            node = next;
            i++;
        }
        return m == 1 ? newHead : head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode list = ListNodeUtil.array2ListNode(arr);
        System.out.println(reverseBetween(list, 3, 4));
    }
}
