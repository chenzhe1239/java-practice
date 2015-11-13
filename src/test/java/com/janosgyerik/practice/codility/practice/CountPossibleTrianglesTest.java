package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountPossibleTrianglesTest {
    int countPossibleTriangles(int[] sticks) {
        int len = sticks.length;
        int x = 0;
        int y = 1;
        int z = 2;
        int count = 0;
        while (z < len) {
            if (sticks[x] + sticks[y] > sticks[z]) {
                ++count;
            }
            if (y + 1 < z) {
                ++y;
            } else if (x + 1 < y) {
                ++x;
                y = x + 1;
            } else {
                ++z;
                x = 0;
                y = 1;
            }
        }
        return count;
    }

    @Test
    public void test_1_2_3_5() {
        assertEquals(0, countPossibleTriangles(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void test_1_2_3_4() {
        // 2 3 4
        assertEquals(1, countPossibleTriangles(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void test_1_2_3_4_5() {
        // 2 3 4
        // 2 4 5
        // 3 4 5
        assertEquals(3, countPossibleTriangles(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test_1_2_3_4_5_6() {
        // 2 3 4
        // 2 4 5
        // 2 5 6
        // 3 4 5
        // 3 4 6
        // 4 5 6
        // 3 5 6
        assertEquals(7, countPossibleTriangles(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
