package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }

        int[] minSums = initMinSums(triangle);

        for (int rowIndex = triangle.size() - 2; rowIndex >= 0; --rowIndex) {
            List<Integer> currentRow = triangle.get(rowIndex);
            int leftValue = minSums[0];
            for (int colIndex = 0; colIndex <= rowIndex; ++colIndex) {
                minSums[colIndex] = currentRow.get(colIndex) + Math.min(leftValue, minSums[colIndex + 1]);
                leftValue = minSums[colIndex + 1];
            }
        }
        return minSums[0];
    }

    private int[] initMinSums(List<List<Integer>> triangle) {
        int[] minSums = new int[triangle.size()];
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < minSums.length; ++i) {
            minSums[i] = lastRow.get(i);
        }
        return minSums;
    }

    public int minimumTotal_Greedy(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int col = 0;
        int sum = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); ++i) {
            List<Integer> row = triangle.get(i);
            int col1 = row.get(col);
            int col2 = row.get(col + 1);
            if (col1 < col2) {
                sum += col1;
            } else {
                sum += col2;
                ++col;
            }
        }
        return sum;
    }

    @Test
    public void testEmpty() {
        assertEquals(0, minimumTotal(Arrays.asList()));
    }

    @Test
    public void testSingleton() {
        assertEquals(2, minimumTotal(Arrays.asList(Arrays.asList(2))));
    }

    @Test
    public void testExample1() {
        assertEquals(11, minimumTotal(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        )));
    }

    @Test
    public void testExample2() {
        assertEquals(16, minimumTotal(Arrays.asList(
                Arrays.asList(     2),
                Arrays.asList(    3, 4),
                Arrays.asList(  1, 6, 7),
                Arrays.asList(14, 11, 8, 3)
        )));
    }
}
