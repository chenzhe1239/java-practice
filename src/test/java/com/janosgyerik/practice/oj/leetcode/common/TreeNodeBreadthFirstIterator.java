package com.janosgyerik.practice.oj.leetcode.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeBreadthFirstIterator implements Iterator<Integer> {

    protected final Queue<TreeNode> nodeQueue = new LinkedList<>();

    public TreeNodeBreadthFirstIterator(TreeNode node) {
        if (node != null) {
            nodeQueue.add(node);
        }
    }

    @Override
    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }

    @Override
    public Integer next() {
        TreeNode node = nodeQueue.poll();
        if (node == null) {
            return null;
        }
        if (node.left != null || node.right != null) {
            enqueueFirstChild(node);
            enqueueSecondChild(node);
        }
        return node.val;
    }

    public void enqueueFirstChild(TreeNode node) {
        enqueueNode(node.left);
    }

    public void enqueueSecondChild(TreeNode node) {
        enqueueNode(node.right);
    }

    public void enqueueNode(TreeNode node) {
        nodeQueue.add(node);
    }
}
