package com.janosgyerik.practice.oj.leetcode.medium.PartitionList;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils.create;

public class PartitionListTest {
    private final Solution solution = new Solution();

    private String solve(int x, int ... values) {
        ListNode head = create(values);
        return ListNodeUtils.toString(solution.partition(head, x));
    }

    @Test
    public void partition_1_2_1_by_2() {
        assertEquals("1->1->2", solve(2, 1, 2, 1));
    }

    private String filter(Predicate<? super Integer> predicate, int ... values) {
        return ListNodeUtils.toString(Solution.filter(create(values), predicate));
    }

    @Test
    public void test_filter_1_2_3_by_gt1() {
        assertEquals("2->3", filter(e -> e > 1, 1, 2, 3));
    }

    @Test
    public void test_filter_1_2_3_by_gt2() {
        assertEquals("3", filter(e -> e > 2, 1, 2, 3));
    }

    @Test
    public void test_filter_1_2_3_by_gt0() {
        assertEquals("1->2->3", filter(e -> e > 0, 1, 2, 3));
    }

    @Test
    public void test_filter_1_2_3_by_gt5() {
        assertEquals("", filter(e -> e > 5, 1, 2, 3));
    }

    @Test
    public void test_concat_empty_and_empty() {
        assertEquals("", ListNodeUtils.toString(Solution.concat(create(), create())));
    }

    @Test
    public void test_concat_1_2_3_and_empty() {
        assertEquals("1->2->3", ListNodeUtils.toString(Solution.concat(create(1, 2, 3), create())));
    }

    @Test
    public void test_concat_empty_and_1_2_3() {
        assertEquals("1->2->3", ListNodeUtils.toString(Solution.concat(create(), create(1, 2, 3))));
    }

    @Test
    public void test_concat_1_2_and_3_empty() {
        assertEquals("1->2->3", ListNodeUtils.toString(Solution.concat(create(1, 2), create(3))));
    }
}
