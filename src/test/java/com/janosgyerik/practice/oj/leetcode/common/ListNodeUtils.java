package com.janosgyerik.practice.oj.leetcode.common;

public class ListNodeUtils {
    static ListNode create(int... values) {
        if (values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode node = head;
        for (int i = 1; i < values.length; ++i) {
            node.next = new ListNode(values[i]);
            node = node.next;
        }

        return head;
    }
}
