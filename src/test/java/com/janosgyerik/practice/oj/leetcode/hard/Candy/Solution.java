package com.janosgyerik.practice.oj.leetcode.hard.Candy;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {

    public int candy(int[] ratings) {
        return sum(calculate(ratings));
    }

    protected int[] calculate(int... ratings) {
        int[] candies = createArrayOfOnes(ratings.length);
        calculateFromLeft(ratings, candies);
        calculateFromRight(ratings, candies);
        return candies;
    }

    private int[] createArrayOfOnes(int length) {
        int[] ones = new int[length];
        Arrays.fill(ones, 1);
        return ones;
    }

    private void calculateFromLeft(int[] ratings, int[] candies) {
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
    }

    private void calculateFromRight(int[] ratings, int[] candies) {
        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
    }

    private int sum(int... ints) {
        return IntStream.of(ints).sum();
    }
}
