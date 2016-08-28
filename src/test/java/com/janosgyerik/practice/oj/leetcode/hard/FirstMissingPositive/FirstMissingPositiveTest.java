package com.janosgyerik.practice.oj.leetcode.hard.FirstMissingPositive;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstMissingPositiveTest {

    private final Solution solution = new Solution();

    private int solve(int... nums) {
        return solution.firstMissingPositive(nums);
    }

    @Test
    public void should_get_1_for_empty() {
        assertEquals(1, solve());
    }

    @Test
    public void should_get_1_for_0() {
        assertEquals(1, solve(0));
    }

    @Test
    public void should_get_2_for_1() {
        assertEquals(2, solve(1));
    }

    @Test
    public void should_get_1_for_2() {
        assertEquals(1, solve(2));
    }

    @Test
    public void should_get_3_for_0_2_2_1_1() {
        assertEquals(3, solve(0, 2, 2, 1, 1));
    }

    @Test
    public void should_get_5_for_1_2_2_1_3_1_0_4_0() {
        assertEquals(5, solve(1, 2, 2, 1, 3, 1, 0, 4, 0));
    }
}
