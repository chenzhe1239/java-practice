package com.janosgyerik.practice.oj.leetcode.medium.PatchingArray;

import java.util.*;

public class Solution {
    public int minPatches(int[] nums, int n) {
        List<Integer> list = toList(nums);
        int count = 0;
        int largest = nums.length > 0 ? Math.min(n, nums[nums.length - 1]) : 0;
        for (int target = 1; target <= largest; ++target) {
            int reduced = reduce(target, list);
            if (reduced > 0) {
                ++count;
                list.add(findInsertionPoint(target, list), target);
            }
        }

        long sum = list.stream().mapToInt(Integer::intValue).sum() + 1;
        while (sum <= n) {
            sum *= 2;
            ++count;
        }
        return count;
    }

    private int reduce(int target, List<Integer> list) {
        for (int i = Math.min(list.size() - 1, findInsertionPoint(target, list)); i >= 0 && target > 0; --i) {
            int value = list.get(i);
            if (target < value) {
                continue;
            }
            target -= value;
        }
        return target;
    }

    private int findInsertionPoint(int target, List<Integer> list) {
        int pos = Collections.binarySearch(list, target);
        return pos < 0 ? -1 - pos : pos;
    }

    private List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
