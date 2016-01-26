package com.janosgyerik.practice.oj.leetcode.hard.ReverseNodesInKGroup;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils.create;
import static org.junit.Assert.assertEquals;

public class ReverseNodesInKGroupTest {

    private final Solution solution = new Solution();

    private String solve(ListNode head, int k) {
        return ListNodeUtils.toString(solution.reverseKGroup(head, k));
    }

    @Test
    public void test_1_2_3_4_5_k_2() {
        assertEquals("2->1->4->3->5", solve(create(1, 2, 3, 4, 5), 2));
    }

    @Test
    public void test_1_2_3_4_5_k_3() {
        assertEquals("3->2->1->4->5", solve(create(1, 2, 3, 4, 5), 3));
    }
}
