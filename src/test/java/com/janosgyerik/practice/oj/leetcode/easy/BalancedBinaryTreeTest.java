package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BalancedBinaryTreeTest {

    private static final int UNBALANCED = -1;

    public boolean isBalanced(TreeNode root) {
        return depth(root) != UNBALANCED;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.left);
        if (left != UNBALANCED) {
            int right = depth(node.right);
            if (right != UNBALANCED) {
                if (Math.abs(left - right) <= 1) {
                    return 1 + Math.max(left, right);
                }
            }
        }
        return UNBALANCED;
    }

    @Test
    public void testFromString() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertFalse(isBalanced(root));
    }
}
