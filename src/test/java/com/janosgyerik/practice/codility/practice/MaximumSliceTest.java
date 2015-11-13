package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaximumSliceTest {
    int maximumSlice(int[] arr) {
        int max = 0;
        int maxEnding = 0;
        for (int item : arr) {
            maxEnding = Math.max(0, maxEnding + item);
            max = Math.max(max, maxEnding);
        }
        return max;
    }

    @Test
    public void test_5_m7_3_5_m2_4_m1() {
        assertEquals(10, maximumSlice(new int[]{5, -7, 3, 5, -2, 4, -1}));
    }

    @Test
    public void test_5_m7_3_5_m2_5_m1() {
        assertEquals(11, maximumSlice(new int[]{5, -7, 3, 5, -2, 5, -1}));
    }

    @Test
    public void test_5_7_3_5_m2_5_m1() {
        assertEquals(23, maximumSlice(new int[]{5, 7, 3, 5, -2, 5, -1}));
    }

    @Test
    public void test_5_7_3_5_m2_1_m1() {
        assertEquals(20, maximumSlice(new int[]{5, 7, 3, 5, -2, 1, -1}));
    }

    @Test
    public void test_5_7_3_5_m2_1_m1_3_m1() {
        assertEquals(21, maximumSlice(new int[]{5, 7, 3, 5, -2, 1, -1, 3, -1}));
    }
}
