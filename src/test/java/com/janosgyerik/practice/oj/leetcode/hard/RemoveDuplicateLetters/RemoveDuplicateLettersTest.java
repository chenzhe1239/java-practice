package com.janosgyerik.practice.oj.leetcode.hard.RemoveDuplicateLetters;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveDuplicateLettersTest {

    private final Solution solution = new Solution();

    String solve(String s) {
        return solution.removeDuplicateLetters(s);
    }

    @Test
    public void test_empty() {
        assertThat(solve("")).isEqualTo("");
    }

    @Test
    public void test_bcabc() {
        assertThat(solve("bcabc")).isEqualTo("abc");
    }

    @Test
    public void test_cbacdcbc() {
        assertThat(solve("cbacdcbc")).isEqualTo("acdb");
    }

    @Test
    public void test_bxshkpdwcsjdbikywvioxrypfzfbppydfilfxxtouzzjxaymjpmdoevv() {
        assertThat(solve("bxshkpdwcsjdbikywvioxrypfzfbppydfilfxxtouzzjxaymjpmdoevv")).isEqualTo("bhcsdikworfltuzjxaympev");
    }

    @Test
    public void test_bxshkpdwcsjdbiky() {
        assertThat(solve("bxshkpdwcsjdbiky")).isEqualTo("bxhkpdwcsjiy");
    }
}
