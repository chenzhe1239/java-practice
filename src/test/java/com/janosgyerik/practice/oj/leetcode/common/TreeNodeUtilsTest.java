package com.janosgyerik.practice.oj.leetcode.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TreeNodeUtilsTest {

    private TreeNode deserialize(String string) {
        return TreeNodeUtils.deserialize(string);
    }

    @Test
    public void testDeserialize_empty() {
        assertNull(deserialize("{}"));
    }

    @Test
    public void testDeserialize_1_2_3() {
        TreeNode root = deserialize("{1,2,3}");

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertEquals(null, root.left.left);
        assertEquals(null, root.left.right);

        assertEquals(3, root.right.val);
        assertEquals(null, root.right.left);
        assertEquals(null, root.right.right);
    }

    @Test
    public void testDeserialize_1_2() {
        TreeNode root = deserialize("{1,2}");

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertEquals(null, root.left.left);
        assertEquals(null, root.left.right);

        assertEquals(null, root.right);
    }

    @Test
    public void testDeserialize_1_2_x_3() {
        TreeNode root = deserialize("{1,2,#,3}");

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertEquals(3, root.left.left.val);
        assertEquals(null, root.left.right);

        assertEquals(null, root.right);
    }

    @Test
    public void testDeserialize_1_x_2_3() {
        TreeNode root = deserialize("{1,#,2,3}");

        assertEquals(1, root.val);

        assertEquals(null, root.left);
        assertEquals(2, root.right.val);

        assertEquals(3, root.right.left.val);
        assertEquals(null, root.right.left.left);
        assertEquals(null, root.right.left.right);
    }

    @Test
    public void testDeserialize_1_2_3_x_x_4_x_x_5() {
        TreeNode root = deserialize("{1,2,3,#,#,4,#,#,5}");

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertEquals(null, root.left.left);
        assertEquals(null, root.left.right);

        assertEquals(3, root.right.val);
        assertEquals(4, root.right.left.val);
        assertEquals(null, root.right.right);

        assertEquals(null, root.right.left.left);
        assertEquals(5, root.right.left.right.val);
        assertEquals(null, root.right.left.right.left);
        assertEquals(null, root.right.left.right.right);
    }

    @Test
    public void testDeserialize_1_x_2_x_3_x_4_x_5() {
        TreeNode root = deserialize("{1,#,2,#,3,#,4,#,5}");

        assertEquals(1, root.val);
        assertEquals(null, root.left);

        assertEquals(2, root.right.val);
        assertEquals(null, root.right.left);

        assertEquals(3, root.right.right.val);
        assertEquals(null, root.right.right.left);

        assertEquals(4, root.right.right.right.val);
        assertEquals(null, root.right.right.right.left);

        assertEquals(5, root.right.right.right.right.val);
        assertEquals(null, root.right.right.right.right.left);
        assertEquals(null, root.right.right.right.right.right);
    }

    @Test
    public void testDeserialize_1_2_x_3_x_4() {
        TreeNode root = deserialize("{1,2,#,3,#,4}");

        assertEquals(1, root.val);
        assertEquals(null, root.right);

        assertEquals(2, root.left.val);
        assertEquals(null, root.left.right);

        assertEquals(3, root.left.left.val);
        assertEquals(null, root.left.left.right);

        assertEquals(4, root.left.left.left.val);
        assertEquals(null, root.left.left.left.left);
        assertEquals(null, root.left.left.left.right);
    }
}
