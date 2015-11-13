package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMinimumInRotatedSortedArrayTest {
    public int findMin(int[] num) {
        int left = 0;
        int right = num.length - 1;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (num[left] > num[mid]) {
                right = mid;
            } else if (num[mid] > num[right]) {
                left = mid;
            } else {
                right = left;
            }
        }
        return Math.min(num[left], num[right]);
    }

    public int findMin_naive(int[] num) {
        for (int i = 1; i < num.length; ++i) {
            if (num[i] < num[i - 1]) {
                return num[i];
            }
        }
        return num[0];
    }

    @Test
    public void test_4() {
        assertEquals(4, findMin(new int[]{4}));
    }

    @Test
    public void test_1_2_3() {
        assertEquals(1, findMin(new int[]{1, 2, 3}));
    }

    @Test
    public void test_1_2() {
        assertEquals(1, findMin(new int[]{1, 2}));
    }

    @Test
    public void test_2_1() {
        assertEquals(1, findMin(new int[]{2, 1}));
    }

    @Test
    public void test_4_5_6_7_0_1_2() {
        assertEquals(0, findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }
}
