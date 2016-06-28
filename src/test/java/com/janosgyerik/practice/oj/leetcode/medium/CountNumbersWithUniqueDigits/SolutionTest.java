package com.janosgyerik.practice.oj.leetcode.medium.CountNumbersWithUniqueDigits;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    private final Solution solution = new Solution();

    private int solve(int n) {
        return solution.countNumbersWithUniqueDigits(n);
    }

    @Test
    public void test_0() {
        assertEquals(1, solve(0));
    }

    @Test
    public void test_1() {
        assertEquals(10, solve(1));
    }

    @Test
    public void test_2() {
        assertEquals(91, solve(2));
    }
}
