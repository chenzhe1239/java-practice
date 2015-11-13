package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ReorderListTest {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        Stack<ListNode> stack = new Stack<>();

        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        node = head;
        int half = stack.size() / 2;
        for (int i = 0; i < half; ++i) {
            ListNode newNext = stack.pop();
            newNext.next = node.next;
            node.next = newNext;
            node = newNext.next;
        }
        node.next = null;
    }

    @Test
    public void test_0() {
        reorderList(null);
    }

    @Test
    public void test_1() {
        List<Integer> orig = Arrays.asList(1);
        ListNode head = fromList(orig);
        reorderList(head);
        assertEquals(orig, toList(head));
    }

    @Test
    public void test_1_2() {
        List<Integer> orig = Arrays.asList(1, 2);
        ListNode head = fromList(orig);
        reorderList(head);

        List<Integer> expected = Arrays.asList(1, 2);
        assertEquals(expected, toList(head));
    }

    @Test
    public void test_1_2_3() {
        List<Integer> orig = Arrays.asList(1, 2, 3);
        ListNode head = fromList(orig);
        reorderList(head);

        List<Integer> expected = Arrays.asList(1, 3, 2);
        assertEquals(expected, toList(head));
    }

    @Test
    public void test_1_2_3_4() {
        List<Integer> orig = Arrays.asList(1, 2, 3, 4);
        ListNode head = fromList(orig);
        reorderList(head);

        List<Integer> expected = Arrays.asList(1, 4, 2, 3);
        assertEquals(expected, toList(head));
    }

    @Test
    public void test_1_2_3_4_5() {
        List<Integer> orig = Arrays.asList(1, 2, 3, 4, 5);
        ListNode head = fromList(orig);
        reorderList(head);

        List<Integer> expected = Arrays.asList(1, 5, 2, 4, 3);
        assertEquals(expected, toList(head));
    }

    @Test
    public void testConverters() {
        List<Integer> orig = Arrays.asList(1, 2, 3, 4);
        assertEquals(orig, toList(fromList(orig)));
    }

    ListNode fromList(List<Integer> args) {
        ListNode head = new ListNode(args.get(0));
        ListNode node = head;
        for (int i = 1; i < args.size(); ++i) {
            node = node.next = new ListNode(args.get(i));
        }
        return head;
    }

    List<Integer> toList(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }
}
