package com.janosgyerik.practice.oj.leetcode.hard.ReverseNodesInKGroup;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = head;
        ListNode prevTail = dummy;

        while (node != null) {
            ListNode tail = skip(node, k);
            if (tail == null) {
                break;
            }
            ListNode nextHead = tail.next;
            ListNode oldHead = node;

            prevTail.next = reverse(node, k);
            oldHead.next = nextHead;

            node = nextHead;
            prevTail = oldHead;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode node, int k) {
        ListNode newHead = node;
        for (int i = 0; i < k; ++i) {
            ListNode next = node.next;
            node.next = newHead;
            newHead = node;
            node = next;
        }
        return newHead;
    }

    private ListNode skip(ListNode node, int k) {
        ListNode runner = node;
        for (int i = 1; i < k && runner != null; ++i) {
            runner = runner.next;
        }
        return runner;
    }
}
