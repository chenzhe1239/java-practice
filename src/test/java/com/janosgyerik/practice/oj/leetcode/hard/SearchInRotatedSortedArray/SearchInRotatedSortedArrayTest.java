package com.janosgyerik.practice.oj.leetcode.hard.SearchInRotatedSortedArray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchInRotatedSortedArrayTest {

    private final Solution solution = new Solution();

    private static final int[] NUMS = {4, 5, 6, 7, 0, 1, 2};

    @Test
    public void test_4_5_6_7_0_1_2_find_5_gives_1() {
        assertEquals(1, solution.search(NUMS, 5));
    }

    @Test
    public void test_4_5_6_7_0_1_2_find_4_gives_0() {
        assertEquals(0, solution.search(NUMS, 4));
    }

    @Test
    public void test_4_5_6_7_0_1_2_find_14_gives_m1() {
        assertEquals(-1, solution.search(NUMS, 14));
    }

    @Test
    public void test_4_5_6_7_0_1_2_find_m4_gives_m1() {
        assertEquals(-1, solution.search(NUMS, -4));
    }

    @Test
    public void test_3_1_find_3_gives_0() {
        assertEquals(0, solution.search(new int[]{3, 1}, 3));
    }

    @Test
    public void test_7_8_1_2_3_4_5_6_find_2_should_give_3() {
        assertEquals(3, solution.search(new int[]{7, 8, 1, 2, 3, 4, 5, 6}, 2));
    }

    @Test
    public void test_3_5_1_find_1_should_give_2() {
        assertEquals(2, solution.search(new int[]{3, 5, 1}, 1));
    }

    @Test
    public void test_2_3_4_5_6_7_8_9_1_find_9_should_give_7() {
        assertEquals(7, solution.search(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1}, 9));
    }
}
