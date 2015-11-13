package com.janosgyerik.practice.codility.easy;

import org.junit.Assert;
import org.junit.Test;

public class BracketsTest {
    @Test
    public void testEmptyIsBalanced() {
        Assert.assertTrue(submit(""));
    }

    @Test
    public void testSimplePairs() {
        Assert.assertTrue(submit("()"));
        Assert.assertTrue(submit("[]"));
        Assert.assertTrue(submit("{}"));
    }

    @Test
    public void testNestedPairs() {
        Assert.assertTrue(submit("({})"));
    }

    @Test
    public void testDoubles() {
        Assert.assertTrue(submit("{}{}"));
        Assert.assertTrue(submit("{}()"));
    }

    @Test
    public void testTriplets() {
        Assert.assertTrue(submit("{}{}{}"));
        Assert.assertTrue(submit("[]{}{}"));
        Assert.assertTrue(submit("{}(){}"));
    }

    @Test
    public void testNestedDoubles() {
        Assert.assertTrue(submit("({}{}){}"));
    }

    @Test
    public void testUnbalanced() {
        Assert.assertFalse(submit("("));
        Assert.assertFalse(submit(")"));
    }

    @Test
    public void testNonMatching() {
        Assert.assertFalse(submit("(}"));
        Assert.assertFalse(submit("[)"));
        Assert.assertFalse(submit("({)}"));
    }

    private boolean submit(String s) {
        return new Brackets().submit(s) == 1;
    }
}
