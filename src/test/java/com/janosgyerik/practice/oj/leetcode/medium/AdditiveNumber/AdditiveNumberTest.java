package com.janosgyerik.practice.oj.leetcode.medium.AdditiveNumber;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdditiveNumberTest {

    private final Solution solution = new Solution();

    @Test
    public void should_return_true_for_112358() {
        assertTrue(solution.isAdditiveNumber("112358"));
    }

    @Test
    public void should_return_true_for_199100199() {
        assertTrue(solution.isAdditiveNumber("199100199"));
    }

    @Test
    public void should_return_false_for_1991001991() {
        assertFalse(solution.isAdditiveNumber("1991001991"));
    }

    @Test
    public void should_return_false_for_0235813() {
        assertFalse(solution.isAdditiveNumber("0235813"));
    }

    @Test
    public void should_return_false_for_101() {
        assertTrue(solution.isAdditiveNumber("101"));
    }

    @Test
    public void should_return_true_for_123() {
        assertTrue(solution.isAdditiveNumber("123"));
    }

    @Test
    public void should_return_true_for_221474836472147483649() {
        assertTrue(solution.isAdditiveNumber("221474836472147483649"));
    }
}
