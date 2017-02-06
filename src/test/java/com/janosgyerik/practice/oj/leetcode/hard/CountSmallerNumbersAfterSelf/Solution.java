package com.janosgyerik.practice.oj.leetcode.hard.CountSmallerNumbersAfterSelf;

import java.util.*;

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new LinkedList<>();

        SortedList sorted = new SortedList(nums.length);

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int index = sorted.countSmaller(num);
            counts.add(0, index);
            sorted.insert(index, num);
        }

        return counts;
    }

    static class SortedList {

        private final List<Integer> nums;

        public SortedList(int capacity) {
            this.nums = new ArrayList<>(capacity);
        }

        public int countSmaller(int num) {
            int index = Collections.binarySearch(nums, num);
            if (index < 0) {
                return -1 - index;
            }
            while (index > 0 && nums.get(index - 1) == num) {
                index--;
            }
            return index;
        }

        public void insert(int index, int num) {
            nums.add(index, num);
        }

        public List<Integer> list() {
            return Collections.unmodifiableList(nums);
        }
    }
}
