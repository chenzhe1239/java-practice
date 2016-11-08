package com.janosgyerik.practice.oj.leetcode.medium.ContainsDuplicate3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static class BST {
        private final int diff;
        private Node root;

        public BST(int diff) {
            this.diff = diff;
        }

        public boolean insert(Node node) {
            if (root == null) {
                root = node;
                return false;
            }
            return insert(root, node);
        }

        private boolean insert(Node parent, Node node) {
            if (nearby(parent, node)) {
                return true;
            }
            if (node.num < parent.num) {
                if (parent.left != null) {
                    return insert(parent.left, node);
                }
                parent.left = node;
                node.parent = parent;
                return false;
            }
            if (parent.right != null) {
                return insert(parent.right, node);
            }
            parent.right = node;
            node.parent = parent;
            return false;
        }

        private boolean nearby(Node node1, Node node2) {
            return Math.abs(node1.num - node2.num) <= diff;
        }

        public void remove(Node node) {
            if (node.parent != null) {
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else if (node.parent.right == node) {
                    node.parent.right = null;
                }
                for (Node n : new TreeIterable(node.left)) {
                    insert(n);
                }
                for (Node n : new TreeIterable(node.right)) {
                    insert(n);
                }
            } else {
                if (node.left != null) {
                    node.left.parent = null;
                    root = node.left;
                    for (Node n : new TreeIterable(node.right)) {
                        insert(n);
                    }
                } else if (node.right != null) {
                    node.right.parent = null;
                    root = node.right;
                } else {
                    root = null;
                }
            }
        }
    }

    static class TreeIterable implements Iterable<Node> {
        private final Node node;

        TreeIterable(Node node) {
            this.node = node;
        }

        @Override
        public Iterator<Node> iterator() {
            return new TreeIterator(node);
        }
    }

    static class TreeIterator implements Iterator<Node> {
        Queue<Node> queue = new LinkedList<>();

        TreeIterator(Node node) {
            if (node != null) {
                queue.add(node);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Node next() {
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            return node;
        }
    }

    static class Node {
        private final long num;
        Node parent;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }

        BST bst = new BST(t);
        Queue<Node> queue = new LinkedList<>();
        for (int num : nums) {
            Node node = new Node(num);
            if (bst.insert(node)) {
                return true;
            }
            if (queue.size() == k) {
                bst.remove(queue.poll());
            }
            queue.add(node);
        }
        return false;
    }
}
