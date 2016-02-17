package com.janosgyerik.practice.oj.leetcode.hard.CountOfRangeSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    static class PrefixSumIndex {
        final long sum;
        final int index;

        PrefixSumIndex(long sum, int index) {
            this.sum = sum;
            this.index = index;
        }

        @Override
        public String toString() {
            return String.format("%s <- %s", sum, index);
        }
    }

    Comparator<? super PrefixSumIndex> sumComparator = (o1, o2) -> Long.compare(o1.sum, o2.sum);

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSums = createPrefixSums(nums);

        List<PrefixSumIndex> prefixSumIndexList = createPrefixSumIndexList(prefixSums);
        Collections.sort(prefixSumIndexList, sumComparator);

        return countRangeSum(nums, lower, upper, prefixSums, prefixSumIndexList);
    }

    private long[] createPrefixSums(int[] nums) {
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 1; i < prefixSums.length; ++i) {
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
        }
        return prefixSums;
    }

    private List<PrefixSumIndex> createPrefixSumIndexList(long[] prefixSums) {
        List<PrefixSumIndex> prefixSumIndexList = new ArrayList<>(prefixSums.length);
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSumIndexList.add(new PrefixSumIndex(prefixSums[i], i));
        }
        return prefixSumIndexList;
    }

    public int countRangeSum(int[] nums, int lower, int upper, long[] prefixSums, List<PrefixSumIndex> prefixSumIndexList) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long rangeStart = prefixSums[i] + lower;
            long rangeEnd = prefixSums[i] + upper;
            PrefixSumIndex key = new PrefixSumIndex(rangeStart, -1);
            int index = Collections.binarySearch(prefixSumIndexList, key, sumComparator);
            if (index < 0) {
                index = -(index + 1);
            } else {
                while (index > 0 && prefixSumIndexList.get(index - 1).sum == rangeStart) {
                    --index;
                }
            }
            for (; index < prefixSumIndexList.size() && prefixSumIndexList.get(index).sum <= rangeEnd; ++index) {
                if (i < prefixSumIndexList.get(index).index) {
                    ++count;
                }
            }
        }
        return count;
    }
}
