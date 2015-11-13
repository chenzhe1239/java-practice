package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.TreeNode;

public class ConvertSortedArrayToBinarySearchTreeTest {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        return insert(num, 0, num.length);
    }

    private TreeNode insert(int[] num, int start, int end) {
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);
        if (start < mid) {
            root.left = insert(num, start, mid);
        }
        if (mid + 1 < end) {
            root.right = insert(num, mid + 1, end);
        }
        return root;
    }

    private void insert(TreeNode node, int num) {
        if (num < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(num);
            } else {
                insert(node.left, num);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(num);
            } else {
                insert(node.right, num);
            }
        }
    }
    // 0,16
    //  0,16 -> insert [8], branch for 0,8 + 9,16
    //  0,8 -> insert [4], branch for 0,4 + 5,8
    //  0,4 -> insert [2], branch for 0,2 + 3,4
    //  0,2 -> insert [1], branch for 0,1
    //  0,1 -> insert [0]
    //  3,4 -> insert [3]
    //  5,8 -> insert [6], branch for 5,6 + 7,8
    //  5,6 -> insert [5]
    //  7,8 -> insert [7]
    //
    // 0, 4
    // 0, 2  // 2, 4
    // 0, 1  // 1, 2
    // 0, 2 -> 2, 4 -> 2, 3
    // 0, 2 -> 2, 4 -> 3, 4

    // 0, 8 -> 4, 8 -> 4, 6 -> 4, 5
    // 0, 8 -> 4, 8 -> 4, 6 -> 5, 6
    // 0, 8 -> 4, 8 -> 6, 8 -> 6, 7
    // 0, 8 -> 4, 8 -> 6, 8 -> 7, 8
}
