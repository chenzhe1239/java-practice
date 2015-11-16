package com.janosgyerik.practice.oj.leetcode.hard.DungeonGame;

public class Solution {
    public int calculateMinimumHP(int[][] grid) {
        int min = grid[0][0];
        int[][] xmin = createGrid(grid);
        int[][] hp = createGrid(grid);

        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                int xminLeft = getLeft(xmin, row, col);
                int xminTop = getTop(xmin, row, col);
                int hpLeft = getLeft(hp, row, col);
                int hpTop = getTop(hp, row, col);

                if (xminLeft < xminTop || xminLeft == xminTop && hpLeft > hpTop) {
                    xmin[row][col] = Math.min(xminLeft, xminLeft + grid[row][col]);
                    hp[row][col] = hpLeft + grid[row][col];
                } else {
                    xmin[row][col] = Math.min(hpTop, hpTop + grid[row][col]);
                    hp[row][col] = hpTop + grid[row][col];
                }
                min = hp[row][col];
            }
        }
        return min >= 0 ? 1 : 1 - min;
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
