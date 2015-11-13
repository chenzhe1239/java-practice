package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TapeRobotTest {
    int findMaxSumWalk(int[] arr, int k, int m) {
        // patterns:
        // 1. go in one direction only
        // 2. go in one direction a little bit, then go more in the other direction
        int len = arr.length;
        int[] prefixSums = new int[len + 1];
        for (int i = 1; i <= len; ++i) {
            prefixSums[i] = prefixSums[i - 1] + arr[i - 1];
        }
        int max = arr[k];
        int maxDetour = m / 2;
        for (int detour = 0; detour < maxDetour; ++detour) {
            int start1 = Math.max(0, k - detour);
            int end1 = Math.min(len, k - detour + m);
            int sum1 = prefixSums[end1] - prefixSums[start1];
            max = Math.max(max, sum1);

            int start2 = Math.max(0, k + detour - m);
            int end2 = Math.min(len, k + detour);
            int sum2 = prefixSums[end2] - prefixSums[start2];
            max = Math.max(max, sum2);
        }
        return max;
    }

    @Test
    public void testExample() {
        assertEquals(18, findMaxSumWalk(new int[]{2, 3, 1, 5, 1, 3, 9}, 4, 4));
    }

    @Test
    public void testExample2() {
        assertEquals(16, findMaxSumWalk(new int[]{2, 3, 1, 5, 1, 9, 1}, 4, 4));
    }
}
