package com.janosgyerik.practice.oj.leetcode.hard.LongestConsecutiveSequence;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 1;
        while (!set.isEmpty()) {
            int value = set.iterator().next();
            set.remove(value);
            longest = Math.max(longest, 1 + count(value, 1, set) + count(value, -1, set));
        }

        return longest;
    }

    int count(int value, int offset, Set<Integer> set) {
        int count = 0;
        while (set.contains(value + offset)) {
            set.remove(value + offset);
            value += offset;
            ++count;
        }
        return count;
    }
}
