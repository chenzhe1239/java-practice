package com.janosgyerik.practice.oj.leetcode.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeBreadthFirstIteratorTest {

    private void assertReversibleSerialization(String string) {
        TreeNode root = TreeNodeUtils.deserialize(string);
        TreeNodeBreadthFirstIterator iterator =
                new TreeNodeBreadthFirstIterator(root);
        assertEquals(string, TreeNodeUtils.serialize(iterator));
    }

    @Test
    public void test_1_2_3() {
        assertReversibleSerialization("{1,2,3}");
    }

    @Test
    public void test_1_x_3() {
        assertReversibleSerialization("{1,#,3}");
    }

    @Test
    public void test_1_2_2_3_4_4_3() {
        String s = "{1,2,2,3,4,4,3}";
        assertReversibleSerialization(s);
    }

    @Test
    public void test_1_2_2_x_3_x_3() {
        String s = "{1,2,2,#,3,#,3}";
        assertReversibleSerialization(s);
    }
}