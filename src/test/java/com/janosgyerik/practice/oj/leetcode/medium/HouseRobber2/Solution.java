package com.janosgyerik.practice.oj.leetcode.medium.HouseRobber2;

public class Solution {
    public int rob(int[] nums) {
        int length = nums.length;

        switch (length) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return Math.max(nums[0], nums[1]);
        }

        return Math.max(rob(nums, 0, length - 1), rob(nums, 1, length));
    }

    private int rob(int[] nums, int start, int end) {
        int maxAtPrevPrev = nums[start];
        int maxAtPrev = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i < end; ++i) {
            int current = nums[i];
            int max = Math.max(maxAtPrevPrev + current, maxAtPrev);
            maxAtPrevPrev = maxAtPrev;
            maxAtPrev = max;
        }
        return maxAtPrev;
    }
}
