package com.janosgyerik.practice.oj.leetcode.hard.NQueens;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NQueensTest {
    private final Solution solution = new Solution();

    @Test
    public void test_4() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList(
                        ".Q..",
                        "...Q",
                        "Q...",
                        "..Q."
                ),
                Arrays.asList(
                        "..Q.",
                        "Q...",
                        "...Q",
                        ".Q.."
                )
        );
        assertEquals(expected, solution.solveNQueens(4));
    }

    @Test
    public void test_isValid_all_placements_are_valid_in_row_0() {
        assertTrue(solution.isValid(new int[0], 0, 0));
        assertTrue(solution.isValid(new int[0], 0, 1));
        assertTrue(solution.isValid(new int[0], 0, 2));
    }

    @Test
    public void test_isValid_false_in_same_column() {
        assertFalse(solution.isValid(new int[]{0, 0}, 1, 0));
        assertFalse(solution.isValid(new int[]{1, 0}, 1, 1));
    }

    @Test
    public void test_isValid_false_in_diagonal_diff1() {
        assertFalse(solution.isValid(new int[]{0, 0}, 1, 1));
        assertFalse(solution.isValid(new int[]{1, 0}, 1, 0));
    }

    @Test
    public void test_isValid_false_in_diagonal_diff2() {
        assertFalse(solution.isValid(new int[]{0, 0, 0}, 2, 2));
        assertFalse(solution.isValid(new int[]{2, 0, 0}, 2, 0));
    }

    @Test
    public void test_isValid_in_m3() {
        assertTrue(solution.isValid(new int[]{0, 0, 0}, 1, 2));
    }
}
