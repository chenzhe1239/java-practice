package com.janosgyerik.practice.oj.leetcode.hard.RegularExpressionMatching;

public class Solution {
    public boolean isMatch(String s, String p) {
        int sIndex = 0;
        int pIndex = 0;

        while (sIndex < s.length() && pIndex < p.length()) {
            char target = p.charAt(pIndex);

            if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
                pIndex += 2;
                if (target == '.') {
                    return pIndex == p.length();
                }
                while (sIndex < s.length() && s.charAt(sIndex) == target) {
                    ++sIndex;
                }
                continue;
            }

            if (target != '.') {
                if (s.charAt(sIndex) != target) {
                    return false;
                }
            }
            ++sIndex;
            ++pIndex;
        }
        return sIndex == s.length() && pIndex == p.length();
    }
}
