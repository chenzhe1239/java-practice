package com.janosgyerik.practice.oj.leetcode.medium.PartitionListContainerWithMostWater;

import java.util.*;

public class Solution {
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int[] uniqueHeights = getUniqueHeights(height);

        Map<Integer, Integer> firstPos = mapToFirstSeenPos(height, uniqueHeights);

        Map<Integer, Integer> lastPos = mapToLastSeenPos(height, uniqueHeights);

        Optional<Integer> result =
                firstPos.keySet().stream()
                .filter(e -> !firstPos.get(e).equals(lastPos.get(e)))
                .map(e -> e * (lastPos.get(e) - firstPos.get(e)))
                .max((a, b) -> a - b);

        return result.get();
    }

    private Map<Integer, Integer> mapToFirstSeenPos(int[] heights, int[] uniqueHeights) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int height : uniqueHeights) {
            for (int pos = 0; pos < heights.length; ++pos) {
                if (heights[pos] >= height) {
                    result.put(height, pos);
                    break;
                }
            }
        }
        return result;
    }

    private Map<Integer, Integer> mapToLastSeenPos(int[] heights, int[] uniqueHeights) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int height : uniqueHeights) {
            for (int pos = heights.length - 1; pos >= 0; --pos) {
                if (heights[pos] >= height) {
                    result.put(height, pos);
                    break;
                }
            }
        }
        return result;
    }

    private int[] getUniqueHeights(int[] height) {
        return Arrays.stream(height).distinct().toArray();
    }
}
