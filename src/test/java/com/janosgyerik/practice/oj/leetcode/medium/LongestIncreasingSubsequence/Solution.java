package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingSubsequence;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0, 0);
    }

    private int lengthOfLIS(int[] nums, int value, int pos, int count) {
        if (pos >= nums.length) {
            return count;
        }
        if (nums[pos] < value) {
            return lengthOfLIS(nums, value, pos + 1, count);
        }
        return Math.max(
                lengthOfLIS(nums, value, pos + 1, count),
                lengthOfLIS(nums, nums[pos], pos + 1, count + 1)
                );
    }
}
