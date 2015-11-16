package com.janosgyerik.practice.oj.leetcode.hard.DungeonGame;

public class Solution {
    public int calculateMinimumHP(int[][] grid) {
        int min = grid[0][0] >= 0 ? 1 : 1 - grid[0][0];

        int[][] xmin = createGrid(grid);
        int[][] hp = createGrid(grid);

        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                if (getLeft(xmin, row, col) < getTop(xmin, row, col)
                        || getLeft(xmin, row, col) == getTop(xmin, row, col)
                        && getLeft(hp, row, col) > getTop(hp, row, col)) {
                    xmin[row][col] = Math.min(getLeft(xmin, row, col), getLeft(xmin, row, col) + grid[row][col]);
                    hp[row][col] = getLeft(hp, row, col) + grid[row][col];
                } else {
                    xmin[row][col] = Math.min(getTop(xmin, row, col), getTop(xmin, row, col) + grid[row][col]);
                    hp[row][col] = getTop(hp, row, col) + grid[row][col];
                }
            }
        }
        return min;
    }

    private int[][] createGrid(int[][] grid) {
        int[][] newGrid = new int[grid.length][];
        int width = grid[0].length;
        for (int i = 0; i < newGrid.length; ++i) {
            newGrid[i] = new int[width];
        }
        return newGrid;
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
