package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

public class ReverseLinkedList2Test {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }

        ListNode zero = new ListNode(0);
        zero.next = head;

        ListNode node = zero;
        for (int i = 1; i < m; ++i) {
            node = node.next;
        }
        ListNode beforeM = node;
        ListNode mth = node.next;
        ListNode reversedHead = node.next;

        for (int i = m; i < n; ++i) {
            ListNode newReversedHead = mth.next;
            mth.next = mth.next.next;
            newReversedHead.next = reversedHead;
            reversedHead = newReversedHead;
        }

        beforeM.next = reversedHead;

        return zero.next;
    }
}
