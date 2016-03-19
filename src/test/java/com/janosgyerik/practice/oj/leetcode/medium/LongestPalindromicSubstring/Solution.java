package com.janosgyerik.practice.oj.leetcode.medium.LongestPalindromicSubstring;

public class Solution {
    public String longestPalindrome(String s) {
        int longestLen = 0;
        String longest = null;
        for (int mid = 0; mid < s.length(); ++mid) {
            int len = longestPalindromicLength(s, mid);
            if (longestLen < len) {
                longestLen = len;
                longest = getPalindrome(s, mid, len);
            }
        }
        return longest;
    }

    private String getPalindrome(String s, int mid, int len) {
        int startOffset = 1 - len & 1;
        int start = mid - len / 2 + startOffset;
        return s.substring(start, start + len);
    }

    public int longestPalindromicLength(String s, int mid) {
        int oddPalindromicLength = 1 + longestPalindromicLength(s, mid - 1, mid + 1);
        int evenPalindromicLength = longestPalindromicLength(s, mid, mid + 1);
        return Math.max(oddPalindromicLength, evenPalindromicLength);
    }

    private int longestPalindromicLength(String s, int start, int end) {
        int len = 0;
        for (; 0 <= start && end < s.length(); --start, ++end) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            len += 2;
        }
        return len;
    }
}
