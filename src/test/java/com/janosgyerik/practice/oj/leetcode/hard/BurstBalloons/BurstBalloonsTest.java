package com.janosgyerik.practice.oj.leetcode.hard.BurstBalloons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurstBalloonsTest {
    private final Solution solution = new Solution();

    private int solve(int... nums) {
        return solution.maxCoins(nums);
    }

    @Test
    public void test_one_balloon_3() {
        int balloon = 3;
        assertEquals(balloon, solve(balloon));
    }

    @Test
    public void test_balloons_2_3() {
        int b2 = 2;
        int b3 = 3;
        int expected = b2 * b3 + b3;
        assertEquals(expected, solve(b2, b3));
    }

    @Test
    public void test_balloons_3_1_5_8() {
        int expected = 167;
        assertEquals(expected, solve(3, 1, 5, 8));
    }

    @Test
    public void should_get_167_for_3_1_5_8() {
        assertEquals(167, solve(3, 1, 5, 8));
    }

    @Test
    public void should_get_0_for_empty() {
        assertEquals(0, solve());
    }

    @Test
    public void should_get_7_for_7() {
        assertEquals(7, solve(7));
    }

    @Test
    public void should_get_21_for_7_2() {
        assertEquals(21, solve(7, 2));
    }

    @Test
    public void should_get_27_for_1_9_1() {
        assertEquals(27, solve(1, 9, 1));
    }

    @Test
    public void should_get_44416_for_9_76_64() {
        assertEquals(44416, solve(9, 76, 64));
    }

    @Test
    public void should_get_1086136_for_9_76_64_21_97_60() {
        assertEquals(1086136, solve(9, 76, 64, 21, 97, 60));
    }
}
