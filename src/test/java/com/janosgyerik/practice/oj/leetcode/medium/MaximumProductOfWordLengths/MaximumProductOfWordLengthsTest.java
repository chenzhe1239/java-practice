package com.janosgyerik.practice.oj.leetcode.medium.MaximumProductOfWordLengths;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaximumProductOfWordLengthsTest {

    private final Solution solution = new Solution();

    @Test
    public void test_example_1() {
        assertEquals(16, solution.maxProduct("abcw", "baz", "foo", "bar", "xtfn", "abcdef"));
    }

    @Test
    public void test_example_2() {
        assertEquals(4, solution.maxProduct("a", "ab", "abc", "d", "cd", "bcd", "abcd"));
    }

    @Test
    public void test_example_3() {
        assertEquals(0, solution.maxProduct("a", "aa", "aaa", "aaaa"));
    }
}
