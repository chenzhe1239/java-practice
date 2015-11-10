package com.janosgyerik.practice.oj.leetcode.common;

public class ListNodeUtils {
    static ListNode create(int... values) {
        if (values.length == 0) {
            return null;
        }
        ListNode head = new ListNode(values[0]);
        return head;
    }
}
