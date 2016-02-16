package com.janosgyerik.practice.oj.leetcode.hard.LongestValidParentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int longestValidParentheses(String s) {
        int maxLenForward = longestValidParentheses(s, '(', new CharGetter(s));
        int maxLenBackward = longestValidParentheses(s, ')', new BackwardCharGetter(s));
        return Math.max(maxLenForward, maxLenBackward);
    }

    static class CharGetter {
        final String s;

        CharGetter(String s) {
            this.s = s;
        }

        char charAt(int index) {
            return s.charAt(index);
        }
    }

    static class BackwardCharGetter extends CharGetter {
        BackwardCharGetter(String s) {
            super(s);
        }

        @Override
        char charAt(int index) {
            return s.charAt(s.length() -1 - index);
        }
    }

    public int longestValidParentheses(String s, char openChar, CharGetter charGetter) {
        Deque<Integer> stack = new ArrayDeque<>();

        int maxLen = 0;
        int currentLen = 0;

        for (int pos = 0; pos < s.length(); ++pos) {
            char c = charGetter.charAt(pos);
            if (c == openChar) {
                stack.push(pos);
            } else {
                if (stack.isEmpty()) {
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                    }
                    currentLen = 0;
                } else {
                    int startPos = stack.pop();
                    if (stack.isEmpty()) {
                        currentLen += pos - startPos + 1;
                    }
                }
            }
        }

        return Math.max(maxLen, currentLen);
    }
}
