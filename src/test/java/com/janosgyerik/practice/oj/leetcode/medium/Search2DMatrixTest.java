package com.janosgyerik.practice.oj.leetcode.medium;

public class Search2DMatrixTest {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (!isValidMatrix(matrix)) {
            return false;
        }
        if (smallerThanFirst(matrix, target) || biggerThanLast(matrix, target)) {
            return false;
        }
        int row = findBestRow(matrix, target);
        return searchRow(matrix[row], target);
    }

    boolean isValidMatrix(int[][] matrix) {
        return matrix.length > 0 && matrix[0].length > 0;
    }

    boolean smallerThanFirst(int[][] matrix, int target) {
        return target < matrix[0][0];
    }

    boolean biggerThanLast(int[][] matrix, int target) {
        return target > matrix[matrix.length - 1][matrix[0].length - 1];
    }

    int findBestRow(int[][] matrix, int target) {
        return findBestRow(matrix, target, 0, matrix.length);
    }

    int findBestRow(int[][] matrix, int target, int low, int high) {
        if (high - low < 2) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (target == matrix[mid][0]) {
            return mid;
        }
        return target < matrix[mid][0]
                ? findBestRow(matrix, target, low, mid)
                : findBestRow(matrix, target, mid, high);
    }

    boolean searchRow(int[] row, int target) {
        return binarySearchRow(row, target, 0, row.length);
    }

    boolean binarySearchRow(int[] row, int target, int low, int high) {
        if (high - low < 2) {
            return row[low] == target;
        }
        int mid = low + (high - low) / 2;
        if (target == row[mid]) {
            return true;
        }
        return target < row[mid]
                ? binarySearchRow(row, target, low, mid)
                : binarySearchRow(row, target, mid, high);
    }
}
