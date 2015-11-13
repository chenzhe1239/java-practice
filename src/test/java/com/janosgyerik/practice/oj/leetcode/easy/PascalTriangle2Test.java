package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PascalTriangle2Test {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; ++i) {
            row.add(1);
            int half = i / 2;
            int prevRight = 1;
            for (int j = 1; j <= half; ++j) {
                int left = prevRight;
                int right = row.get(j);
                int value = left + right;
                row.set(j, value);
                row.set(i - j, value);
                prevRight = right;
            }
        }
        return row;
    }

    @Test
    public void test_0() {
        assertEquals(Arrays.asList(1), getRow(0));
    }

    @Test
    public void test_1() {
        assertEquals(Arrays.asList(1, 1), getRow(1));
    }

    @Test
    public void test_2() {
        assertEquals(Arrays.asList(1, 2, 1), getRow(2));
    }

    @Test
    public void test_3() {
        assertEquals(Arrays.asList(1, 3, 3, 1), getRow(3));
    }

    @Test
    public void test_4() {
        assertEquals(Arrays.asList(1, 4, 6, 4, 1), getRow(4));
    }
}
