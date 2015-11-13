package com.janosgyerik.practice.codility.practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrogJumpsTest {

    private int countWays(int[] steps, int target) {
        int[] counts = new int[target + 1];
        counts[0] = 1;
        for (int smallTarget = 1; smallTarget <= target; ++smallTarget) {
            for (int step : steps) {
                if (step <= smallTarget) {
                    counts[smallTarget] += counts[smallTarget - step];
                }
            }
        }
        return counts[target];
    }

    @Test
    public void test_2_with_1() {
        assertEquals(1, countWays(new int[]{1}, 2));
    }

    @Test
    public void test_2_with_1_2() {
        assertEquals(2, countWays(new int[]{1, 2}, 2));
    }

    @Test
    public void test_3_with_1() {
        assertEquals(1, countWays(new int[]{1}, 3));
    }

    @Test
    public void test_3_with_1_2() {
        assertEquals(3, countWays(new int[]{1, 2}, 3));
    }

    @Test
    public void test_3_with_1_2_3() {
        assertEquals(4, countWays(new int[]{1, 2, 3}, 3));
    }

    @Test
    public void test_4_with_1() {
        assertEquals(1, countWays(new int[]{1}, 4));
    }

    @Test
    public void test_4_with_1_2() {
        assertEquals(5, countWays(new int[]{1, 2}, 4));
    }

    @Test
    public void test_4_with_1_2_3() {
        assertEquals(7, countWays(new int[]{1, 2, 3}, 4));
    }
}
