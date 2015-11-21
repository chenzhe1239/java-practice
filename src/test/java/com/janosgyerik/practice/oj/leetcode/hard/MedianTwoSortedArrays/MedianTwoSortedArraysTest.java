package com.janosgyerik.practice.oj.leetcode.hard.MedianTwoSortedArrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianTwoSortedArraysTest {

    private static final double DELTA = 1e-3;

    private final Solution solution = new Solution();

    private double solve(int[] nums1, int[] nums2) {
        return solution.findMedianSortedArrays(nums1, nums2);
    }

    @Test
    public void test_singleton_gives_self() {
        assertEquals(1, solve(new int[]{1}, new int[0]), DELTA);
    }

    @Test
    public void test_1_2_x_empty_gives_1p5() {
        assertEquals(1.5, solve(new int[]{1, 2}, new int[0]), DELTA);
    }

    @Test
    public void test_1_x_2_empty_gives_1p5() {
        assertEquals(1.5, solve(new int[]{1}, new int[]{2}), DELTA);
    }

    @Test
    public void test_empty_x_1_2_empty_gives_1p5() {
        assertEquals(1.5, solve(new int[0], new int[]{1, 2}), DELTA);
    }

    @Test
    public void test_1_2_x_3_gives_2() {
        assertEquals(2, solve(new int[]{1, 2}, new int[]{3}), DELTA);
    }
}
