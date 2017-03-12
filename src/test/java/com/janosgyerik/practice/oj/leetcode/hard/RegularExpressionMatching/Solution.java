package com.janosgyerik.practice.oj.leetcode.hard.RegularExpressionMatching;

public class Solution {
    public boolean isMatch(String input, String pattern) {
        return isMatch(input, pattern, 0, 0);
    }

    public boolean isMatch(String input, String pattern, int i, int pi) {
        if (i == input.length()) return allZeroWidth(pattern, pi);
        if (pi == pattern.length()) return false;

        while (i < input.length() && pi < pattern.length()) {
            char ci = input.charAt(i);
            char cp = pattern.charAt(pi);

            if (pi + 1 < pattern.length()) {
                char cp2 = pattern.charAt(pi + 1);
                if (cp2 == '*') {
                    if (cp == '.') {
                        for (; i <= input.length(); i++) {
                            if (isMatch(input, pattern, i, pi + 2)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        for (; i < input.length() && input.charAt(i) == cp; i++) {
                            if (isMatch(input, pattern, i, pi + 2)) {
                                return true;
                            }
                        }
                        pi += 2;
                        continue;
                    }
                }
            }

            if (ci != cp && cp != '.') return false;
            i++;
            pi++;
        }
        return isMatch(input, pattern, i, pi);
    }

    private boolean allZeroWidth(String pattern, int start) {
        if ((pattern.length() - start) % 2 != 0) {
            return false;
        }
        for (int i = start; i + 1 < pattern.length(); i += 2) {
            if (pattern.charAt(i + 1) != '*') {
                return false;
            }
        }
        return true;
    }
}
