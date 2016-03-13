package com.janosgyerik.practice.oj.leetcode.hard.FindDuplicateNumber;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindDuplicateNumberTest {

    private final Solution solution = new Solution();

    @Test
    public void test_1_1_should_give_1() {
        assertEquals(1, solution.findDuplicate(1, 1));
    }

    @Test
    public void test_2_1_1_should_give_1() {
        assertEquals(1, solution.findDuplicate(2, 1, 1));
    }

    @Test
    public void test_2_1_2_should_give_2() {
        assertEquals(2, solution.findDuplicate(2, 1, 2));
    }

    @Test
    public void test_1_1_1_should_give_1() {
        assertEquals(1, solution.findDuplicate(1, 1, 1));
    }
}
