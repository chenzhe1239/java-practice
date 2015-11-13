package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinPathBinTreeTest {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left) {
            this(x, left, null);
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    @Test
    public void testEmpty() {
        assertEquals(0, minDepth(null));
    }

    @Test
    public void testSingle() {
        assertEquals(1, minDepth(new TreeNode(3)));
    }

    @Test
    public void test_1_2() {
        assertEquals(2, minDepth(new TreeNode(3, new TreeNode(4))));
    }

    @Test
    public void testLinearExample_1() {
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11)));
        assertEquals(3, minDepth(root));
    }

    @Test
    public void testLinearExample_2() {
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11)), new TreeNode(7));
        assertEquals(2, minDepth(root));
    }

    @Test
    public void testDeeperExample() {
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2))), new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(1))));
        assertEquals(3, minDepth(root));
    }
}
