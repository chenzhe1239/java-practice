package com.janosgyerik.practice.oj.leetcode.medium.PartitionList;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

import java.util.function.Predicate;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        return concat(filter(head, e -> e < x), filter(head, e -> e >= x));
    }

    protected static ListNode concat(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        last(head1).next = head2;
        return head1;
    }

    private static ListNode last(ListNode head) {
        assert head != null;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    protected static ListNode filter(ListNode head, Predicate<? super Integer> predicate) {
        ListNode result = new ListNode(0);  // dummy
        ListNode runner = result;
        for (ListNode node = head; node != null; node = node.next) {
            if (predicate.test(node.val)) {
                runner.next = new ListNode(node.val);
                runner = runner.next;
            }
        }
        return result.next;
    }
}
