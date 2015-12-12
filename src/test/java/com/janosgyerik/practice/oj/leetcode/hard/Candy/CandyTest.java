package com.janosgyerik.practice.oj.leetcode.hard.Candy;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CandyTest {

    private final Solution solution = new Solution();

    private int solve(int... ratings) {
        return solution.candy(ratings);
    }

    @Test
    public void test_if_length_0_then_0() {
        assertEquals(0, solve());
    }

    @Test
    public void test_if_length_1_then_1() {
        assertEquals(1, solve(3));
    }

    @Test
    public void test_if_1_1_then_2() {
        assertEquals(2, solve(1, 1));
    }

    @Test
    public void test_if_5_5_then_2() {
        assertEquals(2, solve(1, 1));
    }

    @Test
    public void test_if_1_2_3_then_6() {
        assertEquals(6, solve(1, 2, 3));
    }

    @Test
    public void test_if_1_3_2_then_4() {
        assertEquals(4, solve(1, 3, 2));
    }

    @Test
    public void test_if_1_2_2_then_4() {
        assertEquals(4, solve(1, 2, 2));
    }

    @Test
    public void test_if_1_0_2_then_5() {
        assertEquals(5, solve(1, 0, 2));
    }

    @Test
    public void test_if_2_2_1_then_4() {
        assertEquals(4, solve(2, 2, 1));
    }

    @Test
    public void test_if_1_2_4_4_3_then_9() {
        assertEquals(9, solve(1, 2, 4, 4, 3));
    }

    @Test
    public void test_reduceByGroups_0z4_1z4_2z4() {
        Solution.IndexAndRating[] pairs = new Solution.IndexAndRating[]{
                new Solution.IndexAndRating(0, 4),
                new Solution.IndexAndRating(1, 4),
                new Solution.IndexAndRating(2, 4),
        };
        assertArrayEquals(new int[]{1, 1, 1}, solution.reduceByGroups(pairs));
    }

    @Test
    public void test_reduceByGroups_1z4_2z4_0z5() {
        Solution.IndexAndRating[] pairs = new Solution.IndexAndRating[]{
                new Solution.IndexAndRating(1, 4),
                new Solution.IndexAndRating(2, 4),
                new Solution.IndexAndRating(0, 5),
        };
        assertArrayEquals(new int[]{2, 1, 1}, solution.reduceByGroups(pairs));
    }

    @Test
    public void test_reduceByGroups_1z4_0z5_2z5() {
        Solution.IndexAndRating[] pairs = new Solution.IndexAndRating[]{
                new Solution.IndexAndRating(1, 4),
                new Solution.IndexAndRating(0, 5),
                new Solution.IndexAndRating(2, 5),
        };
        assertArrayEquals(new int[]{2, 1, 2}, solution.reduceByGroups(pairs));
    }
}
