package com.janosgyerik.practice.oj.leetcode.hard.WildcardMatching;

public class Solution {
    public boolean isMatch(String text, String pattern) {
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
                    boolean match = isMatch(text.substring(iText), subPattern);
                    if (match) {
                        return true;
                    }
                }
                return false;
            }
        }
        return iText == text.length();
    }
}
