package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindPeakElementTest {
    public int findPeakElement(int[] num) {
        int len = num.length;
        for (int i = 1; i < len; ++i) {
            if (num[i] < num[i - 1]) {
                return i - 1;
            }
        }
        return len - 1;
    }

    @Test
    public void test_1_2_3_1() {
        assertEquals(2, findPeakElement(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void test_1() {
        assertEquals(0, findPeakElement(new int[]{1}));
    }

    @Test
    public void test_2_1() {
        assertEquals(0, findPeakElement(new int[]{2, 1}));
    }

    @Test
    public void test_1_2() {
        assertEquals(1, findPeakElement(new int[]{1, 2}));
    }
}
