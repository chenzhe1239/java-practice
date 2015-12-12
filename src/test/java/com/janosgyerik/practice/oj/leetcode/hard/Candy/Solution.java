package com.janosgyerik.practice.oj.leetcode.hard.Candy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {

    protected static class IndexAndRating {
        private final int index;
        private int rating;

        protected IndexAndRating(int index, int rating) {
            this.index = index;
            this.rating = rating;
        }
    }

    public int candy(int[] ratings) {
        if (ratings.length < 2) {
            return ratings.length;
        }

        IndexAndRating[] pairs = createIndexAndRatingPairs(ratings);
        sortByRating(pairs);
        int[] reducedRatings = reduceByGroups(pairs);
//        adjustFromLeft(reducedRatings);
//        adjustFromRight(reducedRatings);
//        adjustEqualNeighbors(reducedRatings);

        return sum(reducedRatings);
    }

    private void sortByRating(IndexAndRating[] pairs) {
        Arrays.sort(pairs, new Comparator<IndexAndRating>() {
            @Override
            public int compare(IndexAndRating o1, IndexAndRating o2) {
                return Integer.compare(o1.rating, o2.rating);
            }
        });
    }

    protected int[] reduceByGroups(IndexAndRating[] pairs) {
        int[] reducedRatings = new int[pairs.length];
        int rating = 1;
        int current = pairs[0].rating;
        for (IndexAndRating pair : pairs) {
            if (pair.rating != current) {
                current = pair.rating;
                ++rating;
            }
            reducedRatings[pair.index] = rating;
        }
        return reducedRatings;
    }

    private int sum(int[] ints) {
        return IntStream.of(ints).sum();
    }

    private IndexAndRating[] createIndexAndRatingPairs(int[] ratings) {
        IndexAndRating[] pairs = new IndexAndRating[ratings.length];
        for (int i = 0; i < ratings.length; ++i) {
            pairs[i] = new IndexAndRating(i, ratings[i]);
        }
        return pairs;
    }
}
