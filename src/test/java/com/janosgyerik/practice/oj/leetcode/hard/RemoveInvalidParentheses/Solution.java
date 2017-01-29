package com.janosgyerik.practice.oj.leetcode.hard.RemoveInvalidParentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public static final char OPEN = '(';
    public static final char CLOSE = ')';

    public List<String> removeInvalidParentheses(String s) {
        Set<String> results = new HashSet<>();
        removeInvalidParentheses(new StringBuilder(s), results);
        return results.stream().collect(Collectors.toList());
    }

    private void removeInvalidParentheses(StringBuilder sb, Set<String> results) {
        List<Integer> closers = new ArrayList<>();
        int open = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == OPEN) {
                open++;
            } else if (c == CLOSE) {
                closers.add(i);
                if (open > 0) {
                    open--;
                } else {
                    for (int pos : closers) {
                        removeInvalidParentheses(copyWithoutPos(sb, pos), results);
                    }
                    return;
                }
            }
        }
        for (String s2 : removeOpeners(sb.toString(), open)) {
            results.add(s2);
        }
    }

    private StringBuilder copyWithoutPos(StringBuilder sb, int pos) {
        return new StringBuilder(sb).deleteCharAt(pos);
    }

    private List<String> removeOpeners(String s, int open) {
        Set<String> queue = new HashSet<>();
        queue.add(s);
        while (open > 0) {
            List<String> current = new ArrayList<>(queue);
            queue.clear();
            for (String s2 : current) {
                removeOpeners(s2, queue);
            }
            open--;
        }
        return new ArrayList<>(queue);
    }

    private void removeOpeners(String s, Set<String> results) {
        StringBuilder sb = new StringBuilder(s);
        List<Integer> openers = new ArrayList<>();
        int close = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == CLOSE) {
                close++;
            } else if (c == OPEN) {
                openers.add(i);
                if (close > 0) {
                    close--;
                } else {
                    for (int pos : openers) {
                        results.add(copyWithoutPos(sb, pos).toString());
                    }
                    return;
                }
            }
        }
    }
}
