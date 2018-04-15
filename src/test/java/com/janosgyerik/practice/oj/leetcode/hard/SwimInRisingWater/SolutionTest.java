package com.janosgyerik.practice.oj.leetcode.hard.SwimInRisingWater;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SolutionTest {
    public int swimInWater(int[][] grid) {
        return new Solution().swimInWater(grid);
    }

    @Test
    public void test_example_1() {
        int[][] grid = {{0, 2}, {1, 3}};
        assertThat(swimInWater(grid)).isEqualTo(3);
    }

    @Test
    public void test_example_2() {
        int[][] grid = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}};

        assertThat(swimInWater(grid)).isEqualTo(16);
    }

    @Test
    public void test_example_3() {
        int[][] grid = {
            {7, 11, 5, 3},
            {2, 14, 12, 8},
            {4, 13, 9, 10},
            {6, 0, 1, 15}
        };

        assertThat(swimInWater(grid)).isEqualTo(15);
    }

    @Test
    public void test_example_3_1() {
        int[][] grid = {
            {7, 11, 15, 3},
            {2, 14, 12, 8},
            {4, 13, 9, 10},
            {6, 0, 1, 5}
        };

        assertThat(swimInWater(grid)).isEqualTo(7);
    }
}
