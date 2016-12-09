package com.janosgyerik.practice.oj.leetcode.medium.FindRightInterval;

import com.janosgyerik.practice.oj.leetcode.common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
    static class IntervalIndex {
        final Interval interval;
        final int index;
        private IntervalIndex(Interval interval, int index) {
            this.interval = interval;
            this.index = index;
        }
    }

    public int[] findRightInterval(Interval[] intervals) {
        IntervalIndex[] indexes = IntStream.range(0, intervals.length)
                .mapToObj(i -> new IntervalIndex(intervals[i], i))
                .toArray(IntervalIndex[]::new);
        Arrays.sort(indexes, Comparator.comparingInt(index -> index.interval.start));
        return Arrays.stream(intervals).mapToInt(x -> findIndex(indexes, x.end)).toArray();
    }

    int findIndex(IntervalIndex[] indexes, int start) {
        int index = Arrays.binarySearch(indexes, new IntervalIndex(new Interval(start, -1), -1),
                Comparator.comparingInt(o -> o.interval.start));
        if (index < 0) {
            index = -1 - index;
            if (index == indexes.length) {
                return -1;
            }
        }
        return indexes[index].index;
    }
}
