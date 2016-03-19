package com.janosgyerik.practice.oj.leetcode.medium.LongestPalindromicSubstring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestPalindromicSubstringTest {

    private final Solution solution = new Solution();

    @Test
    public void should_return_a_for_a() {
        assertEquals("a", solution.longestPalindrome("a"));
    }

    @Test
    public void should_return_bb_for_abbc() {
        assertEquals("bb", solution.longestPalindrome("abbc"));
    }

    @Test
    public void should_return_ccc_for_abbccc() {
        assertEquals("ccc", solution.longestPalindrome("abbccc"));
    }

    @Test
    public void should_return_cbbc_for_acbbccc() {
        assertEquals("cbbc", solution.longestPalindrome("acbbccc"));
    }
}
