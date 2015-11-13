package com.janosgyerik.practice.oj.leetcode.medium.RemoveInvalidParens;

import java.util.*;

public class Solution {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public List<String> removeInvalidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        List<String> result = new ArrayList<>();

        for (int pos = 0; pos < chars.length; ++pos) {
            char c = chars[pos];
            if (c == OPEN) {
                stack.push(c);
            } else if (c == CLOSE) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    for (int seek = pos - 1; seek >= 0; --seek) {
                        if (chars[seek] == CLOSE) {
                            result.add(stringWithPosRemoved(chars, seek));
                        }
                    }
                }
            }
        }

        if (result.isEmpty() && stack.isEmpty()) {
            return Collections.singletonList(s);
//            return Collections.singletonList(text.toString());
        }

        return result;
    }

    public String stringWithPosRemoved(String string, int pos) {
        return new String(arrWithPosRemoved(string.toCharArray(), pos));
    }

    private String stringWithPosRemoved(char[] chars, int pos) {
        return new String(arrWithPosRemoved(chars, pos));
    }

    private char[] arrWithPosRemoved(char[] chars, int pos) {
        if (pos == 0) {
            return Arrays.copyOfRange(chars, 1, chars.length);
        }
        if (pos == chars.length - 1) {
            return Arrays.copyOfRange(chars, 0, chars.length - 1);
        }
        char[] copy = new char[chars.length - 1];
        System.arraycopy(chars, 0, copy, 0, pos);
        System.arraycopy(chars, pos + 1, copy, pos, chars.length - pos - 1);
        return copy;
    }
}