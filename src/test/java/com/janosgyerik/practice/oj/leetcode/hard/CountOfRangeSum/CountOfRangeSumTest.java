
package com.janosgyerik.practice.oj.leetcode.hard.CountOfRangeSum;

import com.janosgyerik.practice.oj.leetcode.medium.Other.IntegerToRomanTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountOfRangeSumTest {

    private final Solution solution = new Solution();

    @Test
    public void test_m2_5_m1_within_m2_2_gives_3() {
        assertEquals(3, solution.countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }

    @Test
    public void test_1_4_m2_3_m4_3_0_m4_4_within_3_6_gives_16() {
        assertEquals(16, solution.countRangeSum(new int[]{1, 4, -2, 3, -4, 3, 0, -4, 4}, 3, 6));
    }

    @Test
    public void test_m1_1_within_0_0_should_give_1() {
        assertEquals(1, solution.countRangeSum(new int[]{-1, 1}, 0, 0));
    }

    @Test
    public void test_intmax_intmin_m1_0_within_m1_0_should_give_4() {
        assertEquals(4, solution.countRangeSum(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, -1, 0}, -1, 0));
    }

    @Test
    public void test_intmin_0_intmin_intmax_within_m564_3864_should_give_3() {
        assertEquals(3, solution.countRangeSum(new int[]{
                Integer.MIN_VALUE + 1, 0, Integer.MIN_VALUE + 1, Integer.MAX_VALUE
        }, -564, 3864));
    }
}
