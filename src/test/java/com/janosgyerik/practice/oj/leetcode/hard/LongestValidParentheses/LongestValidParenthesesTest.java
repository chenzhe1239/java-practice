package com.janosgyerik.practice.oj.leetcode.hard.LongestValidParentheses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestValidParenthesesTest {

    private final Solution solution = new Solution();

    @Test
    public void test_ooc_2() {
        assertEquals(2, solution.longestValidParentheses("(()"));
    }

    @Test
    public void test_cococc_4() {
        assertEquals(4, solution.longestValidParentheses(")()())"));
    }

    @Test
    public void test_oc_2() {
        assertEquals(2, solution.longestValidParentheses("()"));
    }

    @Test
    public void test_ocoocc_6() {
        assertEquals(6, solution.longestValidParentheses("()(())"));
    }
}
