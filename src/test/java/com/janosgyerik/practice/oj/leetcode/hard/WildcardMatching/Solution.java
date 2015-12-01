package com.janosgyerik.practice.oj.leetcode.hard.WildcardMatching;

public class Solution {
    public boolean isMatch(String text, String pattern) {
        String[] segments = split(pattern);
        String firstSegment = segments[0];
        if (segments.length == 1) {
            return startsWith(text, firstSegment) && text.length() == firstSegment.length();
        }

        if (!firstSegment.isEmpty()) {
            if (!startsWith(text, firstSegment)) {
                return false;
            }
            segments[0] = "";
            text = text.substring(firstSegment.length());
        }

        String lastSegment = segments[segments.length - 1];
        if (!lastSegment.isEmpty()) {
            if (!endsWith(text, lastSegment)) {
                return false;
            }
            segments[segments.length - 1] = "";
            text = text.substring(0, text.length() - lastSegment.length());
        }

        int textIndex = 0;
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            int index = indexOf(text, segment, textIndex);
            if (index < 0) {
                return false;
            }
            textIndex = index + segment.length();
        }

        return textIndex == text.length() || segments[segments.length - 1].isEmpty();
    }

    public int indexOf(String text, String pattern) {
        return indexOf(text, pattern, 0);
    }

    public int indexOf(String text, String pattern, int start) {
        for (int pos = start; pos <= text.length() - pattern.length(); ++pos) {
            int i = 0;
            for (; i < pattern.length(); ++i) {
                char c = text.charAt(pos + i);
                char p = pattern.charAt(i);
                if (c != p && p != '?') {
                    break;
                }
            }
            if (i == pattern.length()) {
                return pos;
            }
        }
        return -1;
    }

    private boolean startsWith(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); ++i) {
            if (pattern.charAt(i) != text.charAt(i) && pattern.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    private boolean endsWith(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }
        final int offset = text.length() - pattern.length();
        for (int i = 0; i < pattern.length(); ++i) {
            if (pattern.charAt(i) != text.charAt(offset + i) && pattern.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    private String[] split(String text) {
        return text.split("\\*", -1);
    }
}
