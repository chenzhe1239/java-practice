package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortListConstantSpaceTest {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode node = head.next;
        while (node != null) {
            if (node.val < prev.val) {
                ListNode nextBackup = node.next;
                head = insert(head, node);
                prev.next = node = nextBackup;
            } else {
                prev = node;
                node = node.next;
            }
        }
        return head;
    }

    private ListNode insert(ListNode head, ListNode target) {
        if (target.val < head.val) {
            target.next = head;
            return target;
        }

        ListNode node = head;
        while (node.next.val <= target.val) {
            node = node.next;
        }
        target.next = node.next;
        node.next = target;

        return head;
    }
}
