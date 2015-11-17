package com.janosgyerik.practice.oj.leetcode.hard.DungeonGame;

public class Solution {
    public int calculateMinimumHP(int[][] grid) {
        int min = 0;
        for (int row = grid.length - 1; row >= 0 ;--row) {
            for (int col = grid[row].length - 1; col >= 0; --col) {
                int requiredForRight = getRight(grid, row, col);
                int requiredForDown = getDown(grid, row, col);
                min = Math.min(0, grid[row][col] + Math.max(requiredForDown, requiredForRight));
                grid[row][col] = min;
            }
        }
        return 1 - Math.min(0, min);
    }

    private int getRight(int[][] grid, int row, int col) {
        if (col == grid[0].length - 1) {
            if (row == grid.length - 1) {
                return 0;
            }
            return grid[row + 1][col];
        }
        return grid[row][col + 1];
    }

    private int getDown(int[][] grid, int row, int col) {
        if (row == grid.length - 1) {
            if (col == grid[0].length - 1) {
                return 0;
            }
            return grid[row][col + 1];
        }
        return grid[row + 1][col];
    }
}
