package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingSubsequence;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        int min = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            if (num > min) {
                ++count;
            }
            min = num;
        }
        return count;
    }
}
