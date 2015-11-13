package com.janosgyerik.misc;

//import com.janosgyerik.tools.util.MatrixUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RotateImageTest {
    public void rotate(int[][] matrix) {
        int[][] copy = makeCopy(matrix);

        // 1 2 3    7 4 1
        // 4 5 6 -> 8 5 2
        // 7 8 9    9 6 3
        int n = matrix.length;
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n; ++col) {
                matrix[row][col] = copy[n - 1 - col][row];
            }
        }
    }

    int[][] makeCopy(int[][] matrix) {
        int[][] copy = matrix.clone();
        for (int i = 0; i < matrix.length; ++i) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    public void rotateInPlace(int[][] matrix) {
        // 1 2 3    7 4 1
        // 4 5 6 -> 8 5 2
        // 7 8 9    9 6 3
        int n = matrix.length;
        for (int ring = 0; ring < n / 2; ++ring) {
            for (int i = 0; i < n - 2 * ring - 1; ++i) {
                rotateRing(matrix, ring);
            }
        }
    }

    void rotateRing(int[][] matrix, int ring) {
        int first = matrix[ring][ring];
        rotateLeftUp(matrix, ring);
        rotateBottomLeft(matrix, ring);
        rotateRightDown(matrix, ring);
        rotateTopRight(matrix, ring, first);
    }

    void rotateLeftUp(int[][] matrix, int ring) {
        int size = matrix.length - 2 * ring;
        for (int i = 0; i < size - 1; ++i) {
            matrix[ring + i][ring] = matrix[ring + i + 1][ring];
        }
    }

    void rotateBottomLeft(int[][] matrix, int ring) {
        int end = matrix.length - 1 - ring;
        int size = matrix.length - 2 * ring;
        for (int i = 0; i < size - 1; ++i) {
            matrix[end][ring + i] = matrix[end][ring + i + 1];
        }
    }

    void rotateRightDown(int[][] matrix, int ring) {
        int end = matrix.length - 1 - ring;
        int size = matrix.length - 2 * ring;
        for (int i = 0; i < size - 1; ++i) {
            matrix[end - i][end] = matrix[end - i - 1][end];
        }
    }

    void rotateTopRight(int[][] matrix, int ring, int first) {
        int end = matrix.length - 1 - ring;
        int size = matrix.length - 2 * ring;
        for (int i = 0; i < size - 2; ++i) {
            matrix[ring][end - i] = matrix[ring][end - i - 1];
        }
        matrix[ring][ring + 1] = first;
    }

    @Test
    public void test_2x2() {
        int[][] matrix = {{1, 2}, {3, 4}};
        int[][] expected = {{3, 1}, {4, 2}};
        rotate(matrix);
//        assertEquals(MatrixUtils.toString(expected), MatrixUtils.toString(matrix));
    }
}
