package com.janosgyerik.practice.oj.leetcode.hard.MedianTwoSortedArrays;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        double median;
        int prev = 0;
        for (int i1 = 0, i2 = 0, count = 0;;) {
            int item;
            if (i1 < nums1.length && i2 < nums2.length) {
                if (nums1[i1] <= nums2[i2]) {
                    item = nums1[i1++];
                } else {
                    item = nums2[i2++];
                }
            } else if (i1 < nums1.length) {
                item = nums1[i1++];
            } else {
                item = nums2[i2++];
            }
            ++count;
            if (count == len / 2 + 1) {
                if (len % 2 == 1) {
                    median = item;
                } else {
                    median = (prev + item) / 2.;
                }
                break;
            }
            prev = item;
        }
        return median;
    }
}
