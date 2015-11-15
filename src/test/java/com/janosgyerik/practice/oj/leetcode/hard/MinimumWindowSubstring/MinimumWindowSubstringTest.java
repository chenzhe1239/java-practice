package com.janosgyerik.practice.oj.leetcode.hard.MinimumWindowSubstring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumWindowSubstringTest {

    private final Solution solution = new Solution();

    private String solve(String s, String t) {
        return solution.minWindow(s, t);
    }

    @Test
    public void test_ADOBECODEBANC_ABC() {
        assertEquals("BANC", solve("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void test_a_aa() {
        assertEquals("", solve("a", "aa"));
    }

    @Test
    public void test_aaab_aab() {
        assertEquals("aab", solve("aaab", "aab"));
    }
}
