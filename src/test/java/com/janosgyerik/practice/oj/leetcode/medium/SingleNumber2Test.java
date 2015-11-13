package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleNumber2Test {
    public int singleNumber(int[] A) {
        long sum = computeSum(A);
        
        OUTER:
        for (int i = 0; i < A.length; ++i) {
            if ((sum - A[i]) % 3 == 0) {
                for (int j = 0; j < A.length; ++j) {
                    if (i != j && A[i] == A[j]) {
                        continue OUTER;
                    }
                }
                return A[i];
            }
        }
        return -1;
    }

    public long computeSum(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    @Test
    public void test_1_1_1_2_2_2_3() {
        assertEquals(3, singleNumber(new int[]{1, 1, 1, 2, 2, 2, 3}));
    }

    @Test
    public void test_1_1_1_2_2_2_3_3_3_4_6_6_6() {
        assertEquals(4, singleNumber(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 6, 6, 6}));
    }
}
