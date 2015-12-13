package com.janosgyerik.practice.oj.leetcode.hard.Candy;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CandyTest {

    private final Solution solution = new Solution();

    @Test
    public void test_calculate_1_2_4_4_3() {
        assertArrayEquals(new int[]{1, 2, 3, 2, 1}, solution.calculate(1, 2, 4, 4, 3));
    }

    @Test
    public void test_calculate_4_2_3_4_1() {
        assertArrayEquals(new int[]{2, 1, 2, 3, 1}, solution.calculate(4, 2, 3, 4, 1));
    }
}
