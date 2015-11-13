package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode cycle = null;
        while (node1 != null && node2 != null && node2.next != null) {
            if (node1 == node2) {
                cycle = node1;
                break;
            }
            node1 = node1.next;
            node2 = node2.next.next;
        }

        if (cycle == null) {
            return null;
        }

        node1 = head;
        while (!cycleContains(cycle, node1)) {
            node1 = node1.next;
        }
        return node1;
    }

    public boolean cycleContains(ListNode cycle, ListNode candidate) {
        ListNode node = cycle;
        do {
            if (candidate == node) {
                return true;
            }
            node = node.next;
        } while (node != cycle);
        return false;
    }
}
