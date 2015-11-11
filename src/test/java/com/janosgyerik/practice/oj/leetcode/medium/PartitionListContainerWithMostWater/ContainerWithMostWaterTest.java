package com.janosgyerik.practice.oj.leetcode.medium.PartitionListContainerWithMostWater;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ContainerWithMostWaterTest {
    private final Solution solution = new Solution();

    private int solve(int... arr) {
        return solution.maxArea(arr);
    }

    @Test
    public void test_1_1() {
        assertEquals(1, solve(1, 1));
    }

    @Test
    public void test_1_2_1() {
        assertEquals(2, solve(1, 2, 1));
    }

    @Test
    public void test_1_2_4_3() {
        assertEquals(4, solve(1, 2, 4, 3));
    }

    @Test
    public void test_3_2_1_3() {
        assertEquals(9, solve(3, 2, 1, 3));
    }

    @Test
    public void test_1_2_3_4_5_3_2_7_3_1() {
        assertEquals(18, solve(1, 2, 3, 4, 5, 3, 2, 7, 3, 1));
    }

    @Test
    public void test_1_2() {
        assertEquals(1, solve(1, 2));
    }

    @Test
    public void test_2_1() {
        assertEquals(1, solve(2, 1));
    }

    @Test
    public void test_2_3_4_5_4_is_9() {
        assertEquals(9, solve(2, 3, 4, 5, 4));
    }

    @Test
    public void test_2_4_3_is_4() {
        assertEquals(4, solve(2, 4, 3));
    }

    @Test
    public void test_3_4_4_is_6() {
        assertEquals(6, solve(3, 4, 4));
    }

    @Test
    public void test_1_4_4_is_4() {
        assertEquals(4, solve(1, 4, 4));
    }

    @Test
    public void test_3_4_4_4_is_9() {
        assertEquals(9, solve(3, 4, 4, 4));
    }

    @Test
    public void test_tle_example1() {
        int[] arr = IntStream.range(1, 15000)
                .map(e -> 15000 - e + 1)
                .toArray();
        assertEquals(56250000, solve(arr));
    }
}
