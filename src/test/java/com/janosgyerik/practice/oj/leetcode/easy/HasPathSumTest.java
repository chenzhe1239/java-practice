package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HasPathSumTest {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int nextSum = sum - root.val;
        if (nextSum == 0 && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, nextSum) || hasPathSum(root.right, nextSum);
    }

    @Test
    public void testExample2() {
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                        null
                ),
                new TreeNode(8,
                        new TreeNode(13), new TreeNode(4, null, new TreeNode(1)))
        );
        assertTrue(hasPathSum(root, 22));
        assertFalse(hasPathSum(root, 21));
        assertTrue(hasPathSum(root, 26));
        assertTrue(hasPathSum(root, 18));
    }

    @Test
    public void testEmpty() {
        assertFalse(hasPathSum((TreeNode) null, 1));
    }

    @Test
    public void test_1_2() {
        assertFalse(hasPathSum(new TreeNode(1, new TreeNode(2), null), 1));
    }

    public boolean hasPathSum(int[] arr, int targetSum) {
        int sum = 0;
        int front = 0;
        int back = 0;
        int len = arr.length;
        while (back < len) {
            if (sum == targetSum) {
                return true;
            }
            while (back < len && sum < targetSum) {
                sum += arr[back++];
            }
            while (front < back && sum > targetSum) {
                sum -= arr[front++];
            }
        }
        return false;
    }

    @Test
    public void testExample() {
        assertFalse(hasPathSum(new int[]{1, 2, 3, 4, 5}, 99));
        assertTrue(hasPathSum(new int[]{1, 2, 3, 4, 5}, 7));
        assertTrue(hasPathSum(new int[]{1, 2, 3, 4, 5}, 9));
        assertFalse(hasPathSum(new int[]{1, 2, 3, 4, 5}, 8));
    }

    @Test
    public void test_6_2_7_4_1_3_6__12() {
        assertTrue(hasPathSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 12));
    }

    @Test
    public void test_6_2_7_4_1_3_6__13() {
        assertTrue(hasPathSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 13));
    }

    @Test
    public void test_6_2_7_4_1_3_6__14() {
        assertTrue(hasPathSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 14));
    }

    @Test
    public void test_6_2_7_4_1_3_6__15() {
        assertTrue(hasPathSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 15));
    }

    @Test
    public void test_6_2_7_4_1_3_6__16() {
        assertFalse(hasPathSum(new int[]{6, 2, 7, 4, 1, 3, 6}, 16));
    }

    @Test
    public void test_6_2_7_14_1_3_6__12() {
        assertFalse(hasPathSum(new int[]{6, 2, 7, 14, 1, 3, 6}, 12));
    }
}
