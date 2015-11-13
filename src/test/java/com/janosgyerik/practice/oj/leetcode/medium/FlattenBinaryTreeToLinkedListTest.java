package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;

public class FlattenBinaryTreeToLinkedListTest {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);

        if (root.left != null) {
            flatten(root.left);
            appendToRight(root.left, root.right);
            root.right = root.left;
            root.left = null;
        }
    }

    private void appendToRight(TreeNode root, TreeNode toAppend) {
        if (root.right == null) {
            root.right = toAppend;
        } else {
            appendToRight(root.right, toAppend);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
