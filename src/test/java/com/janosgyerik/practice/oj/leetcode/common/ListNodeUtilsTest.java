package com.janosgyerik.practice.oj.leetcode.common;

import org.junit.Test;

import static com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils.create;
import static org.junit.Assert.*;

public class ListNodeUtilsTest {
    @Test
    public void test_create_empty() {
        assertEquals(null, create());
    }
}
