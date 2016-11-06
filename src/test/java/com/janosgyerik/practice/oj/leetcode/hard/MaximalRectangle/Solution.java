package com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        Queue<Rectangle> queue = initQueue(matrix);

        int max = maxHorizontal(matrix);
        max = Math.max(max, maxVertical(matrix));

        while (!queue.isEmpty()) {
            Rectangle rectangle = queue.poll();
            max = Math.max(max, rectangle.width * rectangle.height);
            rectangle.expandRight().map(queue::add);
            rectangle.expandDown().map(queue::add);
        }
        return max;
    }

    private int maxVertical(char[][] matrix) {
        int max = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            max = Math.max(max, maxVertical(matrix, col));
        }
        return max;
    }

    private int maxVertical(char[][] matrix, int col) {
        int max = 0;
        int current = 0;
        for (char[] row : matrix) {
            char c = row[col];
            if (c == '1') {
                current++;
                max = Math.max(max, current);
            } else {
                current = 0;
            }
        }
        return max;
    }

    private int maxHorizontal(char[][] matrix) {
        int max = 0;
        for (char[] row : matrix) {
            max = Math.max(max, maxHorizontal(row));
        }
        return max;
    }

    private int maxHorizontal(char[] row) {
        int max = 0;
        int current = 0;
        for (char c : row) {
            if (c == '1') {
                current++;
                max = Math.max(max, current);
            } else {
                current = 0;
            }
        }
        return max;
    }

    private Queue<Rectangle> initQueue(char[][] matrix) {
        Queue<Rectangle> queue = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isSet(matrix, col, row)) {
                    if (isSet(matrix, col + 1, row)) {
                        queue.add(new Rectangle(matrix, col, row, 2, 1));
                    }
                    if (isSet(matrix, col, row + 1)) {
                        queue.add(new Rectangle(matrix, col, row, 1, 2));
                    }
                }
            }
        }
        return queue;
    }

    static class Rectangle {
        final char[][] matrix;
        final int left;
        final int top;
        final int width;
        final int height;

        Rectangle(char[][] matrix, int left, int top, int width, int height) {
            this.matrix = matrix;
            this.left = left;
            this.top = top;
            this.width = width;
            this.height = height;
        }

        Optional<Rectangle> expandRight() {
            if (height == 1) {
                return Optional.empty();
            }
            for (int i = top; i < top + height; i++) {
                if (!isSet(left + width, i)) {
                    return Optional.empty();
                }
            }
            return Optional.of(new Rectangle(matrix, left, top, width + 1, height));
        }

        Optional<Rectangle> expandDown() {
            if (width == 1) {
                return Optional.empty();
            }
            for (int i = left; i < left + width; i++) {
                if (!isSet(i, top + height)) {
                    return Optional.empty();
                }
            }
            return Optional.of(new Rectangle(matrix, left, top, width, height + 1));
        }

        boolean isSet(int col, int row) {
            return Solution.isSet(matrix, col, row);
        }
    }

    static boolean isSet(char[][] grid, int col, int row) {
        return !(col < 0 || row < 0 || grid[0].length <= col || grid.length <= row) && grid[row][col] == '1';
    }
}
