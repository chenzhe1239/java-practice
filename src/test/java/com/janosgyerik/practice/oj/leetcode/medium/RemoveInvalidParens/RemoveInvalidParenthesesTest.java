package com.janosgyerik.practice.oj.leetcode.medium.RemoveInvalidParens;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RemoveInvalidParenthesesTest {

    private final Solution solution = new Solution();

    private List<String> solve(String input) {
        return solution.removeInvalidParentheses(input);
    }

    @Test
    public void testExample1() {
        assertEquals(Arrays.asList("()()()", "(())()"), solve("()())()"));
    }

    @Test
    public void testExample2() {
        assertEquals(Arrays.asList("(a)()()", "(a())()"), solve("(a)())()"));
    }

    @Test
    public void testExample3() {
        assertEquals(Arrays.asList(""), solve(")("));
    }

    @Test
    public void testSingleLetter() {
        assertEquals(Arrays.asList("n"), solve("n"));
    }

    @Test
    public void testDoubleClose() {
        assertEquals(Arrays.asList(""), solve("))"));
    }

    @Test
    public void testManyClose() {
        assertEquals(Arrays.asList(""), solve(")))))"));
    }

    @Test
    public void testBalanced() {
        assertEquals(Arrays.asList("()"), solve("()"));
    }

    @Test
    public void testTooManyOpen() {
        assertEquals(Arrays.asList("()"), solve("(()"));
    }

    @Test
    public void test_stringWithPosRemoved_hello_0() {
        assertEquals("ello", solution.stringWithPosRemoved("hello", 0));
    }

    @Test
    public void test_stringWithPosRemoved_hello_1() {
        assertEquals("hllo", solution.stringWithPosRemoved("hello", 1));
    }

    @Test
    public void test_stringWithPosRemoved_hello_4() {
        assertEquals("hell", solution.stringWithPosRemoved("hello", 4));
    }
}

