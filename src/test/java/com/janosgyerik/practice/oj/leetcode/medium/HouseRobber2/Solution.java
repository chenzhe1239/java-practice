package com.janosgyerik.practice.oj.leetcode.medium.HouseRobber2;

public class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 1, nums.length), rob(nums, 0, nums.length - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int max = 0;
        int prev = 0;
        int prevprev = 0;

        for (int i = start; i < end; ++i) {
            int current = nums[i];
            max = Math.max(current + prevprev, prev);
            prevprev = prev;
            prev = max;
        }
        return max;
    }
}
