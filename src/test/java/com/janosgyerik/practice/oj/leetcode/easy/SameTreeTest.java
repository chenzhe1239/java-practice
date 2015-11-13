package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameTreeTest {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    @Test
    public void test_both_empty() {
        assertTrue(isSameTree(null, null));
    }

    @Test
    public void test_first_empty() {
        assertFalse(isSameTree(null, new TreeNode(3)));
    }

    @Test
    public void test_second_empty() {
        assertFalse(isSameTree(new TreeNode(3), null));
    }

    @Test
    public void test_same_singleton() {
        int val = 3;
        assertTrue(isSameTree(new TreeNode(val), new TreeNode(val)));
    }

    @Test
    public void test_same_top_left() {
        int topVal = 3;
        int leftVal = 4;
        TreeNode first = new TreeNode(topVal);
        first.left = new TreeNode(leftVal);
        TreeNode second = new TreeNode(topVal);
        second.left = new TreeNode(leftVal);
        assertTrue(isSameTree(first, second));
    }

    @Test
    public void test_branch_flipped() {
        int topVal = 3;
        int leftVal = 4;
        TreeNode first = new TreeNode(topVal);
        first.left = new TreeNode(leftVal);
        TreeNode second = new TreeNode(topVal);
        second.right = new TreeNode(leftVal);
        assertFalse(isSameTree(first, second));
    }

    @Test
    public void test_left_value_mismatch() {
        int topVal = 3;
        int leftVal = 4;
        TreeNode first = new TreeNode(topVal);
        first.left = new TreeNode(leftVal);
        TreeNode second = new TreeNode(topVal);
        second.left = new TreeNode(leftVal + 1);
        assertFalse(isSameTree(first, second));
    }

}
