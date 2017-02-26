package com.janosgyerik.practice.oj.leetcode.hard.MaximumGap;

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int maximumGap(int[] nums) {
        nums = IntStream.of(nums).distinct().toArray();

        if (nums.length < 2) {
            return 0;
        }

        IntSummaryStatistics stats = IntStream.of(nums).summaryStatistics();
        if (stats.getMin() == stats.getMax()) return 0;

        Buckets buckets = new Buckets(nums, stats);
        while (!buckets.emptyExists()) {
            System.out.println("halve!");
            buckets.halve();
        }

        return buckets.maxGap();
    }

    static class Buckets {
        final List<Bucket> buckets = new ArrayList<>();

        Buckets(int[] nums, IntSummaryStatistics stats) {
            int size = (int) ((stats.getMax() - stats.getMin() + 1) / (stats.getCount() - 1));
            for (long start = stats.getMin(); start < stats.getMax(); start += size) {
                int istart = (int) start;
                buckets.add(new Bucket(istart, istart + size));
            }
            for (int num : nums) {
                int id = (num - stats.getMin() - 1) / size;
                buckets.get(id).add(num);
            }
        }

        boolean emptyExists() {
            return buckets.stream().anyMatch(Bucket::isEmpty);
        }

        void halve() {
            List<Bucket> newBuckets = new ArrayList<>();
            for (Bucket bucket : buckets) {
                newBuckets.add(bucket.firstHalf());
                newBuckets.add(bucket.secondHalf());
            }
            buckets.clear();
            buckets.addAll(newBuckets);
        }

        int maxGap() {
            int max = 0;
            int prevMax = buckets.get(0).min;
            for (Bucket bucket : buckets) {
                if (!bucket.isEmpty()) {
                    max = Math.max(max, bucket.min - prevMax);
                    prevMax = bucket.max;
                }
            }
            return max;
        }

        static class Bucket {
            final int lower, upper;
            final List<Integer> nums = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            Bucket(int lower, int upper) {
                this.lower = lower;
                this.upper = upper;
            }

            boolean isEmpty() {
                return nums.isEmpty();
            }

            void add(int num) {
                nums.add(num);
                min = Math.min(min, num);
                max = Math.max(max, num);
            }

            int mid() {
                return lower + (upper - lower) / 2;
            }

            Bucket firstHalf() {
                return half(lower, mid());
            }

            Bucket secondHalf() {
                return half(mid(), upper);
            }

            Bucket half(int low, int up) {
                Bucket half = new Bucket(low, up);
                for (int num : nums) {
                    if (low <= num && num < up) {
                        half.add(num);
                    }
                }
                return half;
            }
        }
    }
}
