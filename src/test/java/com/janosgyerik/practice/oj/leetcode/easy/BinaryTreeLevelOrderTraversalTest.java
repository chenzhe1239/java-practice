package com.janosgyerik.practice.oj.leetcode.easy;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;
import com.janosgyerik.practice.oj.leetcode.common.TreeNodeUtils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryTreeLevelOrderTraversalTest {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<Integer> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (TreeNode node : new ArrayList<>(nodes)) {
                level.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }
        return levels;
    }

    @Test
    public void test_empty() {
        assertTrue(levelOrder(null).isEmpty());
    }

    @Test
    public void test_singleton() {
        int val = 3;
        List<List<Integer>> levels = levelOrder(new TreeNode(val));
        assertEquals(1, levels.size());
        assertEquals(1, levels.get(0).size());
        assertEquals(val, levels.get(0).get(0).intValue());
    }

    @Test
    public void test_3_9_20_x_x_15_7() {
        assertEquals("[[3], [9, 20], [15, 7]]", levelOrder(TreeNodeUtils.deserialize("{3,9,20,#,#,15,7}")).toString());
    }
}
