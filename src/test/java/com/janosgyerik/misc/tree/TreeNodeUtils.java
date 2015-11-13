package com.janosgyerik.misc.tree;

import java.util.*;

public class TreeNodeUtils {
    private TreeNodeUtils() {
        // forbidden
    }

    static <T> TreeNode<T> toTree(List<T> values) {
        if (values.isEmpty()) {
            return null;
        }

        Iterator<T> iterator = values.iterator();
        TreeNode<T> root = new TreeNode<>(iterator.next());

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (iterator.hasNext()) {
            TreeNode<T> node = queue.poll();
            T left = iterator.next();

            if (left != null) {
                node.left = new TreeNode<>(left);
                queue.add(node.left);
            }

            if (iterator.hasNext()) {
                T right = iterator.next();
                if (right != null) {
                    node.right = new TreeNode<>(right);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    static <T> List<T> toList(TreeNode<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }

        LinkedList<T> values = new LinkedList<>();

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            for (TreeNode<T> node : new LinkedList<>(queue)) {
                queue.poll();
                if (node != null) {
                    values.add(node.value);
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    values.add(null);
                }
            }
        }
        while (values.peekLast() == null) {
            values.removeLast();
        }
        return values;
    }
}
