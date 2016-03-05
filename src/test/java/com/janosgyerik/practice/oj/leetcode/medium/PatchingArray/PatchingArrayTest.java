package com.janosgyerik.practice.oj.leetcode.medium.PatchingArray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatchingArrayTest {

    private final Solution solution = new Solution();

    private int solve(int n, int... nums) {
        return solution.minPatches(nums, n);
    }

    @Test
    public void test_1_3_for_6_should_give_1() {
        assertEquals(1, solve(6, 1, 3));
    }

    @Test
    public void test_1_5_10_for_20_should_give_2() {
        assertEquals(2, solve(20, 1, 5, 10));
    }

    @Test
    public void test_1_2_31_33_for_2147483647_should_give() {
        assertEquals(28, solve(2147483647, 1, 2, 31, 33));
    }

    @Test
    public void test_empty_for_7_should_give() {
        assertEquals(3, solve(7));
    }
}
