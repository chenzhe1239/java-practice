package com.janosgyerik.practice.oj.leetcode.medium.DecodeWays;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DecodeWaysTest {
    private final Solution solution = new Solution();

    int solve(String s) {
        return solution.numDecodings(s);
    }

    @Test
    public void test_empty_string_gives_0() {
        assertThat(solve("")).isEqualTo(0);
    }

    @Test
    public void test_single_digit_gives_1() {
        assertThat(solve("1")).isEqualTo(1);
    }

    @Test
    public void test_10_gives_1() {
        assertThat(solve("10")).isEqualTo(1);
    }

    @Test
    public void test_33_gives_1() {
        assertThat(solve("33")).isEqualTo(1);
    }

    @Test
    public void test_121_gives_3() {
        assertThat(solve("121")).isEqualTo(3);
    }

    @Test
    public void test_1212_gives_5() {
        assertThat(solve("1212")).isEqualTo(5);
    }

    @Test
    public void test_27_gives_1() {
        assertThat(solve("27")).isEqualTo(1);
    }

    @Test
    public void test_0_gives_0() {
        assertThat(solve("0")).isEqualTo(0);
    }

    @Test
    public void test_00_gives_0() {
        assertThat(solve("00")).isEqualTo(0);
    }

    @Test
    public void test_01_gives_0() {
        assertThat(solve("01")).isEqualTo(0);
    }

    @Test
    public void test_227_gives_2() {
        assertThat(solve("227")).isEqualTo(2);
    }

    @Test
    public void test_230_gives_0() {
        assertThat(solve("230")).isEqualTo(0);
    }

    @Test
    public void test_1090_gives_0() {
        assertThat(solve("1090")).isEqualTo(0);
    }

    @Test
    public void test_17_gives_2() {
        assertThat(solve("17")).isEqualTo(2);
    }

    @Test
    public void test_100_gives_0() {
        assertThat(solve("100")).isEqualTo(0);
    }

    @Test
    public void test_1010_gives_1() {
        assertThat(solve("1010")).isEqualTo(1);
    }

    @Test
    public void test_110_gives_1() {
        assertThat(solve("110")).isEqualTo(1);
    }
}
