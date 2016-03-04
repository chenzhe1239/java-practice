package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingPathInMatrix;

import java.util.stream.IntStream;

public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] lengths = zeros(matrix);
        int longest = 0;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                longest = Math.max(longest, 1 + max(
                        calculateLength(matrix, lengths, i, j, i + 1, j),
                        calculateLength(matrix, lengths, i, j, i - 1, j),
                        calculateLength(matrix, lengths, i, j, i, j + 1),
                        calculateLength(matrix, lengths, i, j, i, j - 1)
                ));
            }
        }
        return longest;
    }

    private int calculateLength(int[][] matrix, int[][] lengths, int x, int y, int i, int j) {
        if (i < 0 || j < 0 || matrix.length <= i || matrix[0].length <= j || matrix[i][j] <= matrix[x][y]) {
            return 0;
        }
        if (lengths[i][j] == 0) {
            lengths[i][j] = -1;
            lengths[i][j] = 1 + max(
                    calculateLength(matrix, lengths, i, j, i + 1, j),
                    calculateLength(matrix, lengths, i, j, i - 1, j),
                    calculateLength(matrix, lengths, i, j, i, j + 1),
                    calculateLength(matrix, lengths, i, j, i, j - 1)
            );
        }
        return lengths[i][j];
    }

    private int max(int... arr) {
        return IntStream.of(arr).max().getAsInt();
    }

    private int[][] zeros(int[][] model) {
        int[][] zeros = new int[model.length][];
        for (int i = 0; i < model.length; ++i) {
            zeros[i] = new int[model[i].length];
        }
        return zeros;
    }
}
