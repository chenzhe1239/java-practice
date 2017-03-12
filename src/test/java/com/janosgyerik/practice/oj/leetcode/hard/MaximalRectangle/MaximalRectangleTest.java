package com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle;

import com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle.Solution.Interval;
import org.junit.Test;

import java.util.List;

import static com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle.Solution.interval;
import static com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle.Solution.intervals;
import static org.assertj.core.api.Assertions.assertThat;

public class MaximalRectangleTest {
    private final Solution solution = new Solution();

    int solve(char[][] matrix) {
        return solution.maximalRectangle(matrix);
    }

    @Test
    public void test_example_1() {
        char[][] matrix = matrix("1 0 1 0 0\n" +
                "1 0 1 1 1\n" +
                "1 1 1 1 1\n" +
                "1 0 0 1 0");
        assertThat(solve(matrix)).isEqualTo(6);
    }

    @Test
    public void test_example_0() {
        char[][] matrix = matrix("0");
        assertThat(solve(matrix)).isEqualTo(0);
    }

    private char[][] matrix(String s) {
        String[] lines = s.split("\n");
        char[][] matrix = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            matrix[i] = lines[i].replaceAll("[^01]", "").toCharArray();
        }
        return matrix;
    }

    static List<Interval> intervals(String row) {
        return Solution.intervals(row.toCharArray());
    }

    @Test
    public void test_intervals_0() {
        assertThat(intervals("0")).isEmpty();
    }

    @Test
    public void test_intervals_1() {
        assertThat(intervals("1")).containsExactly(interval(0, 1));
    }

    @Test
    public void test_intervals_111() {
        assertThat(intervals("111")).containsExactly(interval(0, 3));
    }

    @Test
    public void test_intervals_000() {
        assertThat(intervals("000")).isEmpty();
    }

    @Test
    public void test_intervals_1101() {
        assertThat(intervals("1101")).containsExactly(interval(0, 2), interval(3, 4));
    }

    @Test
    public void test_intervals_001() {
        assertThat(intervals("001")).containsExactly(interval(2, 3));
    }
}
