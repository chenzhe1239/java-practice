package com.janosgyerik.practice.oj.leetcode.hard.SmallestRotationWithHighestScore;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    @Test
    public void test_1_3_0_2_4() {
        assertThat(solve(1, 3, 0, 2, 4)).isEqualTo(0);
    }

    @Test
    public void test_2_3_1_4_0() {
        assertThat(solve(2, 3, 1, 4, 0)).isEqualTo(3);
    }

    @Test
    public void test_2_4_1_3_0() {
        assertThat(solve(2, 4, 1, 3, 0)).isEqualTo(0);
    }

    private static int solve(int... arr) {
        return new Solution().bestRotation(arr);
    }
}
