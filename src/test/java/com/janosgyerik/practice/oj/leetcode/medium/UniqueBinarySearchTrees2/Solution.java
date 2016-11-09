package com.janosgyerik.practice.oj.leetcode.medium.UniqueBinarySearchTrees2;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<TreeNode> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (List<Integer> permutation : permutations(n)) {
            TreeNode tree = newTree(permutation);
            if (set.add(toString(tree))) {
                list.add(tree);
            }
        }
        return list;
    }

    private Iterable<? extends List<Integer>> permutations(int n) {
        List<Integer> nums = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        return new Iterable<List<Integer>>() {
            @Override
            public Iterator<List<Integer>> iterator() {
                return permutationIterator(nums);
            }
        };
    }

    public static <T> Iterator<List<T>> permutationIterator(List<T> list) {
        int size = list.size();
        int maxCount = factorial(size);

        return new Iterator<List<T>>() {
            int count = 0;
            int[] indexes = createInitialIndexes();

            private int[] createInitialIndexes() {
                int[] indexes = new int[size];
                for (int i = 0; i < indexes.length; ++i) {
                    indexes[i] = i;
                }
                return indexes;
            }

            @Override
            public boolean hasNext() {
                return count < maxCount;
            }

            @Override
            public List<T> next() {
                List<T> current = new ArrayList<>(size);
                for (int index : indexes) {
                    current.add(list.get(index));
                }

                if (++count < maxCount) {
                    updateIndexes();
                }

                return current;
            }

            private void updateIndexes() {
                int i = indexes.length - 2;
                for (; i >= 0; --i) {
                    if (indexes[i] < indexes[i + 1]) {
                        break;
                    }
                }
                int j = indexes.length - 1;
                for (;; j--) {
                    if (indexes[j] > indexes[i]) {
                        break;
                    }
                }

                swap(i, j);

                int half = (indexes.length - i) / 2;
                for (int k = 1; k <= half; ++k) {
                    swap(i + k, indexes.length - k);
                }
            }

            private void swap(int i, int j) {
                int tmp = indexes[i];
                indexes[i] = indexes[j];
                indexes[j] = tmp;
            }
        };
    }

    private static int factorial(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    String toString(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            builder.append("->");
            if (node != null) {
                builder.append(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return builder.toString();
    }

    TreeNode newTree(List<Integer> nums) {
        TreeNode dummy = new TreeNode(-1);
        for (int num : nums) {
            insert(dummy, new TreeNode(num));
        }
        return dummy.right;
    }

    void insert(TreeNode parent, TreeNode node) {
        if (node.val < parent.val) {
            if (parent.left == null) {
                parent.left = node;
            } else {
                insert(parent.left, node);
            }
        } else {
            if (parent.right == null) {
                parent.right = node;
            } else {
                insert(parent.right, node);
            }
        }
    }
}
