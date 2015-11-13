package com.janosgyerik.practice.oj.leetcode.easy;

public class HouseRobberTest {
    public int rob(int[] nums) {
        int length = nums.length;
        switch (length) {
            case 0: return 0;
            case 1: return nums[0];
        }

        int[] result = new int[length];
        boolean[] useLast = new boolean[length];

        result[0] = nums[0];
        useLast[0] = true;

        result[1] = Math.max(nums[0], nums[1]);
        useLast[1] = result[1] > result[0];

        for (int i = 2; i < length; ++i) {
            int current = nums[i];
            if (useLast[i - 1]) {
                result[i] = Math.max(result[i - 2] + current, result[i - 1]);
                useLast[i] = result[i] - current == result[i - 2];
            } else {
                result[i] = result[i - 1] + current;
                useLast[i] = true;
            }
        }
        return result[length - 1];
    }
}