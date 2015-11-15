package com.janosgyerik.practice.oj.leetcode.medium.HouseRobber2;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseRobber2Test {

    private final Solution solution = new Solution();

    private int solve(int... nums) {
        return solution.rob(nums);
    }

    @Test
    public void test_empty() {
        assertEquals(0, solve());
    }

    @Test
    public void test_singleton_3() {
        assertEquals(3, solve(3));
    }

    @Test
    public void test_1_2_gives_2() {
        assertEquals(2, solve(1, 2));
    }

    @Test
    public void test_2_1_gives_2() {
        assertEquals(2, solve(2, 1));
    }

    @Test
    public void test_1_2_3_4_5_gives_8() {
        assertEquals(8, solve(1, 2, 3, 4, 5));
    }

    @Test
    public void test_1_2_3_4_5_6_gives_12() {
        assertEquals(12, solve(1, 2, 3, 4, 5, 6));
    }
}
