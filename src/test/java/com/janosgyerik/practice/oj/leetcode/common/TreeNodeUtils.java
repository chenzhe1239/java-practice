package com.janosgyerik.practice.oj.leetcode.common;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeUtils {

    private static final String NULL_MARKER = "#";

    private TreeNodeUtils() {
        // utility class, forbidden constructor
    }

    public static TreeNode deserialize(String string) {
        String[] values = string.substring(1, string.length() - 1).split(",");
        if (values[0].isEmpty()) {
            return null;
        }

        Queue<TreeNode> parents = new LinkedList<>();

        TreeNode root = parseValueAndCreateNode(values[0]);
        parents.add(root);

        for (int i = 1; i < values.length; ++i) {
            TreeNode parent = parents.poll();

            TreeNode left = parseValueAndCreateNode(values[i]);
            if (left != null) {
                parent.left = left;
                parents.add(left);
            }
            if (i + 1 < values.length) {
                TreeNode right = parseValueAndCreateNode(values[++i]);
                if (right != null) {
                    parent.right = right;
                    parents.add(right);
                }
            }
        }

        return root;
    }

    private static TreeNode parseValueAndCreateNode(String value) {
        return value.equals(NULL_MARKER) ? null : new TreeNode(Integer.parseInt(value));
    }

    public static String serialize(TreeNodeBreadthFirstIterator iterator) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        if (iterator.hasNext()) {
            builder.append(iterator.next());
            while (iterator.hasNext()) {
                Integer value = iterator.next();
                builder.append(",").append(value == null ? "#" : value);
            }
        }
        return builder.append("}").toString();
    }
}

