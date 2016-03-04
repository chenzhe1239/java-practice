package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingPathInMatrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestIncreasingPathInMatrixTest {
    private final Solution solution = new Solution();

    @Test
    public void test_example_1() {
        assertEquals(4, solution.longestIncreasingPath(matrix(
                new int[]{9, 9, 4},
                new int[]{6, 6, 8},
                new int[]{2, 1, 1}
        )));
    }

    @Test
    public void test_example_2() {
        assertEquals(4, solution.longestIncreasingPath(matrix(
                new int[]{3, 4, 5},
                new int[]{3, 2, 6},
                new int[]{2, 2, 1}
        )));
    }

    private int[][] matrix(int[] ... rows) {
        int[][] matrix = new int[rows.length][];
        System.arraycopy(rows, 0, matrix, 0, rows.length);
        return matrix;
    }
}
