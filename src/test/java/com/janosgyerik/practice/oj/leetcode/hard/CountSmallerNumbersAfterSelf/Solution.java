package com.janosgyerik.practice.oj.leetcode.hard.CountSmallerNumbersAfterSelf;

import java.util.*;

// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
// TLE on very large input, test case 15/16: [5183,2271,3067,539,8939,2999,9264,737,3974,5846,-210,9278,5800,...]
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> smaller = new ArrayList<>(nums.length);

        SortedMap<Integer, Integer> counts = createCounts(nums);

        for (int num : nums) {
            counts.put(num, counts.get(num) - 1);
            smaller.add(countSmaller(counts, num));
        }

        return smaller;
    }

    private int countSmaller(SortedMap<Integer, Integer> counts, int value) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            if (num >= value) {
                break;
            }
            count += entry.getValue();
        }
        return count;
    }

    private SortedMap<Integer, Integer> createCounts(int[] nums) {
        SortedMap<Integer, Integer> counts = new TreeMap<>();
        for (int num : nums) {
            Integer count = counts.get(num);
            if (count == null) {
                count = 0;
            }
            counts.put(num, count + 1);
        }
        return counts;
    }
}
