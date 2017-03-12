package com.janosgyerik.practice.oj.leetcode.hard.MaximalRectangle;

import java.util.*;

public class Solution {
    static class Interval {
        final int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean intersect(Interval other) {
            return Math.max(start, other.start) < Math.min(end, other.end);
        }

        public Interval intersection(Interval next) {
            return new Interval(Math.max(start, next.start), Math.min(end, next.end));
        }

        public boolean isEmpty() {
            return start < end;
        }

        public int length() {
            return end - start;
        }

        @Override
        public boolean equals(Object obj) {
            Interval other = (Interval) obj;
            return start == other.start && end == other.end;
        }
    }

    static class Intersector {
        private List<Interval> intervals = Collections.emptyList();

        public int add(List<Interval> intervals) {
            if (this.intervals.isEmpty()) {
                this.intervals = intervals;
                return intervals.stream().mapToInt(Interval::length).max().orElse(0);
            }

            int longest = 0;
            List<Interval> copy = this.intervals;
            this.intervals = new ArrayList<>();
            for (Interval i1 : copy) {
                for (Interval i2 : intervals) {
                    if (i1.intersect(i2)) {
                        Interval intersection = i1.intersection(i2);
                        this.intervals.add(intersection);
                        longest = Math.max(longest, intersection.length());
                    }
                }
            }
            return longest;
        }

        public boolean isEmpty() {
            return intervals.isEmpty();
        }
    }

    public int maximalRectangle(char[][] matrix) {
        List<List<Interval>> intervals = intervals(matrix);

        int max = 0;
        for (int i = 0; i < intervals.size(); i++) {
            Intersector intersector = new Intersector();
            for (int j = i; j < intervals.size(); j++) {
                int longest = intersector.add(intervals.get(j));
                int height = j - i + 1;
                max = Math.max(max, longest * height);
                if (intersector.isEmpty()) {
                    break;
                }
            }
        }

        return max;
    }

    private List<List<Interval>> intervals(char[][] matrix) {
        List<List<Interval>> intervals = new ArrayList<>();
        for (char[] row : matrix) {
            intervals.add(intervals(row));
        }
        return intervals;
    }

    static List<Interval> intervals(char[] row) {
        List<Interval> intervals = new ArrayList<>();
        int start = 0, end = 0, pos = 0;
        for (char c : row) {
            pos++;
            if (c == '1') {
                end++;
            } else {
                if (start < end) {
                    intervals.add(interval(start, end));
                }
                start = end = pos;
            }
        }
        if (start < end) {
            intervals.add(interval(start, end));
        }
        return intervals;
    }

    static Interval interval(int start, int end) {
        return new Interval(start, end);
    }
}
