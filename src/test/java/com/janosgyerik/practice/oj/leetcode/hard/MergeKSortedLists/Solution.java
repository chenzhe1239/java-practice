package com.janosgyerik.practice.oj.leetcode.hard.MergeKSortedLists;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;

import java.util.Arrays;

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        switch (lists.length) {
            case 0: return null;
            case 1: return lists[0];
            case 2: return merge(lists[0], lists[1]);
            default:
                int mid = lists.length / 2;
                return merge(
                        mergeKLists(subArray(lists, 0, mid)),
                        mergeKLists(subArray(lists, mid, lists.length))
                );
        }
    }

    private ListNode[] subArray(ListNode[] lists, int from, int to) {
        return Arrays.copyOfRange(lists, from, to);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);  // dummy

        ListNode node = head;
        ListNode first = list1;
        ListNode second = list2;

        while (first != null && second != null) {
            if (first.val <= second.val) {
                node.next = new ListNode(first.val);
                first = first.next;
            } else {
                node.next = new ListNode(second.val);
                second = second.next;
            }
            node = node.next;
        }
        append(node, first);
        append(node, second);
        return head.next;
    }

    private void append(ListNode node, ListNode list) {
        ListNode runner = list;
        while (runner != null) {
            node.next = new ListNode(runner.val);
            node = node.next;
            runner = runner.next;
        }
    }
}
