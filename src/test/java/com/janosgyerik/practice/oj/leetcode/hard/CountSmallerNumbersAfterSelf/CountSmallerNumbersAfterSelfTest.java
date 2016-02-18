package com.janosgyerik.practice.oj.leetcode.hard.CountSmallerNumbersAfterSelf;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountSmallerNumbersAfterSelfTest {

    private final Solution solution = new Solution();

    private List<Integer> solve(int... nums) {
        return solution.countSmaller(nums);
    }

    @Test
    public void test_for_5_2_6_1_should_return_2_1_1_0() {
        assertEquals(Arrays.asList(2, 1, 1, 0), solve(5, 2, 6, 1));
    }

    @Test
    public void test_for_m1_should_return_0() {
        assertEquals(Collections.singletonList(0), solve(-1));
    }

    @Test
    public void test_for_m1_m1_should_return_0_0() {
        assertEquals(Arrays.asList(0, 0), solve(-1, -1));
    }
}
