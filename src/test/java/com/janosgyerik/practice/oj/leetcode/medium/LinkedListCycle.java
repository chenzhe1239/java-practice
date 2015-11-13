package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode node1 = head;
        ListNode node2 = head.next;
        while (node1 != null && node2 != null && node2.next != null) {
            if (node1 == node2) {
                return true;
            }
            node1 = node1.next;
            node2 = node2.next.next;
        }
        return false;
    }
}
