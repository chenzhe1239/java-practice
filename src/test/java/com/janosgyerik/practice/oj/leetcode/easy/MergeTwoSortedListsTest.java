package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeTwoSortedListsTest {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head;
        ListNode node1 = l1;
        ListNode node2 = l2;
        if (node1.val <= node2.val) {
            head = new ListNode(node1.val);
            node1 = node1.next;
        } else {
            head = new ListNode(node2.val);
            node2 = node2.next;
        }

        ListNode node = head;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                node.next = new ListNode(node1.val);
                node1 = node1.next;
            } else {
                node.next = new ListNode(node2.val);
                node2 = node2.next;
            }
            node = node.next;
        }
        while (node1 != null) {
            node.next = new ListNode(node1.val);
            node = node.next;
            node1 = node1.next;
        }
        while (node2 != null) {
            node.next = new ListNode(node2.val);
            node = node.next;
            node2 = node2.next;
        }
        return head;
    }

    @Test
    public void testBothEmpty() {
        assertEquals(ListNodeUtils.create(), mergeTwoLists(ListNodeUtils.create(), ListNodeUtils.create()));
    }

    @Test
    public void testFirstEmpty() {
        ListNode l1 = ListNodeUtils.create();
        ListNode l2 = ListNodeUtils.create(1);
        assertEquals(ListNodeUtils.toString(l2), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testSecondEmpty() {
        ListNode l1 = ListNodeUtils.create(1);
        ListNode l2 = ListNodeUtils.create();
        assertEquals(ListNodeUtils.toString(l1), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testInterweaved() {
        ListNode l1 = ListNodeUtils.create(1, 3, 5);
        ListNode l2 = ListNodeUtils.create(2, 4, 6);
        ListNode expected = ListNodeUtils.create(1, 2, 3, 4, 5, 6);
        assertEquals(ListNodeUtils.toString(expected), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testInterweavedWithDups() {
        ListNode l1 = ListNodeUtils.create(1, 1, 1, 3, 5, 5, 5);
        ListNode l2 = ListNodeUtils.create(2, 4, 4, 4, 6);
        ListNode expected = ListNodeUtils.create(1, 1, 1, 2, 3, 4, 4, 4, 5, 5, 5, 6);
        assertEquals(ListNodeUtils.toString(expected), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testSecondWithSmallest() {
        ListNode l1 = ListNodeUtils.create(3, 5, 5, 5);
        ListNode l2 = ListNodeUtils.create(2, 4, 4, 4, 6);
        ListNode expected = ListNodeUtils.create(2, 3, 4, 4, 4, 5, 5, 5, 6);
        assertEquals(ListNodeUtils.toString(expected), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testConcatFirstSecond() {
        ListNode l1 = ListNodeUtils.create(1, 2, 3, 4);
        ListNode l2 = ListNodeUtils.create(5, 6);
        ListNode expected = ListNodeUtils.create(1, 2, 3, 4, 5, 6);
        assertEquals(ListNodeUtils.toString(expected), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }

    @Test
    public void testConcatSecondFirst() {
        ListNode l1 = ListNodeUtils.create(5, 6);
        ListNode l2 = ListNodeUtils.create(1, 2, 3, 4);
        ListNode expected = ListNodeUtils.create(1, 2, 3, 4, 5, 6);
        assertEquals(ListNodeUtils.toString(expected), ListNodeUtils.toString(mergeTwoLists(l1, l2)));
    }
}
