package com.janosgyerik.practice.oj.leetcode.medium.ContainerWithMostWater;

import java.util.*;

public class Solution {
    public int maxArea(int[] height) {
        int length = height.length;

        if (length < 2) {
            return 0;
        }

        return Math.max(maxAreaHelper(height), maxAreaHelper(reversed(height)));
    }

    private int[] reversed(int[] height) {
        int[] copy = new int[height.length];
        for (int i = 0; i < height.length; ++i) {
            copy[i] = height[height.length - i - 1];
        }
        return copy;
    }

    private int maxAreaHelper(int[] height) {
        SortedMap<Integer, Integer> firstPosMap = new TreeMap<>();
        firstPosMap.put(height[0], 0);

        int maxArea = 0;
        for (int pos = 1; pos < height.length; ++pos) {
            Integer firstPos = firstPosMap.get(height[pos]);
            if (firstPos == null) {
                firstPosMap.put(height[pos], pos);
                Integer prevLowerPos = findPrevLowerPos(height, pos);
                if (prevLowerPos == null) {
                    firstPos = pos;
                } else {
                    firstPos = firstPosMap.get(height[prevLowerPos]);
                }
            }
            int area = (pos - firstPos) * height[firstPos];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    protected Integer findPrevLowerPos(int[] height, int pos) {
        for (int prevLowerPos = pos - 1; prevLowerPos >= 0; --prevLowerPos) {
            if (height[prevLowerPos] < height[pos]) {
                return prevLowerPos;
            }
        }
        return null;
    }
}
