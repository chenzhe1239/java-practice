package com.janosgyerik.practice.oj.leetcode.hard.RegularExpressionMatching;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegularExpressionMatchingTest {

    private final Solution solution = new Solution();

    private boolean solve(String s, String p) {
        return solution.isMatch(s, p);
    }

    @Test
    public void test_aa_a() {
        assertFalse(solve("aa", "a"));
    }

    @Test
    public void test_aa_aa() {
        assertTrue(solve("aa", "aa"));
    }

    @Test
    public void test_aaa_aa() {
        assertFalse(solve("aaa", "aa"));
    }

    @Test
    public void test_aa_astar() {
        assertTrue(solve("aa", "a*"));
    }

    @Test
    public void test_aa_dotstar() {
        assertTrue(solve("aa", ".*"));
    }

    @Test
    public void test_ab_dotstar() {
        assertTrue(solve("ab", ".*"));
    }

    @Test
    public void test_aab_cstar_astar_b() {
        assertTrue(solve("ab", "c*a*b"));
    }

    @Test
    public void test_empty_empty_matches() {
        assertTrue(solve("", ""));
    }

    @Test
    public void test_nonempty_empty_fails() {
        assertFalse(solve("a", ""));
    }

    @Test
    public void test_aaa_astara_matches() {
        assertTrue(solve("aaa", "a*a"));
    }

    @Test
    public void test_aaa_dotstardot_matches() {
        assertTrue(solve("aaa", ".*."));
    }

    @Test
    public void test_aaa_abstarastarcstara_matches() {
        assertTrue(solve("aaa", "ab*a*c*a"));
    }
}
