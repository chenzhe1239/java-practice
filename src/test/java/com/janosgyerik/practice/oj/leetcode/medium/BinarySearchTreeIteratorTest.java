package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

class BSTIterator {

    private final Stack<TreeNode> nodes = new Stack<>();
    private int currentVal;
    private boolean foundNext = false;

    public BSTIterator(TreeNode root) {
        if (root != null) {
            findSmallestAndSetCurrentVal(root);
        }
    }

    private void findSmallestAndSetCurrentVal(TreeNode node) {
        nodes.push(node);
        if (node.left != null) {
            findSmallestAndSetCurrentVal(node.left);
        } else {
            currentVal = node.val;
            foundNext = true;
        }
    }

    public boolean hasNext() {
        return foundNext;
    }

    public int next() {
        int previousVal = currentVal;
        TreeNode last = nodes.peek();
        if (last.right != null) {
            findSmallestAndSetCurrentVal(last.right);
        } else {
            while (nodes.peek().val <= currentVal) {
                nodes.pop();
                if (nodes.empty()) {
                    foundNext = false;
                    return currentVal;
                }
            }
            currentVal = nodes.peek().val;
        }
        return previousVal;
    }
}

public class BinarySearchTreeIteratorTest {
    @Test
    public void test_empty() {
        assertFalse(new BSTIterator(null).hasNext());
    }

    @Test
    public void test_1() {
        BSTIterator iterator = new BSTIterator(new TreeNode(12));
        assertTrue(iterator.hasNext());
    }

    private List<Integer> toList(BSTIterator iterator) {
        List<Integer> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    private void assertValuesInSequence(BSTIterator iterator) {
        assertTrue(iterator.hasNext());
        int i = 1;
        while (iterator.hasNext()) {
            assertEquals(i++, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void test_1_2_3() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        assertValuesInSequence(new BSTIterator(root));
    }

    @Test
    public void test_1_2_3_4_5() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);

        assertValuesInSequence(new BSTIterator(root));
    }

    @Test
    public void test_1_2_left() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);

        assertEquals(Arrays.asList(1, 2), toList(new BSTIterator(root)));
    }

    @Test
    public void test_1_2_right() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        assertEquals(Arrays.asList(1, 2), toList(new BSTIterator(root)));
    }
}
