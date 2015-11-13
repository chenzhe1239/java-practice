package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MajorityElementTest {
    int getLeader(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        int candidate = arr[0];
        int count = 1;
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            int next = arr[i];
            if (next == candidate) {
                ++count;
            } else {
                --count;
                if (count == 0 && ++i < len) {
                    candidate = arr[i];
                    count = 1;
                }
            }
        }
        return count > 0 ? candidate : -1;
    }

    @Test
    public void test_1_1_1_2() {
        assertEquals(1, getLeader(new int[]{1, 1, 1, 2}));
    }

    @Test
    public void test_1_1_1_2_1_2_2_2_2() {
        assertEquals(2, getLeader(new int[]{1, 1, 1, 2, 1, 2, 2, 2, 2}));
    }

    @Test
    public void test_1_2() {
        assertEquals(-1, getLeader(new int[]{1, 2}));
    }

    @Test
    public void test_empty() {
        assertEquals(-1, getLeader(new int[]{}));
    }

    @Test
    public void test_1() {
        assertEquals(1, getLeader(new int[]{1}));
    }

    @Test
    public void test_1_1_2() {
        assertEquals(1, getLeader(new int[]{1, 1, 2}));
    }

    @Test
    public void test_1_2_2() {
        assertEquals(2, getLeader(new int[]{1, 2, 2}));
    }

    @Test
    public void test_1_3_2_3_3() {
        assertEquals(3, getLeader(new int[]{1, 3, 2, 3, 3}));
    }

    @Test
    public void test_1_2_3_3_3() {
        assertEquals(3, getLeader(new int[]{1, 2, 3, 3, 3}));
    }
}
