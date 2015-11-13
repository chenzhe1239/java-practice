package com.janosgyerik.practice.codility.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxDoubleSliceSumTest {

    public int solution(int[] ints) {
        if (ints.length < 4) {
            return 0;
        }

        int[] maxStartSumCache = new int[ints.length];
        int maxStartSum = 0;
        for (int i = ints.length - 2; i > 1; --i) {
            maxStartSum = Math.max(maxStartSum + ints[i], 0);
            maxStartSumCache[i - 1] = maxStartSum;
        }

        int[] maxEndSumCache = new int[ints.length];
        int maxEndSum = 0;
        for (int i = 1; i < ints.length - 2; ++i) {
            maxEndSum = Math.max(maxEndSum + ints[i], 0);
            maxEndSumCache[i + 1] = maxEndSum;
        }

        int maxSum = 0;
        for (int i = 1; i < ints.length - 1; ++i) {
            int maxAfter = maxStartSumCache[i];
            int maxBefore = maxEndSumCache[i];
            int tmp = maxAfter + maxBefore;
            maxSum = Math.max(maxSum, tmp);
        }
        return maxSum;
    }

    private int maxStartSumAfter(int[] ints, int i) {
        int maxSum = 0;
        int sum = 0;
        for (int j = i + 1; j < ints.length - 1; ++j) {
            sum += ints[j];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    private int maxEndSumBefore(int[] ints, int i) {
        int maxSum = 0;
        int sum = 0;
        for (int j = i - 1; j > 0; --j) {
            sum += ints[j];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    @Test
    public void testZeroSum() {
        assertEquals(0, solution(new int[]{1, 2, 3}));
    }

    @Test
    public void testSolution() {
        assertEquals(17, solution(new int[]{3, 2, 6, -1, 4, 5, -1, 2}));
    }

    @Test
    public void testSolution2() {
        assertEquals(9, solution(new int[]{0, 1, 2, 3, 4, 5}));
    }

    @Test
    public void testSolutionAllNeg() {
        assertEquals(0, solution(new int[]{-1, -2, -3, -4, -5, -6}));
        assertEquals(0, solution(new int[]{-1, -2, -3, -4, -5}));
        assertEquals(0, solution(new int[]{-1, -2, -3, -4}));
    }

    @Test
    public void testSolutionSinglePositive() {
        assertEquals(2, solution(new int[]{-1, 2, -3, -4, -5, -6}));
        assertEquals(2, solution(new int[]{-1, 2, -3, -4, -5}));
        assertEquals(2, solution(new int[]{-1, 2, -3, -4}));
    }

    @Test
    public void testSolutionTwoPositive() {
        assertEquals(5, solution(new int[]{-1, 2, 3, -4, -5, -6}));
        assertEquals(5, solution(new int[]{-1, 2, 3, -4, -5}));
        assertEquals(3, solution(new int[]{-1, 2, 3, -4}));
    }

    @Test
    public void testSolutionTakeAllPositive() {
        assertEquals(12, solution(new int[]{-1, -2, 3, 4, 5, -6, -7}));
        assertEquals(9, solution(new int[]{-1, -2, -3, 4, 5, -6, -7}));
        assertEquals(5, solution(new int[]{-1, -2, -3, -4, 5, -6, -7}));
    }

    @Test
    public void testSolutionInterleavedPositive() {
        assertEquals(14, solution(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10}));
        assertEquals(2, solution(new int[]{0, -1, 1, -1, 1, -1, 1, 0}));
        assertEquals(2, solution(new int[]{0, -1, 1, -1, 1, -1, 0}));
        assertEquals(2, solution(new int[]{0, -1, 1, -1, 1, 0}));

    }

    @Test
    public void testMaxStartSumAfter() {
        assertEquals(8, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10}, 6));
        assertEquals(9, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10, 11}, 6));
        assertEquals(1, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9}, 5));
        assertEquals(7, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9}, 4));
        assertEquals(2, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9}, 3));
        assertEquals(7, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 0}, 4));
        assertEquals(1, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 0}, 5));
        assertEquals(8, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 0}, 6));
        assertEquals(0, maxStartSumAfter(new int[]{-1, 2, -3, 4, -5, 6, -7, 8}, 5));
        assertEquals(9, maxStartSumAfter(new int[]{3, 2, 6, -1, 4, 5, -1, 2}, 3));
        assertEquals(9, maxStartSumAfter(new int[]{0, 1, 2, 3, 4, 5}, 1));
        assertEquals(7, maxStartSumAfter(new int[]{0, 1, 2, 3, 4, 5}, 2));
        assertEquals(4, maxStartSumAfter(new int[]{0, 1, 2, 3, 4, 5}, 3));
        assertEquals(0, maxStartSumAfter(new int[]{0, 1, 2, 3, 4, 5}, 4));
        assertEquals(0, maxStartSumAfter(new int[]{0, -1, -2, -3, -4, -5}, 1));
        assertEquals(0, maxStartSumAfter(new int[]{0, -1, -2, -3, -4, -5}, 2));
        assertEquals(0, maxStartSumAfter(new int[]{0, -1, -2, -3, -4, -5}, 3));
        assertEquals(0, maxStartSumAfter(new int[]{0, -1, -2, -3, -4, -5}, 4));
        assertEquals(1, maxStartSumAfter(new int[]{0, -1, 1, -1, 1, -1, 1, 0}, 1));
        assertEquals(0, maxStartSumAfter(new int[]{0, -1, 1, -1, 1, -1, 1, 0}, 2));
        assertEquals(1, maxStartSumAfter(new int[]{0, -1, 1, -1, 1, -1, 1, 0}, 3));
    }

    @Test
    public void testMaxEndSumBefore() {
        assertEquals(6, maxEndSumBefore(new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10, 11}, 6));
        assertEquals(6, maxEndSumBefore(new int[]{-1, 2, -3, 4, -5, 6, -7,}, 6));
        assertEquals(0, maxEndSumBefore(new int[]{-1, 2, -3, 4, -5, 6,}, 5));
        assertEquals(4, maxEndSumBefore(new int[]{-1, 2, -3, 4, -5, 6,}, 4));
        assertEquals(8, maxEndSumBefore(new int[]{3, 2, 6, -1, 4, 5, -1, 2}, 3));
    }
}
