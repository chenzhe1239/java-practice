package com.janosgyerik.practice.oj.leetcode.medium.ValidPerfectSquare;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPerfectSquareTest {
    private final Solution solution = new Solution();

    private boolean solve(int num) {
        return solution.isPerfectSquare(num);
    }

    @Test
    public void test_16() {
        assertEquals(true, solve(16));
    }

    @Test
    public void test_14() {
        assertEquals(false, solve(14));
    }

    @Test
    public void test_2147483647() {
        assertEquals(false, solve(2147483647));
    }
}
