package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntersectionTwoLinkedListsTest {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                nodeA = nodeA.next;
            } else if (nodeB.val < nodeA.val) {
                nodeB = nodeB.next;
            } else {
                return nodeA;
            }
        }
        return null;
    }

    @Test
    public void testExample() {
        //        A:          a1 → a2
        //                           ↘
        //                             c1 → c2 → c3
        //                           ↗
        //        B:     b1 → b2 → b3

        ListNode headA = new ListNode(11);
        headA.next = new ListNode(12);
        headA.next.next = new ListNode(31);
        headA.next.next.next = new ListNode(32);
        headA.next.next.next.next = new ListNode(33);

        ListNode headB = new ListNode(21);
        headB.next = new ListNode(22);
        headB.next.next = new ListNode(23);
        headB.next.next.next = headA.next.next;

        assertEquals(headA.next.next, getIntersectionNode(headA, headB));
    }
}
