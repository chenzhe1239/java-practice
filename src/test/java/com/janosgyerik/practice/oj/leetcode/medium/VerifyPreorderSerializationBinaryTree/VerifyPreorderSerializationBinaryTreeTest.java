package com.janosgyerik.practice.oj.leetcode.medium.VerifyPreorderSerializationBinaryTree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerifyPreorderSerializationBinaryTreeTest {

    private final Solution solution = new Solution();

    @Test
    public void test_null() {
        assertEquals(true, solution.isValidSerialization("#"));
    }

    @Test
    public void test_long_example() {
        assertEquals(true, solution.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    @Test
    public void test_1_x() {
        assertEquals(false, solution.isValidSerialization("1,#"));
    }

    @Test
    public void test_1_x_x_x_x() {
        assertEquals(false, solution.isValidSerialization("1,#,#,#,#"));
    }

    @Test
    public void test_9_x_x_1() {
        assertEquals(false, solution.isValidSerialization("9,#,#,1"));
    }
}
