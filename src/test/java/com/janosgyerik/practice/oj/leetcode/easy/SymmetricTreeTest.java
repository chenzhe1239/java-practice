package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import com.janosgyerik.practice.oj.leetcode.common.TreeNodeBreadthFirstIterator;
import com.janosgyerik.practice.oj.leetcode.common.TreeNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SymmetricTreeTest {
    private static class DeeperIterator extends TreeNodeBreadthFirstIterator {
        public DeeperIterator(TreeNode node) {
            super(node);
        }

        @Override
        public Integer next() {
            TreeNode node = nodeQueue.poll();
            if (node == null) {
                return null;
            }
            enqueueFirstChild(node);
            enqueueSecondChild(node);
            return node.val;
        }
    }

    private static class RightIterator extends DeeperIterator {
        public RightIterator(TreeNode node) {
            super(node);
        }

        @Override
        public void enqueueFirstChild(TreeNode node) {
            enqueueNode(node.right);
        }

        @Override
        public void enqueueSecondChild(TreeNode node) {
            enqueueNode(node.left);
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        return left.val == right.val
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric_Iterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        DeeperIterator leftIterator = new DeeperIterator(root.left);
        RightIterator rightIterator = new RightIterator(root.right);
        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Integer left = leftIterator.next();
            Integer right = rightIterator.next();
            if (left == null ^ right == null) {
                return false;
            }
            if (left != null && !left.equals(right)) {
                return false;
            }
        }
        return !leftIterator.hasNext() && !rightIterator.hasNext();
    }

    @Test
    public void test_empty() {
        assertTrue(isSymmetric(TreeNodeUtils.deserialize("{}")));
    }

    @Test
    public void test_1() {
        assertTrue(isSymmetric(TreeNodeUtils.deserialize("{1}")));
    }

    @Test
    public void test_1_2_2() {
        assertTrue(isSymmetric(TreeNodeUtils.deserialize("{1,2,2}")));
    }

    @Test
    public void test_1_2() {
        assertFalse(isSymmetric(TreeNodeUtils.deserialize("{1,2}")));
    }

    @Test
    public void test_1_2_2_3_4_4_3() {
        assertTrue(isSymmetric(TreeNodeUtils.deserialize("{1,2,2,3,4,4,3}")));
    }

    @Test
    public void test_1_2_2_x_3_x_3() {
        assertFalse(isSymmetric(TreeNodeUtils.deserialize("{1,2,2,#,3,#,3}")));
    }

    @Test
    public void test_1_2_2_3_x_x_3_4_x_x_4() {
        assertTrue(isSymmetric(TreeNodeUtils.deserialize("{1,2,2,3,#,#,3,4,#,#,4}")));
    }

    @Test
    public void test_1_2_2_3_x_x_3_4_x_4() {
        assertFalse(isSymmetric(TreeNodeUtils.deserialize("{1,2,2,3,#,#,3,4,#,4}")));
    }

    @Test
    public void test_2_3_3_4_5_5_4_x_x_8_9_x_x_9_8() {
        assertFalse(isSymmetric(TreeNodeUtils.deserialize("{2,3,3,4,5,5,4,#,#,8,9,#,#,9,8}")));
    }

    @Test
    public void test_1_2_3() {
        assertFalse(isSymmetric(TreeNodeUtils.deserialize("{1,2,3}")));
    }
}
