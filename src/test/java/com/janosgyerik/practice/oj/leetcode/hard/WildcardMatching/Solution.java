package com.janosgyerik.practice.oj.leetcode.hard.WildcardMatching;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean isMatch(String text, String pattern) {
        return isMatchHelper(text, simplify(pattern), new HashSet<>());
    }

    private String simplify(String pattern) {
        return pattern.replaceAll("\\*{2,}", "*");
    }

    public boolean isMatchHelper(String text, String pattern, Set<String> unmatched) {
        int iText = 0;
        for (int iPattern = 0; iText < text.length() && iPattern < pattern.length(); ++iText, ++iPattern) {
            char cText = text.charAt(iText);
            char cPattern = pattern.charAt(iPattern);
            if (cPattern == '?') {
                // nothing to do
            } else if (cPattern != '*') {
                if (cText != cPattern) {
                    return false;
                }
            } else if (iPattern == pattern.length() - 1) {
                return true;
            } else {
                String subPattern = pattern.substring(iPattern + 1);
                for (; iText < text.length(); ++iText) {
                    String subText = text.substring(iText);
                    if (unmatched.contains(subText)) {
                        continue;
                    }
                    boolean match = isMatchHelper(subText, subPattern, new HashSet<>());
                    if (match) {
                        return true;
                    }
                    unmatched.add(subText);
                }
                return false;
            }
        }
        return iText == text.length();
    }
}
