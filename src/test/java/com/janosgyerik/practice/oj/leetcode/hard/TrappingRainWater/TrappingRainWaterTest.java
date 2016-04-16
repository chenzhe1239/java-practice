package com.janosgyerik.practice.oj.leetcode.hard.TrappingRainWater;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrappingRainWaterTest {

    private final Solution solution = new Solution();

    @Test
    public void test_4_2_3() {
        assertEquals(1, solution.trap(4, 2, 3));
    }

    @Test
    public void test_4_2_3_4() {
        assertEquals(3, solution.trap(4, 2, 3, 4));
    }

    @Test
    public void test_4_0_0_4() {
        assertEquals(8, solution.trap(4, 0, 0, 4));
    }

    @Test
    public void test_1_2_3() {
        assertEquals(0, solution.trap(1, 2, 3));
    }

    @Test
    public void test_3_2_1() {
        assertEquals(0, solution.trap(3, 2, 1));
    }

    @Test
    public void test_0_1_0_2_1_0_1_3_2_1_2_1() {
        assertEquals(6, solution.trap(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
    }
}
