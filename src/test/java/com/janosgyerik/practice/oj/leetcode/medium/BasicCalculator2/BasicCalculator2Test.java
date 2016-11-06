package com.janosgyerik.practice.oj.leetcode.medium.BasicCalculator2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicCalculator2Test {
    private final Solution solution = new Solution();

    int solve(String s) {
        return solution.calculate(s);
    }

    @Test
    public void test_example_1() {
        assertThat(solve("3+2*2")).isEqualTo(7);
    }

    @Test
    public void test_example_2() {
        assertThat(solve(" 3/2 ")).isEqualTo(1);
    }

    @Test
    public void test_example_3() {
        assertThat(solve(" 3+5 / 2 ")).isEqualTo(5);
    }
}
