package com.janosgyerik.practice.oj.leetcode.medium.LongestPalindromicSubstring;

public class Solution {
    public String longestPalindrome(String s) {
        int longestLen = 0;
        String longest = "";
        for (int i = 0; i < s.length(); ++i) {
            int length = getPalindromicLength(s, i);
            if (length > longestLen) {
                longestLen = length;
                int start;
                if ((length & 1) == 0) {
                    start = i - length / 2 + 1;
                } else {
                    start = i - length / 2;
                }
                longest = s.substring(start, start + length);
            }
        }
        return longest;
    }

    private int getPalindromicLength(String s, int mid) {
        return Math.max(getPalindromicLength(s, mid, mid + 1), 1 + getPalindromicLength(s, mid - 1, mid + 1));
    }

    private int getPalindromicLength(String s, int left, int right) {
        int length = 0;
        int p1 = left;
        int p2 = right;
        while (p1 >= 0 && p2 < s.length() && s.charAt(p1) == s.charAt(p2)) {
            length += 2;
            --p1;
            ++p2;
        }
        return length;
    }
}
