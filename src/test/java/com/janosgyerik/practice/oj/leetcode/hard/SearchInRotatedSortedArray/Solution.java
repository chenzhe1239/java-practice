package com.janosgyerik.practice.oj.leetcode.hard.SearchInRotatedSortedArray;

public class Solution {

    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    public int search(int[] nums, int target, int start, int end) {
        if (start == end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (target < nums[mid]) {
            if (target < nums[start] && nums[start] < nums[mid]) {
                return search(nums, target, mid + 1, end);
            }
            return search(nums, target, start, mid);
        }
        if (nums[end - 1] < target && nums[mid] <= nums[end - 1]) {
            return search(nums, target, start, mid);
        }
        return search(nums, target, mid + 1, end);
    }
}
