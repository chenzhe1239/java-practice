package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingSubsequence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestIncreasingSubsequenceTest {

    private final Solution solution = new Solution();

    private int solve(int ... nums) {
        return solution.lengthOfLIS(nums);
    }

    @Test
    public void test_empty() {
        assertEquals(0, solve());
    }

    @Test
    public void test_3() {
        assertEquals(1, solve(3));
    }

    @Test
    public void test_1_3() {
        assertEquals(2, solve(1, 3));
    }

    @Test
    public void test_3_1() {
        assertEquals(1, solve(3, 1));
    }

    @Test
    public void test_3_2_1() {
        assertEquals(1, solve(3, 2, 1));
    }

    @Test
    public void test_1_3_2() {
        assertEquals(2, solve(1, 3, 2));
    }

    @Test
    public void test_10_9_2_5_3_7_101_18() {
        assertEquals(4, solve(10, 9, 2, 5, 3, 7, 101, 18));
    }
}
