package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleNumberTest {
    public int singleNumber(int[] A) {
        int xor = 0;
        for (int num : A) {
            xor ^= num;
        }
        return xor;
    }

    @Test
    public void test_1_1_2_2_3() {
        assertEquals(3, singleNumber(new int[]{1, 1, 2, 2, 3}));
    }

    @Test
    public void test_1_1_2_2_3_3_4_6_6() {
        assertEquals(4, singleNumber(new int[]{1, 1, 2, 2, 3, 3, 4, 6, 6}));
    }
}
