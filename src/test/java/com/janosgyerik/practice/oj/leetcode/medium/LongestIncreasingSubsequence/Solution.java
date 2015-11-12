package com.janosgyerik.practice.oj.leetcode.medium.LongestIncreasingSubsequence;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0, 0, new HashMap<>());
    }

    private int lengthOfLIS(int[] nums, int value, int pos, int count, Map<Integer, Integer> results) {
        int result;
        if (pos >= nums.length) {
            result = count;
        } else if (nums[pos] < value) {
            result = lengthOfLIS(nums, value, pos + 1, count, results);
        } else {
            result = Math.max(
                    lengthOfLIS(nums, value, pos + 1, count, results),
                    lengthOfLIS(nums, nums[pos], pos + 1, count + 1, results)
            );
        }
        results.put(pos, result);
        return result;
    }
}
