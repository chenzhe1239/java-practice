package com.janosgyerik.practice.oj.leetcode.hard.TrappingRainWater;

import java.util.*;

public class Solution {
    protected static class Elevation {
        private final int height;
        private final int pos;

        protected Elevation(int height, int pos) {
            this.height = height;
            this.pos = pos;
        }
    }

    public int trap(int... heights) {
        int rain = 0;

        int prev = 0;
        int pos = 0;
        Deque<Elevation> stack = new ArrayDeque<>();

        for (int height : heights) {
            if (height < prev) {
                stack.push(new Elevation(prev, pos - 1));
            } else if (height > prev) {
                while (!stack.isEmpty() && stack.peek().height <= height) {
                    Elevation elevation = stack.pop();
                    rain += (pos - elevation.pos - 1) * (elevation.height - prev);
                    prev = elevation.height;
                }
                if (!stack.isEmpty() && stack.peek().height > height) {
                    Elevation elevation = stack.peek();
                    rain += (pos - elevation.pos - 1) * (height - prev);
                }
            }
            prev = height;
            ++pos;
        }
        return rain;
    }
}
