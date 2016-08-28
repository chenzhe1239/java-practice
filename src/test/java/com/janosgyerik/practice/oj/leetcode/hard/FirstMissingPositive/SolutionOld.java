package com.janosgyerik.practice.oj.leetcode.hard.FirstMissingPositive;

public class SolutionOld {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 1) {
            return nums[0] != 1 ? 1 : 2;
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 1 || nums.length <= nums[i]) {
                nums[i] = 0;
                continue;
            }
            int target = nums[i];
            nums[i] = 0;
            findAndSet(nums, target);
        }
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return max == nums.length ? max + 1 : nums.length;
    }

    public void findAndSet(int[] nums, int target) {
        if (nums[target] != target) {
            if (nums[target] < 1 || nums.length <= nums[target]) {
                nums[target] = target;
                return;
            }
            int orig = nums[target];
            nums[target] = target;
            findAndSet(nums, orig);
        }
    }
}
