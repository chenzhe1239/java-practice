package com.janosgyerik.misc.tree;

public class TreeNode<T> {

    final T value;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }
}
