package com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

// TODO time limit exceeded on test case 63/66
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        Queue<Rectangle> queue = initQueue(matrix);

        int max = 0;
        while (!queue.isEmpty()) {
            Rectangle rectangle = queue.poll();
            max = Math.max(max, rectangle.width * rectangle.height);
            rectangle.expandRight().map(queue::add);
            rectangle.expandDown().map(queue::add);
        }
        return max;
    }

    private Queue<Rectangle> initQueue(char[][] matrix) {
        Queue<Rectangle> queue = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isSet(matrix, col, row)) {
                    queue.add(new Rectangle(matrix, col, row, 1, 1));
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
            for (int i = top; i < top + height; i++) {
                if (!isSet(left + width, i)) {
                    return Optional.empty();
                }
            }
            return Optional.of(new Rectangle(matrix, left, top, width + 1, height));
        }

        Optional<Rectangle> expandDown() {
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
