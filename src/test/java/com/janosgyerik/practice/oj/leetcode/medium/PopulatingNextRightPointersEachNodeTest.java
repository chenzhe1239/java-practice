package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeLinkNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PopulatingNextRightPointersEachNodeTest {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<TreeLinkNode> level = new ArrayList<>(queue);
            for (int i = 0; i < level.size(); ++i) {
                TreeLinkNode node = queue.poll();
                if (i < level.size() - 1) {
                    node.next = level.get(i + 1);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
}
