package com.janosgyerik.practice.oj.leetcode.medium.MinimumPathSum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumPathSumTest {

    private final Solution solution = new Solution();

    private int solve(int[][] grid) {
        return solution.minPathSum(grid);
    }

    @Test
    public void test_empty() {
        assertEquals(0, solve(new int[0][0]));
    }

    @Test
    public void test_singleton_3() {
        assertEquals(3, solve(new int[][]{{3}}));
    }

    @Test
    public void test_row_1_2_3() {
        assertEquals(6, solve(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void test_column_1_2_3() {
        assertEquals(6, solve(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    public void test_pass_through_top() {
        assertEquals(0, solve(new int[][]{{0, 0, 0}, {1, 1, 0}}));
    }

    @Test
    public void test_pass_through_bottom() {
        assertEquals(0, solve(new int[][]{{0, 1, 1}, {0, 0, 0}}));
    }

    @Test
    public void test_pass_through_zigzag() {
        assertEquals(0, solve(new int[][]{{0, 1, 1}, {0, 0, 1}, {1, 0, 0}}));
    }

    @Test
    public void test_multiple_min_paths() {
        assertEquals(0, solve(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }
}
