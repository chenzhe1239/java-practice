package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveDuplicatesFromSortedListTest {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            }  else {
                node = node.next;
            }
        }
        return head;
    }

    private void assertResult(String expected, int ... values) {
        assertEquals(expected, ListNodeUtils.toString(deleteDuplicates(ListNodeUtils.create(values))));
    }

    @Test
    public void test_1_1_2_x_1_2() {
        assertResult("1->2", 1, 1, 2);
    }

    @Test
    public void test_1_1_2_3_3_x_1_2_3() {
        assertResult("1->2->3", 1, 1, 2, 3, 3);
    }
}
