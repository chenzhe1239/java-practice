package com.janosgyerik.practice.oj.leetcode.hard.FindDuplicateNumber;

import java.util.stream.IntStream;

public class Solution {
    public int findDuplicate(int... nums) {
        int sum = sum(nums);
        int uniqueSum = (nums.length - 1) * nums.length / 2;
        int diff = sum - uniqueSum;
        if (diff > 0 && isDuplicate(nums, diff)) {
            return diff;
        }

        int avg = sum / nums.length;
        if (isDuplicate(nums, avg)) {
            return avg;
        }

        for (int i = 1; i < avg; ++i) {
            if (isDuplicate(nums, avg + i)) {
                return avg + i;
            }
            if (isDuplicate(nums, avg - i)) {
                return avg - i;
            }
        }
        return 0;
    }

    private boolean isDuplicate(int[] nums, int target) {
        boolean seen = false;
        for (int num : nums) {
            if (num == target) {
                if (seen) {
                    return true;
                }
                seen = true;
            }
        }
        return false;
    }

    private int sum(int[] nums) {
        return IntStream.of(nums).sum();
    }
}
