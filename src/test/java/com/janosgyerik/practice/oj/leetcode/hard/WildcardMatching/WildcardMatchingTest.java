package com.janosgyerik.practice.oj.leetcode.hard.WildcardMatching;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildcardMatchingTest {
    private final Solution solution = new Solution();

    private boolean isMatch(String text, String pattern) {
        return solution.isMatch(text, pattern);
    }

    @Test
    public void test_aa_a() {
        assertFalse(isMatch("aa", "a"));
    }

    @Test
    public void test_a_aa() {
        assertFalse(isMatch("a", "aa"));
    }

    @Test
    public void test_aa_aa() {
        assertTrue(isMatch("aa", "aa"));
    }

    @Test
    public void test_aaa_a() {
        assertFalse(isMatch("aaa", "a"));
    }

    @Test
    public void test_aa_S() {
        assertTrue(isMatch("aa", "*"));
    }

    @Test
    public void test_aa_aS() {
        assertTrue(isMatch("aa", "a*"));
    }

    @Test
    public void test_aa_QS() {
        assertTrue(isMatch("aa", "?*"));
    }

    @Test
    public void test_aab_cSaSb() {
        assertFalse(isMatch("aab", "c*a*b"));
    }

    @Test
    public void test_aabcbc_aabScbs() {
        assertTrue(isMatch("aabcbc", "aab*cbc"));
    }

    @Test
    public void test_aabcbc_aabScSc() {
        assertTrue(isMatch("aabcbc", "aab*c*c"));
    }

    @Test
    public void test_aabcbc_aab_star_c_q_c() {
        assertTrue(isMatch("aabcbc", "aab*c?c"));
    }

    @Test
    public void test_many_stars() {
        assertFalse(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
    }
}
