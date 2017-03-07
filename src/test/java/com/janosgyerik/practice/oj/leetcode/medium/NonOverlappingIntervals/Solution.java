package com.janosgyerik.practice.oj.leetcode.medium.NonOverlappingIntervals;

import com.janosgyerik.practice.oj.leetcode.common.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Definition for an interval.
 * public class Interval {
 * int start;
 * int end;
 * Interval() { start = 0; end = 0; }
 * Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    // order increasing by start and decreasing by end
    static class StartComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            int cmp = Integer.compare(o1.start, o2.start);
            if (cmp != 0) {
                return cmp;
            }
            return -Integer.compare(o1.end, o2.end);
        }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new StartComparator());

        int count = 0;
        for (int prev = 0, next = 1; next < intervals.length; next++) {
            if (contains(intervals[prev], intervals[next])) {
                count++;
                prev = next;
            } else if (overlap(intervals[prev], intervals[next])) {
                count++;
            } else {
                prev = next;
            }
        }
        return count;
    }

    private boolean contains(Interval prev, Interval next) {
        return next.end <= prev.end;
    }

    private boolean overlap(Interval prev, Interval next) {
        return next.start < prev.end;
    }
}

