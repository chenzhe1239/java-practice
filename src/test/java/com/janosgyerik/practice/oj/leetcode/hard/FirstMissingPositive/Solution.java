package com.janosgyerik.practice.oj.leetcode.hard.FirstMissingPositive;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int min = findMin(nums);
        if (1 < min) {
            return 1;
        }

        for (int i = 0; i < nums.length; ) {
            if (0 <= nums[i] && nums[i] - min < nums.length
                    && nums[i] != min + i
                    && nums[nums[i] - min] != nums[i]) {
                swap(nums, i, nums[i] - min);
            } else {
                i++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != min + i) {
                return min + i;
            }
        }
        return nums[nums.length - 1] + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < 0) {
                continue;
            }
            min = Math.min(min, num);
        }
        return min;
    }
}
