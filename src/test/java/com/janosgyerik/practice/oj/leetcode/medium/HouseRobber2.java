package com.janosgyerik.practice.oj.leetcode.medium;

public class HouseRobber2 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            return nums[0];
        }

        // choose first num, so don't count last
        int backup = nums[length - 1];
        nums[length - 1] = 0;
        int alt1 = robHelper(nums);
        nums[length - 1] = backup;

        // don't count first num
        nums[0] = 0;
        int alt2 = robHelper(nums);

        // don't count first two nums
        nums[1] = 0;
        int alt3 = robHelper(nums);

        return Math.max(alt1, Math.max(alt2, alt3));
    }

    private int robHelper(int[] nums) {
        int length = nums.length;
        int maxAtPrevPrev = nums[0];
        int maxAtPrev = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; ++i) {
            int current = nums[i];
            int max = Math.max(maxAtPrevPrev + current, maxAtPrev);
            maxAtPrevPrev = maxAtPrev;
            maxAtPrev = max;
        }
        return maxAtPrev;
    }
}