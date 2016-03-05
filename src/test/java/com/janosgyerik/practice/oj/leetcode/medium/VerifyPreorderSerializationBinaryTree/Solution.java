package com.janosgyerik.practice.oj.leetcode.medium.VerifyPreorderSerializationBinaryTree;

import java.util.ArrayDeque;

public class Solution {
    private static final String NULL_TOKEN = "#";
    private static final Object LEFT = new Object();
    private static final Object RIGHT = new Object();

    public boolean isValidSerialization(String preorder) {
        String[] tokens = preorder.split(",");
        ArrayDeque<Object> stack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            if (!token.equals(NULL_TOKEN)) {
                stack.push(LEFT);
            } else {
                while (!stack.isEmpty() && stack.peek() == RIGHT) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                    stack.push(RIGHT);
                } else {
                    return i + 1 == tokens.length;
                }
            }
        }
        return stack.isEmpty();
    }
}
