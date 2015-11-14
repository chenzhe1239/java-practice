package com.janosgyerik.practice.oj.leetcode.medium.MinimumPathSum;

public class Solution {
    public int minPathSum(int[][] grid) {
        int min = 0;
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                min = grid[row][col] + Math.min(getLeft(grid, row, col), getTop(grid, row, col));
                grid[row][col] = min;
            }
        }
        return min;
    }

    private int getLeft(int[][] grid, int row, int col) {
        if (col == 0) {
            if (row == 0) {
                return 0;
            }
            return grid[row - 1][col];
        }
        return grid[row][col - 1];
    }

    private int getTop(int[][] grid, int row, int col) {
        if (row == 0) {
            if (col == 0) {
                return 0;
            }
            return grid[row][col - 1];
        }
        return grid[row - 1][col];
    }
}
