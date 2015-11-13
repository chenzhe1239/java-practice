package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

public class InsertionSortListTest {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            if (next.val < current.val) {
                current.next = next.next;
                head = insert(head, next);
            } else {
                current = next;
            }
        }
        return head;
    }

    ListNode insert(ListNode head, ListNode toInsert) {
        if (toInsert.val < head.val) {
            toInsert.next = head;
            return toInsert;
        }
        ListNode current = head;
        while (current.next.val <= toInsert.val) {
            current = current.next;
        }
        toInsert.next = current.next;
        current.next = toInsert;
        return head;
    }
}
