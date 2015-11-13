package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import com.janosgyerik.practice.oj.leetcode.common.TreeNodeUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class BinaryTreePreorderTraversalTest {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    private void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    private String preorderTraversal(String serialized) {
        return preorderTraversal(TreeNodeUtils.deserialize(serialized)).toString();
    }

    @Test
    public void test_1_x_2_3() {
        assertEquals("[1, 2, 3]", preorderTraversal("{1,#,2,3}"));
    }
}
