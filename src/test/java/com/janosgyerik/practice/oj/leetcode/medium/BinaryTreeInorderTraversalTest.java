package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversalTest {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (current.left != null) {
                stack.push(current);
                TreeNode left = current.left;
                current.left = null;
                current = left;
            } else {
                list.add(current.val);
                if (current.right != null) {
                    current = current.right;
                } else if (stack.isEmpty()) {
                    break;
                } else {
                    current = stack.pop();
                }
            }
        }

        return list;
    }

    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(list, root);
        return list;
    }

    private void inorderTraversal(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(list, node.left);
        list.add(node.val);
        inorderTraversal(list, node.right);
    }
}
