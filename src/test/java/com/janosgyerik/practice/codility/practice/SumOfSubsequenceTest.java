package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SumOfSubsequenceTest {
    boolean hasSubsequenceWithSum(int[] arr, int targetSum) {
        int front = 0;
        int sum = 0;
        for (int item : arr) {
            sum += item;
            while (sum > targetSum) {
                sum -= arr[front++];
            }
            if (sum == targetSum) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_6_2_7_4_1_3_6__12() {
        assertTrue(hasSubsequenceWithSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 12));
    }

    @Test
    public void test_6_2_7_4_1_3_6__13() {
        assertTrue(hasSubsequenceWithSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 13));
    }

    @Test
    public void test_6_2_7_4_1_3_6__14() {
        assertTrue(hasSubsequenceWithSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 14));
    }

    @Test
    public void test_6_2_7_4_1_3_6__15() {
        assertTrue(hasSubsequenceWithSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 15));
    }

    @Test
    public void test_6_2_7_4_1_3_6__16() {
        assertFalse(hasSubsequenceWithSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 16));
    }

    @Test
    public void test_6_2_7_14_1_3_6__12() {
        assertFalse(hasSubsequenceWithSum(new int[]{6, 2, 7, 14, 1, 3, 6}, 12));
    }
}
