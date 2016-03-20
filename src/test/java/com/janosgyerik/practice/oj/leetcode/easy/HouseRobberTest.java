package com.janosgyerik.practice.oj.leetcode.easy;

public class HouseRobberTest {
    public int rob(int[] nums) {
        int max = 0;
        int prev = 0;
        int prevprev = 0;
        for (int current : nums) {
            max = Math.max(current + prevprev, prev);
            prevprev = prev;
            prev = max;
        }
        return max;
    }
}
