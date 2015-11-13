package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MaximumProductSubarrayTest {
    public int maxProduct(int[] A) {
        if (A.length < 1) {
            return 0;
        }
        int[] sanitized = eliminate3m1s(eliminate1s(A));
        int max = A[0];
        for (int i = 0; i <= sanitized.length - 1; ++i) {
            for (int j = i + 1; j <= sanitized.length; ++j) {
                int product = calculateProduct(sanitized, i, j);
                if (product > max) {
                    max = product;
                }
            }
        }
        return max;
    }

    private int calculateProduct(int[] arr, int start, int end) {
        int product = arr[start];
        for (int i = start + 1; i < end; ++i) {
            product *= arr[i];
        }
        return product;
    }

    private int[] eliminate1s(int[] arr) {
        int front = 0;
        for (int num : arr) {
            if (num != 1) {
                arr[front++] = num;
            }
        }
        if (front < arr.length) {
            arr[front++] = 1;
        }
        int[] result = new int[front];
        System.arraycopy(arr, 0, result, 0, front);
        return result;
    }

    private int[] eliminate3m1s(int[] arr) {
        if (arr.length < 4) {
            return arr;
        }
        int front = 2;
        for (int i = 2; i < arr.length; ++i) {
            int num = arr[i];
            if (arr[i] == -1 && arr[i - 1] == -1 && arr[i - 2] == -1) {
                --front;
                do {
                    ++i;
                } while (i < arr.length && arr[i] == -1);
                --i;
            } else {
                arr[front++] = num;
            }
        }
        int[] result = new int[front];
        System.arraycopy(arr, 0, result, 0, front);
        return result;
    }

    @Test
    public void test_2_3_m2_4() {
        assertEquals(6, maxProduct(new int[]{2, 3, -2, 4}));
    }

    @Test
    public void test_long() {
        int[] no1s = eliminate1s(new int[]{-5, 2, 4, 1, -2, 2, -6, 3, -1, -1, -1, -2, -3, 5, 1, -3, -4, 2, -4, 6, -1, 5, -6, 1, -1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 1, -1, -1, -1, -1, 1, -1, 1, 7, -1, 1, 1, -1, -1, -1, -1, 1, -1, -1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, 1, -1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, -1, 1, -1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1, 1, -1, -1, -1, 1, 1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, 1, 1, 1});
        int[] no3m1s = eliminate3m1s(no1s);
        assertEquals("[-5, 2, 4, -2, 2, -6, 3, -1, -2, -3, 5, -3, -4, 2, -4, 6, -1, 5, -6, -1, 7, -1, 1]", Arrays.toString(no3m1s));
    }

    @Test
    public void test_m2() {
        assertEquals(-2, maxProduct(new int[]{-2}));
    }

    @Test
    public void test_m1_m1_m1() {
        assertEquals(1, maxProduct(new int[]{-1, -1, -1}));
    }

    @Test
    public void test_m4_m3() {
        assertEquals(12, maxProduct(new int[]{-4, -3}));
    }

    @Test
    public void test_0_2() {
        assertEquals(2, maxProduct(new int[]{0, 2}));
    }

    @Test
    public void test_m3_0_1_m2() {
        assertEquals(1, maxProduct(new int[]{-3, 0, 1, -2}));
    }
}
