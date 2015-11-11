package com.janosgyerik.practice.oj.leetcode.common;

public class ListNodeUtils {

    private ListNodeUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public static ListNode create(int... values) {
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

    public static String toString(ListNode head) {
        if (head == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (ListNode node = head; node != null; node = node.next) {
            builder.append(node.val).append("->");
        }
        return builder.substring(0, builder.length() - 2);
    }
}
