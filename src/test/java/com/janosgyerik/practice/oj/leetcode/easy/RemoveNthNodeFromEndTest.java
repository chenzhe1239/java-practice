package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RemoveNthNodeFromEndTest {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodes = new ArrayList<>();
        for (ListNode node = head; node.next != null; node = node.next) {
            nodes.add(node);
        }
        if (nodes.isEmpty()) {
            return null;
        }
        if (nodes.size() < n) {
            head = head.next;
        } else {
            ListNode node = nodes.get(nodes.size() - n);
            node.next = node.next.next;
        }
        return head;
    }

    @Test
    public void test_1_2_3_4_5_x_2() {
        ListNode head = ListNodeUtils.create(1, 2, 3, 4, 5);
        assertEquals("1->2->3->5", ListNodeUtils.toString(removeNthFromEnd(head, 2)));
    }

    @Test
    public void test_1_x_2() {
        ListNode head = ListNodeUtils.create(1);
        assertEquals("", ListNodeUtils.toString(removeNthFromEnd(head, 1)));
    }

    @Test
    public void test_1_2_x_2() {
        ListNode head = ListNodeUtils.create(1, 2);
        assertEquals("2", ListNodeUtils.toString(removeNthFromEnd(head, 2)));
    }
}
