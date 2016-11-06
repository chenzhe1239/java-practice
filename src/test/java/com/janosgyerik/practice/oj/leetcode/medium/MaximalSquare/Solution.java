package com.janosgyerik.practice.oj.leetcode.medium.MaximalSquare;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        Queue<Square> queue = initQueue(matrix);

        int max = 0;
        while (!queue.isEmpty()) {
            Square square = queue.poll();
            max = Math.max(max, square.size);
            square.expand().map(queue::add);
        }
        return max;
    }

    private Queue<Square> initQueue(char[][] matrix) {
        Queue<Square> queue = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isSet(matrix, col, row)) {
                    queue.add(new Square(matrix, col, row, 1));
                    if (isSet(matrix, col + 1, row) && isSet(matrix, col, row + 1) && isSet(matrix, col + 1, row + 1)) {
                        queue.add(new Square(matrix, col, row, 2));
                    }
                }
            }
        }
        return queue;
    }

    static class Square {
        final char[][] matrix;
        final int left;
        final int top;
        final int size;

        Square(char[][] matrix, int left, int top, int size) {
            this.matrix = matrix;
            this.left = left;
            this.top = top;
            this.size = size;
        }

        Optional<Square> expand() {
            for (int i = top - 1; i < top + size + 1; i++) {
                if (!isSet(left - 1, i)) {
                    return Optional.empty();
                }
                if (!isSet(left + size, i)) {
                    return Optional.empty();
                }
            }
            for (int i = left - 1; i < left + size + 1; i++) {
                if (!isSet(i, top - 1)) {
                    return Optional.empty();
                }
                if (!isSet(i, top + size)) {
                    return Optional.empty();
                }
            }
            return Optional.of(new Square(matrix, left - 1, top - 1, size + 2));
        }

        boolean isSet(int col, int row) {
            return Solution.isSet(matrix, col, row);
        }
    }

    static boolean isSet(char[][] grid, int col, int row) {
        return !(col < 0 || row < 0 || grid[0].length <= col || grid.length <= row) && grid[row][col] == '1';
    }
}
