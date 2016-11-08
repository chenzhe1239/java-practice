package com.janosgyerik.practice.oj.leetcode.medium.ContainsDuplicate3;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsDuplicate3Test {
    private final Solution solution = new Solution();

    private boolean solve(int k, int t, int... nums) {
        return solution.containsNearbyAlmostDuplicate(nums, k, t);
    }

    @Test
    public void test_1_2_3_4_5__1_1_should_be_true() {
        assertThat(solve(1, 1, 1, 2, 3, 4, 5)).isTrue();
    }

    @Test
    public void test_1_3_5_2_4__1_1_should_be_false() {
        assertThat(solve(1, 1, 1, 3, 5, 2, 4)).isFalse();
    }

    @Test
    public void test_1_3_5_2_4__2_1_should_be_true() {
        assertThat(solve(2, 1, 1, 3, 5, 2, 4)).isTrue();
    }

    @Test
    public void test_1_3_5_2_4__1_2_should_be_true() {
        assertThat(solve(1, 2, 1, 3, 5, 2, 4)).isTrue();
    }
    
    @Test
    public void test_overflow() {
        assertThat(solve(1, 2147483647, -1, 2147483647)).isFalse();
    }

    @Test
    public void test_() {
        Solution.BST bst = new Solution.BST(1);
        Solution.Node node1 = new Solution.Node(1);
        Solution.Node node3 = new Solution.Node(3);
        Solution.Node node5 = new Solution.Node(5);
        Solution.Node node2 = new Solution.Node(2);
        Solution.Node node4 = new Solution.Node(4);
//        bst.insert()
    }
}
